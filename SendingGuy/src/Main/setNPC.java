package Main;

import Entidades.*;

public class setNPC {
    Panel pane;
    Jugador pl;
    public setNPC(Panel p){
        this.pane = p;
    }
    public void cargarNPC(){
        String[] dialogo = {"Buenas!","Podria entregar algo a un profesor en el 2do piso?"};
        String[] Dialogo = {"Hey, Fijate"," No me interrumpas"};
        pane.NPCs[0] = new NPC_Vigilante(86*Panel.TILE_SIZE,31*Panel.TILE_SIZE,pane);
        pane.NPCs[0].mapIndex = 0;
        pane.NPCs[0].solido = true;
        pane.NPCs[0].iniciaMision = false;
        pane.NPCs[1] = new NPC_Vigilante(86*Panel.TILE_SIZE,34*Panel.TILE_SIZE,pane);
        pane.NPCs[1].mapIndex = 0;
        pane.NPCs[1].solido = true;
        pane.NPCs[1].iniciaMision = false;
        pane.NPCs[2] =new NPC_Administradora(75*Panel.TILE_SIZE,71*Panel.TILE_SIZE, pane, dialogo);
        pane.NPCs[2].solido = true;
        pane.NPCs[2].mapIndex = 1;
        pane.NPCs[2].iniciaMision = true;
        pane.NPCs[3] = new NPC_Alumno(62*Panel.TILE_SIZE, 92*Panel.TILE_SIZE, pane, Dialogo);
        pane.NPCs[3].mapIndex = 1;
        pane.NPCs[3].solido = true;
        pane.NPCs[3].iniciaMision = false;
        String[] dialogo2 = {"Hola Joven", "¿Qué necesita?"};
        pane.NPCs[4] = new NPC_Profesor(10 * Panel.TILE_SIZE, 30 * Panel.TILE_SIZE, pane, dialogo2);
        pane.NPCs[4].mapIndex = 2;
        pane.NPCs[4].solido = true;
        pane.NPCs[4].iniciaMision = false;
        pane.NPCs[5] = new NPC_Alumno(18 * Panel.TILE_SIZE, 90 * Panel.TILE_SIZE, pane, Dialogo);
        pane.NPCs[5].mapIndex = 0;
        pane.NPCs[5].solido = true;
        pane.NPCs[5].iniciaMision = false;
        pane.NPCs[6] = new NPC_Alumno(45 * Panel.TILE_SIZE, 22 * Panel.TILE_SIZE, pane, Dialogo);
        pane.NPCs[6].mapIndex = 1;
        pane.NPCs[6].solido = true;
        pane.NPCs[6].iniciaMision = false;
        pane.NPCs[7] = new NPC_Alumno(78 * Panel.TILE_SIZE, 12 * Panel.TILE_SIZE, pane, Dialogo);
        pane.NPCs[7].mapIndex = 2;
        pane.NPCs[7].solido = false;
        pane.NPCs[7].iniciaMision = false;
        String[] dialogo3 = {"¿Has visto al profesor Ortega?" ,"Debe estar en esta zona, pero no lo encuentro."};
        pane.NPCs[8] = new NPC_Administradora(79 * Panel.TILE_SIZE, 38 * Panel.TILE_SIZE, pane, dialogo3);
        pane.NPCs[8].mapIndex = 3;
        pane.NPCs[8].solido = true;
        pane.NPCs[8].iniciaMision = true;
        String[] dialogoProfesor = {
            "Hola Joven",
            "¿La administradora te envió?",
            "Perfecto, dile que estaré en la sala de reuniones en unos minutos."
        };

        pane.NPCs[6] = new NPC_Profesor(6 * Panel.TILE_SIZE, 42 * Panel.TILE_SIZE, pane, dialogoProfesor);
        pane.NPCs[6].mapIndex = 3;
        pane.NPCs[6].solido = true;
        pane.NPCs[6].iniciaMision = false;
    }
}
