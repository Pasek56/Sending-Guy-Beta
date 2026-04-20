package Main;

import Mosaicos.ManejoMosaico;
import javafx.geometry.Rectangle2D;

public class Eventos {
    Panel pane;
    Rectangle2D Teleport;
    Rectangle2D TeleportA_A;
    Rectangle2D TeleportA_A1;
    Rectangle2D TeleportA_A2;
    Rectangle2D TeleportA_A3;
    Rectangle2D TeleportA_A4;
    Rectangle2D TeleportA_A5;
    Rectangle2D TeleportAB;
    Rectangle2D TeleportBA;
    Rectangle2D EntradaB;
    Rectangle2D BEntrada;
    public Eventos(Panel pane) {
        this.pane = pane;
        int colA_A = 41;
        int RowA_A = 27;
        this.Teleport = new Rectangle2D(colA_A*Panel.TILE_SIZE, RowA_A*Panel.TILE_SIZE, 
                Panel.TILE_SIZE, Panel.TILE_SIZE);
        int ColA_A1 = 50;
        int Row1_A1 = 99;
        this.TeleportA_A = new Rectangle2D(ColA_A1*Panel.TILE_SIZE, Row1_A1*Panel.TILE_SIZE,
        Panel.TILE_SIZE, Panel.TILE_SIZE
        );
        int ColA_A2 = 1;
        int RowA_A2 = 84;
        this.TeleportA_A1 = new Rectangle2D(ColA_A2*Panel.TILE_SIZE, RowA_A2*Panel.TILE_SIZE,
        Panel.TILE_SIZE, Panel.TILE_SIZE);
        this.TeleportA_A2 = new Rectangle2D(99*Panel.TILE_SIZE, 48*Panel.TILE_SIZE,Panel.TILE_SIZE,Panel.TILE_SIZE);
        this.TeleportA_A3 = new Rectangle2D(39*Panel.TILE_SIZE, 67*Panel.TILE_SIZE, Panel.TILE_SIZE, Panel.TILE_SIZE);
        this.TeleportA_A4 = new Rectangle2D(98*Panel.TILE_SIZE, 84*Panel.TILE_SIZE, Panel.TILE_SIZE, Panel.TILE_SIZE);
        this.TeleportA_A5 = new Rectangle2D(68*Panel.TILE_SIZE,67*Panel.TILE_SIZE, Panel.TILE_SIZE,Panel.TILE_SIZE);
        this.TeleportAB = new Rectangle2D(2*Panel.TILE_SIZE,52*Panel.TILE_SIZE,Panel.TILE_SIZE,Panel.TILE_SIZE);
        this.TeleportBA = new Rectangle2D(97*Panel.TILE_SIZE,88*Panel.TILE_SIZE,Panel.TILE_SIZE,Panel.TILE_SIZE);
        this.EntradaB = new Rectangle2D(14*Panel.TILE_SIZE,11*Panel.TILE_SIZE,Panel.TILE_SIZE,Panel.TILE_SIZE);
        this.BEntrada = new Rectangle2D(88*Panel.TILE_SIZE,99*Panel.TILE_SIZE,Panel.TILE_SIZE,Panel.TILE_SIZE);
    }

    public void ChequeoEve() {
        if (Choque(0, Teleport, "any") && ManejoMosaico.MapaActual == 0) {
            Teleporting(1, 1, 50, 98);
        }
        if (Choque(1, TeleportA_A, "any") && ManejoMosaico.MapaActual == 1) {
            Teleporting(1, 0, 41, 28);
        }
        if(Choque(1,TeleportA_A1,"any")&& ManejoMosaico.MapaActual == 1){
            Teleporting(1,2,40,68);
        }
        if(Choque(1,TeleportA_A2, "any")&& ManejoMosaico.MapaActual == 1){
            Teleporting(1,2,67,67);
        }
        if(Choque(2,TeleportA_A3,"any") && ManejoMosaico.MapaActual == 2){
            Teleporting(1,1,3,84);
        }
        if(Choque(1,TeleportA_A4,"any") && ManejoMosaico.MapaActual == 1){
            Teleporting(1,2,66,67);
        }
        if(Choque(2,TeleportA_A5,"any") && ManejoMosaico.MapaActual == 2){
            Teleporting(1,1,97,84);
        }
        if(Choque(2,TeleportAB, "any") && ManejoMosaico.MapaActual == 2){
            Teleporting(1,3,96,88);
        }
        if(Choque(3,TeleportBA, "any") && ManejoMosaico.MapaActual == 3){
            Teleporting(1,2,3,52);
        }
        if(Choque(0,EntradaB,"any")&&ManejoMosaico.MapaActual == 0){
            Teleporting(1,3,88,98);
        }
        if(Choque(3,BEntrada,"ant")&&ManejoMosaico.MapaActual == 3){
            Teleporting(1,0,16,11);
        }
    }   
    public boolean Choque(int map, Rectangle2D zona, String Dire) {
        if (map == ManejoMosaico.MapaActual) {
            Rectangle2D playerHitBox = new Rectangle2D(
                pane.player.x + pane.player.HitBox.getMinX(),
                pane.player.y + pane.player.HitBox.getMinY(),
                pane.player.HitBox.getWidth(),
                pane.player.HitBox.getHeight()
            );
            return playerHitBox.intersects(zona) &&
                   (pane.player.Direccion.equals(Dire) || Dire.equals("any"));
        }
        return false;
    }

    public void Teleporting(int State, int Map, int colum, int Row) {
        pane.Comando = State;
        pane.Gui.Mensaje = "Teleport!";

        ManejoMosaico.MapaActual = Map;

        String ruta = "/BinMaps/Map00" + (Map + 1) + ".txt";
        pane.mosa.cargarDatos(ruta, Map);

        pane.player.x = colum * Panel.TILE_SIZE;
        pane.player.y = Row * Panel.TILE_SIZE;
        pane.player.Direccion = "down";
    }
}
