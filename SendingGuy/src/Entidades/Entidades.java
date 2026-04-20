package Entidades;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entidades {
    public int mapIndex;
    public double x, y;
    public double Speed;
    public String Direccion = "Down";
    public int numFrames = 1;
    public int CountFrames = 0;
    public Rectangle2D HitBox = new Rectangle2D(8, 8, 32, 32);
    public int HitBoxX, HitBoxY;
    public boolean colisionON = false;
    public boolean solido;
    public boolean iniciaMision = false;

    public Entidades(double x, double y, double Speed) {
        this.x = x;
        this.y = y;
        this.Speed = Speed;
    }

    public int getMapX() {
        return (int) x;
    }

    public int getMapY() {
        return (int) y;
    }

    public abstract void update();
    public abstract void draw(GraphicsContext G);
    public abstract void Colision();
}

