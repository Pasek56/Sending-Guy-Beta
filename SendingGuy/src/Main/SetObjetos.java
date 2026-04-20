package Main;
import Objetos.*; 

public class SetObjetos {
    Panel pane;
    public SetObjetos(Panel pan) {
        this.pane = pan;
    }
    public void establecerObjeto(){
        pane.OBJ[0] = new OBJ_Carnet();
        pane.OBJ[0].PosX = 88*Panel.TILE_SIZE;
        pane.OBJ[0].PosY = 4*Panel.TILE_SIZE;
        pane.OBJ[0].mapIndex = 0;
        pane.OBJ[1] = new OBJ_Reja();
        pane.OBJ[1].PosX = 85*Panel.TILE_SIZE;
        pane.OBJ[1].PosY = 31*Panel.TILE_SIZE;
        pane.OBJ[1].mapIndex = 0;
        pane.OBJ[2] = new OBJ_Reja();
        pane.OBJ[2].PosX = 85*Panel.TILE_SIZE;
        pane.OBJ[2].PosY = 32*Panel.TILE_SIZE;
        pane.OBJ[2].mapIndex = 0;
        pane.OBJ[3] = new OBJ_Reja();
        pane.OBJ[3].PosX = 85*Panel.TILE_SIZE;
        pane.OBJ[3].PosY = 33*Panel.TILE_SIZE;
        pane.OBJ[3].mapIndex = 0;
        pane.OBJ[4] = new OBJ_Reja();
        pane.OBJ[4].PosX = 85*Panel.TILE_SIZE;
        pane.OBJ[4].PosY = 34*Panel.TILE_SIZE;
        pane.OBJ[4].mapIndex = 0;
        pane.OBJ[5] = new OBJ_Botas();
        pane.OBJ[5].PosX = 33*Panel.TILE_SIZE;
        pane.OBJ[5].PosY = 48*Panel.TILE_SIZE;
        pane.OBJ[5].mapIndex = 0;
        pane.OBJ[6] = new OBJ_Carro();
        pane.OBJ[6].PosX = 29*Panel.TILE_SIZE;
        pane.OBJ[6].PosY = 40*Panel.TILE_SIZE;
        pane.OBJ[6].mapIndex = 0;
        pane.OBJ[7] = new OBJ_Carro();
        pane.OBJ[7].PosX = 34*Panel.TILE_SIZE;
        pane.OBJ[7].PosY = 76*Panel.TILE_SIZE;
        pane.OBJ[7].mapIndex = 0;
        pane.OBJ[8] = new OBJ_Faro();
        pane.OBJ[8].PosX = 52*Panel.TILE_SIZE;
        pane.OBJ[8].PosY = 29*Panel.TILE_SIZE;
        pane.OBJ[8].mapIndex = 0;
        pane.OBJ[9] = new OBJ_Banca();
        pane.OBJ[9].PosX = 41*Panel.TILE_SIZE;
        pane.OBJ[9].PosY = 55*Panel.TILE_SIZE;
        pane.OBJ[9].mapIndex = 0;
        
        pane.OBJ[10] = new OBJ_Alumna();
        pane.OBJ[10].PosX = 15*Panel.TILE_SIZE;
        pane.OBJ[10].PosY = 15*Panel.TILE_SIZE;
        pane.OBJ[10].mapIndex = 1;
        
        pane.OBJ[11] = new OBJ_Mesa();
        pane.OBJ[11].PosX = 8 * Panel.TILE_SIZE;
        pane.OBJ[11].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[11].colisionObject = true;
        pane.OBJ[11].mapIndex = 2;

        pane.OBJ[12] = new OBJ_Mesa();
        pane.OBJ[12].PosX = 12 * Panel.TILE_SIZE;
        pane.OBJ[12].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[12].mapIndex = 2;
        pane.OBJ[12].colisionObject = true;

        pane.OBJ[13] = new OBJ_Mesa();
        pane.OBJ[13].PosX = 12 * Panel.TILE_SIZE;
        pane.OBJ[13].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[13].mapIndex = 2;
        pane.OBJ[13].colisionObject = true;

        pane.OBJ[14] = new OBJ_Mesa();
        pane.OBJ[14].PosX = 16 * Panel.TILE_SIZE;
        pane.OBJ[14].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[14].mapIndex = 2;
        pane.OBJ[14].colisionObject = true;

        pane.OBJ[15] = new OBJ_Mesa();
        pane.OBJ[15].PosX = 20 * Panel.TILE_SIZE;
        pane.OBJ[15].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[15].mapIndex = 2;
        pane.OBJ[15].colisionObject = true;

        pane.OBJ[16] = new OBJ_Mesa();
        pane.OBJ[16].PosX = 24 * Panel.TILE_SIZE;
        pane.OBJ[16].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[16].mapIndex = 2;
        pane.OBJ[16].colisionObject = true;
        
        pane.OBJ[17] = new OBJ_Alumna();
        pane.OBJ[17].PosX = 30 * Panel.TILE_SIZE;
        pane.OBJ[17].PosY = 40 * Panel.TILE_SIZE;
        pane.OBJ[17].mapIndex = 3;
        
        pane.OBJ[18] = new OBJ_Alumna();
        pane.OBJ[18].PosX = 42 * Panel.TILE_SIZE;
        pane.OBJ[18].PosY = 38 * Panel.TILE_SIZE;
        pane.OBJ[18].mapIndex = 3;
        
        pane.OBJ[19] = new OBJ_Alumna();
        pane.OBJ[19].PosX = 60 * Panel.TILE_SIZE;
        pane.OBJ[19].PosY = 36 * Panel.TILE_SIZE;
        pane.OBJ[19].mapIndex = 3;
        
        pane.OBJ[20] = new OBJ_Alumna();
        pane.OBJ[20].PosX = 15 * Panel.TILE_SIZE;
        pane.OBJ[20].PosY = 60 * Panel.TILE_SIZE;
        pane.OBJ[20].mapIndex = 3;
        
        pane.OBJ[21] = new OBJ_Alumna();
        pane.OBJ[21].PosX = 48 * Panel.TILE_SIZE;
        pane.OBJ[21].PosY = 44 * Panel.TILE_SIZE;
        pane.OBJ[21].mapIndex = 3;
        pane.OBJ[22] = new OBJ_Botas();
        pane.OBJ[22].PosX = 33 * Panel.TILE_SIZE;
        pane.OBJ[22].PosY = 48 * Panel.TILE_SIZE;
        pane.OBJ[22].mapIndex = 0;

        pane.OBJ[23] = new OBJ_Botas();
        pane.OBJ[23].PosX = 12 * Panel.TILE_SIZE;
        pane.OBJ[23].PosY = 88 * Panel.TILE_SIZE;
        pane.OBJ[23].mapIndex = 0;

        pane.OBJ[24] = new OBJ_Botas();
        pane.OBJ[24].PosX = 45 * Panel.TILE_SIZE;
        pane.OBJ[24].PosY = 60 * Panel.TILE_SIZE;
        pane.OBJ[24].mapIndex = 0;
        
        pane.OBJ[25] = new OBJ_Botas();
        pane.OBJ[25].PosX = 20 * Panel.TILE_SIZE;
        pane.OBJ[25].PosY = 22 * Panel.TILE_SIZE;
        pane.OBJ[25].mapIndex = 1;

        pane.OBJ[26] = new OBJ_Botas();
        pane.OBJ[26].PosX = 38 * Panel.TILE_SIZE;
        pane.OBJ[26].PosY = 26 * Panel.TILE_SIZE;
        pane.OBJ[26].mapIndex = 1;

        pane.OBJ[27] = new OBJ_Botas();
        pane.OBJ[27].PosX = 60 * Panel.TILE_SIZE;
        pane.OBJ[27].PosY = 28 * Panel.TILE_SIZE;
        pane.OBJ[27].mapIndex = 1;
        
        pane.OBJ[28] = new OBJ_Botas();
        pane.OBJ[28].PosX = 78 * Panel.TILE_SIZE;
        pane.OBJ[28].PosY = 12 * Panel.TILE_SIZE;
        pane.OBJ[28].mapIndex = 2;

        pane.OBJ[29] = new OBJ_Botas();
        pane.OBJ[29].PosX = 65 * Panel.TILE_SIZE;
        pane.OBJ[29].PosY = 18 * Panel.TILE_SIZE;
        pane.OBJ[29].mapIndex = 2;

        pane.OBJ[30] = new OBJ_Botas();
        pane.OBJ[30].PosX = 50 * Panel.TILE_SIZE;
        pane.OBJ[30].PosY = 30 * Panel.TILE_SIZE;
        pane.OBJ[30].mapIndex = 2;
        
        pane.OBJ[31] = new OBJ_Botas();
        pane.OBJ[31].PosX = 38 * Panel.TILE_SIZE;
        pane.OBJ[31].PosY = 42 * Panel.TILE_SIZE;
        pane.OBJ[31].mapIndex = 3;

        pane.OBJ[32] = new OBJ_Botas();
        pane.OBJ[32].PosX = 60 * Panel.TILE_SIZE;
        pane.OBJ[32].PosY = 36 * Panel.TILE_SIZE;
        pane.OBJ[32].mapIndex = 3;

        pane.OBJ[33] = new OBJ_Botas();
        pane.OBJ[33].PosX = 15 * Panel.TILE_SIZE;
        pane.OBJ[33].PosY = 60 * Panel.TILE_SIZE;
        pane.OBJ[33].mapIndex = 3;
    }
    
}
