
package Objetos;

import javafx.scene.image.Image;

public class OBJ_Botas extends SObjetos{
    public OBJ_Botas(){
        nombreObject = "Botas";
        try{
            image = new Image(getClass().getResourceAsStream("/Items/Boots.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        colisionObject = true;
    }
}
