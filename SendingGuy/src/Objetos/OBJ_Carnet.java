package Objetos;

import javafx.scene.image.Image;

public class OBJ_Carnet extends SObjetos{
    public OBJ_Carnet(){
        nombreObject = "Key";
        try{
            image = new Image(getClass().getResourceAsStream("/Items/Carnet.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        colisionObject = true;
    }
}
