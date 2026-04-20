package Main;

import static javafx.scene.input.KeyCode.*;

public class Controles {
    public static boolean upPressed = false;
    public static boolean downPressed= false;
    public static boolean leftPressed = false;
    public static boolean RightPressed= false;
    public static void InputSet(javafx.scene.Scene Sce){
        Sce.setOnKeyPressed(event -> {
            
            switch(event.getCode()){
                case W -> upPressed = true;
                case S -> downPressed = true;
                case A -> leftPressed = true;
                case D -> RightPressed = true;
            }
        });
        Sce.setOnKeyReleased(event -> {
            switch(event.getCode()){
                case W -> upPressed = false;
                case S -> downPressed = false;
                case A -> leftPressed = false;
                case D -> RightPressed = false;
            }
        });
    }
}
