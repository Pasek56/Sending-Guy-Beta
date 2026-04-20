package Entidades;

import Main.Panel;
import java.io.InputStream;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class NPC_Alumno extends Entidades {
    private static final double WIDTH = 64;
    private static final double HEIGHT = 64;
    private final Panel panel;
    private Image ima;
    private String[] Dialogo;
    private int In = 0;
    private boolean visible = true;
    private boolean hablando = false;
    private long tiempoInicio;
    private final int duracionBloqueo = 2000;

    public NPC_Alumno(double x, double y, Panel panel, String[] dialogo) {
        super(x, y, 0);
        this.panel = panel;
        this.Dialogo = dialogo;
        this.HitBox = new Rectangle2D(8, 8, 32, 32);
        this.HitBoxX = 32;
        this.HitBoxY = 32;
        CargaSprite("/NPC/Alumno.png");
    }

    public void CargaSprite(String ruta) {
        InputStream sprite = getClass().getResourceAsStream(ruta);
        if (sprite != null) {
            ima = new Image(sprite, 64, 64, true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Archivo inexistente");
        }
    }

    @Override
    public void update() {
        if (hablando) {
            long ahora = System.currentTimeMillis();
            if (ahora - tiempoInicio >= duracionBloqueo) {
                hablando = false;
                panel.player.setPuedeMoverse(true);
            }
        }
    }

    @Override
    public void draw(GraphicsContext G) {
        if (!visible || ima == null) return;
        double camX = x - panel.player.x + Panel.SCREEN_WIDTH / 2 - 24;
        double camY = y - panel.player.y + Panel.SCREEN_HEIGHT / 2 - 24;
        G.drawImage(ima, camX, camY, WIDTH, HEIGHT);
    }

    public String getDialogoActual() {
        if (In < Dialogo.length) {
            return Dialogo[In];
        }
        return "";
    }

    public void avanzarDialogo() {
        In++;
        if (In >= Dialogo.length) {
            In = 0;
        }
    }

    public void mostrar(boolean estado) {
        this.visible = estado;
    }

    @Override
    public void Colision() {
        tiempoInicio = System.currentTimeMillis();
        panel.Gui.Notificacion(getDialogoActual());
        avanzarDialogo();
    }
}
