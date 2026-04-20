package Mosaicos;

import Main.Panel;
import java.io.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ManejoMosaico {
    private final Panel panel;
    public final Mosaico[] tile;
    public int[][][] MapTile;
    public static final int MaxMap = 6;
    public static int MapaActual = 0;
    public static final int MAX_WORLD_COL = 100;
    public static final int MAX_WORLD_ROW = 100; 

    public ManejoMosaico(Panel panel) {
        this.panel = panel;
        this.tile = new Mosaico[16];
        this.MapTile = new int[MaxMap][MAX_WORLD_COL][MAX_WORLD_ROW];
        cargarImagenes();
        cargarDatos("/BinMaps/Map001.txt",0);
        cargarDatos("/BinMaps/Map002.txt",1);
    }

    private void cargarImagenes() {
        try {
            tile[0] = new Mosaico();
            tile[0].image = cargarImagen("/Mapas/00.png");
            tile[0].Colision = true;
            tile[0].Type = "Borde";
            
            tile[1] = new Mosaico();
            tile[1].image = cargarImagen("/Mapas/01.png");
            tile[1].Colision = true;
            tile[1].Type = "Borde";
            
            tile[2] = new Mosaico();
            tile[2].image = cargarImagen("/Mapas/02.png");
            tile[2].Colision = false;
            tile[2].Type = "";
            
            tile[3] = new Mosaico();
            tile[3].image = cargarImagen("/Mapas/03.png");
            tile[3].Colision = false;
            tile[3].Type = "Piso";
            
            tile[4] = new Mosaico();
            tile[4].image = cargarImagen("/Mapas/04.png");
            tile[4].Colision = true;
            tile[4].Type = "Borde";
            
            tile[5] = new Mosaico();
            tile[5].image = cargarImagen("/Mapas/05.png");
            tile[5].Colision = false;
            tile[5].Type = "Piso";
            
            tile[6] = new Mosaico();
            tile[6].image = cargarImagen("/Mapas/06.png");
            tile[6].Colision = false;
            tile[6].Type = "Piso";
            
            tile[7] = new Mosaico();
            tile[7].image = cargarImagen("/Mapas/07.png");
            tile[7].Colision = false;
            tile[7].Type = "Piso";
            
            tile[8] = new Mosaico();
            tile[8].image = cargarImagen("/Mapas/08.png");
            tile[8].Colision = false;
            tile[8].Type = "Piso";
            
            tile[9] = new Mosaico();
            tile[9].image = cargarImagen("/Mapas/09.png");
            tile[9].Colision = true;
            tile[9].Type = "Borde";
            
            tile[10] = new Mosaico();
            tile[10].image = cargarImagen("/Mapas/10.png");
            tile[10].Colision = false;
            tile[10].Type = "Piso";
            
            tile[11] = new Mosaico();
            tile[11].image = cargarImagen("/Mapas/11.png");
            tile[11].Colision = false;
            tile[11].Type = "Piso";
            
            tile[12] = new Mosaico();
            tile[12].image = cargarImagen("/Mapas/12.png");
            tile[12].Colision = true;
            tile[12].Type = "Borde";
            
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes de mosaico:");
            e.printStackTrace();
        }
    }

    private Image cargarImagen(String ruta) {
        InputStream is = getClass().getResourceAsStream(ruta);
        if (is == null) {
            System.err.println("Imagen no encontrada: " + ruta);
            return null;
        }
        return new Image(is);
    }

    private int getTileIndex(int col, int fil, int Map) {
        return (col >= 0 && col < MAX_WORLD_COL && fil >= 0 && fil < MAX_WORLD_ROW)
                ? MapTile[Map][col][fil] : -1;
    }

    public void cargarDatos(String rutaArchivo, int Map) {
        InputStream is = getClass().getResourceAsStream(rutaArchivo);
        if (is == null) {
            System.err.println("Archivo de mapa no encontrado: " + rutaArchivo);
            return;
        }
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(is))) {
            for (int fila = 0; fila < MAX_WORLD_ROW; fila++) {
                String linea = lector.readLine();
                if (linea == null) break;

                String[] valores = linea.trim().split(" ");
                for (int col = 0; col < MAX_WORLD_COL; col++) {
                    if (col < valores.length) {
                        try {
                            MapTile[Map][col][fila] = Integer.parseInt(valores[col]);
                        } catch (NumberFormatException e) {
                            MapTile[Map][col][fila] = 0;
                        }
                    } else {
                        MapTile[Map][col][fila] = -1; 
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de mapa:");
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext g) {
        final int TILE_SIZE = Panel.TILE_SIZE;

        if (panel == null || panel.player == null) return;

        int playerWorldX = panel.player.getMapX();
        int playerWorldY = panel.player.getMapY();
        int camX = playerWorldX - Panel.SCREEN_WIDTH / 2;
        int camY = playerWorldY - Panel.SCREEN_HEIGHT / 2;
        int worldStartCol = Math.max(camX / TILE_SIZE, 0);
        int worldStartRow = Math.max(camY / TILE_SIZE, 0);
        int worldEndCol = Math.min(worldStartCol + Panel.MAX_SCREEN_COL + 2, MAX_WORLD_COL);
        int worldEndRow = Math.min(worldStartRow + Panel.MAX_SCREEN_ROW + 2, MAX_WORLD_ROW);

        for (int row = worldStartRow; row < worldEndRow; row++) {
            for (int col = worldStartCol; col < worldEndCol; col++) {
                int tileIndex = getTileIndex(col, row, MapaActual);
                if (tileIndex < 0 || tileIndex >= tile.length) continue;

                Mosaico mosaico = tile[tileIndex];
                if (mosaico == null || mosaico.image == null) continue;

                int tileWorldX = col * TILE_SIZE;
                int tileWorldY = row * TILE_SIZE;
                int screenX = tileWorldX - camX;
                int screenY = tileWorldY - camY;
                g.drawImage(mosaico.image, screenX, screenY, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}
