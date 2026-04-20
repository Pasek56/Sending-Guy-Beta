package Mosaicos;

import javafx.scene.image.Image;


public class Mosaico{
    public Image image;
    public boolean Colision = false;
    public String Type;

    public Mosaico() {
    }

    public Mosaico(Image image, String Type) {
        this.image = image;
        this.Type = Type;
    }
}
