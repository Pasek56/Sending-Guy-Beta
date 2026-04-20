package Objetos;
import Main.Panel;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class SObjetos{
    public Image image;
    public String nombreObject;
    public boolean colisionObject = false;
    public int PosX, PosY;
    public Rectangle2D Papu = new Rectangle2D(8,8,32,32);
    public int HitBoxX = 0;
    public int HitBoxY = 0;
    public int mapIndex;
    
    public void Draw(GraphicsContext GD, Panel pane){
        if(image != null){
            int screenX = (int) (PosX - pane.player.x + Panel.SCREEN_WIDTH / 2);
            int screenY = (int) (PosY - pane.player.y + Panel.SCREEN_HEIGHT / 2);
            GD.drawImage(image, screenX, screenY);
        }else{
            JOptionPane.showMessageDialog(null, "Error, manejo indebido de archivos");
        }
    }
    public Rectangle2D getHitBoxObject(){
        double Scale = 1.6;
        return new Rectangle2D(
        Papu.getMaxX(),
        Papu.getMaxY(),
        Papu.getWidth(),
        Papu.getHeight()
        );
    }
}
