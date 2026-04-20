/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import javafx.scene.image.Image;

/**
 *
 * @author migue
 */
public class OBJ_Carro extends SObjetos{

    public OBJ_Carro() {
        nombreObject = "Carro";
        try{
            image = new Image(getClass().getResourceAsStream("/Items/Carro.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
