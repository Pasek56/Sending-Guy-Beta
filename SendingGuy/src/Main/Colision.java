package Main;

import Entidades.Entidades;
import Entidades.Jugador;
import Mosaicos.ManejoMosaico;
import Mosaicos.Mosaico;
import Objetos.SObjetos;
import javafx.geometry.Rectangle2D;

public class Colision {
    Panel panel;

    public Colision(Panel pane) {
        this.panel = pane;
    }

    public void chequeoMosaico(Entidades enty) {
        enty.colisionON = false;
        Rectangle2D hitbox = enty.HitBox;
        double speed = enty.Speed;

        double worldLeft   = enty.x + hitbox.getMinX();
        double worldRight  = enty.x + hitbox.getMinX() + hitbox.getWidth();
        double worldTop    = enty.y + hitbox.getMinY();
        double worldBottom = enty.y + hitbox.getMinY() + hitbox.getHeight();

        switch (enty.Direccion) {
            case "Up" -> {
                double newTop = worldTop - speed;
                checkTiles(enty, worldLeft, worldRight, newTop, true);
            }
            case "Down" -> {
                double newBottom = worldBottom + speed;
                checkTiles(enty, worldLeft, worldRight, newBottom, true);
            }
            case "Left" -> {
                double newLeft = worldLeft - speed;
                checkTiles(enty, worldTop, worldBottom, newLeft, false);
            }
            case "Right" -> {
                double newRight = worldRight + speed;
                checkTiles(enty, worldTop, worldBottom, newRight, false);
            }
        }
    }
    private void checkTiles(Entidades enty, double start, double end, double fixed, boolean vertical) {
        double mid = (start + end) / 2;

        int col1 = vertical ? (int)(start / Panel.TILE_SIZE) : (int)(fixed / Panel.TILE_SIZE);
        int row1 = vertical ? (int)(fixed / Panel.TILE_SIZE) : (int)(start / Panel.TILE_SIZE);

        int col2 = vertical ? (int)(mid / Panel.TILE_SIZE) : (int)(fixed / Panel.TILE_SIZE);
        int row2 = vertical ? (int)(fixed / Panel.TILE_SIZE) : (int)(mid / Panel.TILE_SIZE);

        int col3 = vertical ? (int)(end / Panel.TILE_SIZE) : (int)(fixed / Panel.TILE_SIZE);
        int row3 = vertical ? (int)(fixed / Panel.TILE_SIZE) : (int)(end / Panel.TILE_SIZE);

        if (col1 < 0 || col1 >= ManejoMosaico.MAX_WORLD_COL || row1 < 0 || row1 >= ManejoMosaico.MAX_WORLD_ROW ||
            col2 < 0 || col2 >= ManejoMosaico.MAX_WORLD_COL || row2 < 0 || row2 >= ManejoMosaico.MAX_WORLD_ROW ||
            col3 < 0 || col3 >= ManejoMosaico.MAX_WORLD_COL || row3 < 0 || row3 >= ManejoMosaico.MAX_WORLD_ROW) {
            enty.colisionON = true;
            return;
        }

        int tile1 = getTileIndex(col1, row1);
        int tile2 = getTileIndex(col2, row2);
        int tile3 = getTileIndex(col3, row3);

        if (isColision(tile1) || isColision(tile2) || isColision(tile3)) {
            enty.colisionON = true;
        }
    }

    private int getTileIndex(int col, int row) {
        if (col < 0 || col >= ManejoMosaico.MAX_WORLD_COL || row < 0 || row >= ManejoMosaico.MAX_WORLD_ROW) {
            return -1;
        }
        return panel.mosa.MapTile[ManejoMosaico.MapaActual][col][row];
    }

    private boolean isColision(int index) {
        if (index < 0 || index >= panel.mosa.tile.length) return false;
        Mosaico mosaico = panel.mosa.tile[index];
        if (mosaico == null) return false;

        if (mosaico.Type == null) {
            System.err.println("Advertencia: Type es null en mosaico index " + index);
            return false;
        }
        return mosaico.Colision;
    }
    public int ColisionObjeto(Entidades En, boolean Jugador) {
        for (int i = 0; i < panel.OBJ.length; i++) {
            SObjetos obj = panel.OBJ[i];
            if (obj != null && obj.mapIndex == ManejoMosaico.MapaActual) {
                double objX = obj.PosX + obj.Papu.getMinX();
                double objY = obj.PosY + obj.Papu.getMinY();
                double objW = obj.Papu.getWidth();
                double objH = obj.Papu.getHeight();
                Rectangle2D hitboxObjeto = new Rectangle2D(objX, objY, objW, objH);

                double entidadX = En.x + En.HitBox.getMinX();
                double entidadY = En.y + En.HitBox.getMinY();
                double entidadW = En.HitBox.getWidth();
                double entidadH = En.HitBox.getHeight();

                switch (En.Direccion) {
                    case "Up" -> entidadY -= En.Speed;
                    case "Down" -> entidadY += En.Speed;
                    case "Left" -> entidadX -= En.Speed;
                    case "Right" -> entidadX += En.Speed;
                }
                Rectangle2D hitboxEntidad = new Rectangle2D(entidadX, entidadY, entidadW, entidadH);
                if (hitboxEntidad.intersects(hitboxObjeto)) {
                    En.colisionON = true;
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void ColisionNPC(Jugador jugador) {
        double nextX = jugador.x;
        double nextY = jugador.y;

        switch (jugador.Direccion) {
            case "Up" -> nextY -= jugador.Speed;
            case "Down" -> nextY += jugador.Speed;
            case "Left" -> nextX -= jugador.Speed;
            case "Right" -> nextX += jugador.Speed;
        }

        Rectangle2D hitJugador = new Rectangle2D(
            nextX + jugador.HitBox.getMinX(),
            nextY + jugador.HitBox.getMinY(),
            jugador.HitBox.getWidth(),
            jugador.HitBox.getHeight()
        );
        for (Entidades npc : panel.NPCs) {
            if (npc != null && npc.mapIndex == ManejoMosaico.MapaActual) {
                Rectangle2D hitNPC = new Rectangle2D(
                    npc.x + npc.HitBox.getMinX(),
                    npc.y + npc.HitBox.getMinY(),
                    npc.HitBox.getWidth(),
                    npc.HitBox.getHeight()
                );
                if (hitJugador.intersects(hitNPC)) {
                    if (npc.solido) {
                        jugador.colisionON = true;
                    }
                    npc.Colision();
                    if (!panel.misionActiva && !panel.misionCompletada && npc.iniciaMision) {
                        panel.Gui.Notificacion("📦 Misión Iniciada!");
                    }
                }
            }
        }
    }

}