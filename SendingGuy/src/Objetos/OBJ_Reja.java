package Objetos;

import javafx.scene.image.Image;

public class OBJ_Reja extends SObjetos{
    public OBJ_Reja() {
        nombreObject = "Reja";
        try{
            image = new Image(getClass().getResourceAsStream("/Items/Reja.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
