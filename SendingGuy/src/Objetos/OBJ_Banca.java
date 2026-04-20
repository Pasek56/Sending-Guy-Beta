package Objetos;

import javafx.scene.image.Image;

/**
 *
 * @author migue
 */
public class OBJ_Banca extends SObjetos{
    public OBJ_Banca() {
        nombreObject = "Reja";
        try{
            image = new Image(getClass().getResourceAsStream("/Items/Banca.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
