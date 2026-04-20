package Entidades;

import Main.Panel;
import java.io.InputStream;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class NPC_Vigilante extends Entidades {
    private static final double WIDTH = 64;
    private static final double HEIGHT = 64;

    private Panel panel;
    private Image ima;
    private boolean visible = true;

    public NPC_Vigilante(double x, double y, Panel panel) {
        super(x, y, 0);
        this.panel = panel;
        this.HitBox = new Rectangle2D(8, 8, 32, 32);
        this.HitBoxX = 32;
        this.HitBoxY = 32;
        CargaSprite("/NPC/Vigilante.png");
    }

    public void CargaSprite(String aw) {
        InputStream Sprite = getClass().getResourceAsStream(aw);
        if (Sprite != null) {
            ima = new Image(Sprite, WIDTH, HEIGHT, true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Archivo inexistente");
        }
    }
    @Override
    public void update() {
    }

    @Override
    public void draw(GraphicsContext G) {
        if (!visible || ima == null) return;

        double camX = x - panel.player.x + Panel.SCREEN_WIDTH / 2 - 24;
        double camY = y - panel.player.y + Panel.SCREEN_HEIGHT / 2 - 24;
        G.drawImage(ima, camX, camY, WIDTH, HEIGHT);
    }

    public void mostrar(boolean estado) {
        this.visible = estado;
    }
    private boolean saludo = false;
    @Override
    public void Colision() {
        if (Jugador.HashObject < 0) {
            panel.Gui.Notificacion("¡Sin carnet no tienes acceso!");
        } else if(Jugador.HashObject == 1 && !saludo){
            panel.Gui.Notificacion("Bienvenido");
            panel.Gui.Notificacion("Adelante Estudiante");
            saludo = true;
        }
    }
}
