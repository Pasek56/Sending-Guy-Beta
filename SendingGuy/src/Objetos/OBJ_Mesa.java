package Objetos;

import javafx.scene.image.Image;

/**
 *
 * @author migue
 */
public class OBJ_Mesa extends SObjetos{
    public OBJ_Mesa(){
        nombreObject = "Reja";
        try{
            image = new Image(getClass().getResourceAsStream("/Items/Mesa.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
