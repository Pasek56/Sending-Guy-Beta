package Entidades;

import Main.Panel;
import java.io.InputStream;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class NPC_Profesor extends Entidades{
    private static final double WIDTH = 64;
    private static final double HEIGHT = 64;
    private Panel panel;
    private Image ima;
    private String[] Dialogo;
    private int In = 0;
    private boolean visible = true;
    private String Direccion;
    
    public NPC_Profesor(double x, double y, Panel panel, String[] dialogo) {
        super(x, y, 4.3);
        this.HitBox = new Rectangle2D(8, 8, 32, 32);
        this.panel = panel;
        this.Dialogo = dialogo;
        this.HitBoxX = 32;
        this.HitBoxY = 32;
        CargaSprite("/NPC/Profesor.png");
    }
    public void CargaSprite(String aw){
        InputStream Sprite = getClass().getResourceAsStream(aw);
        if(Sprite != null){
            ima = new Image(Sprite, 64, 64, true, true);
        }else{
            JOptionPane.showMessageDialog(null, "Error Archivo inexistente");
        }
    }
    @Override
    public void update() {
        //establece limites laterales
        //crea un condicional para la direccion
        //contador que llegue hasta el limite
        //contador de frames
        //al llegar a 10, se reinicia todo
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
        if (In >= Dialogo.length - 1) {
            In = 0;
        }
    }
    public void mostrar(boolean estado) {
        this.visible = estado;
    }
    private boolean Pasado = false;
            
    @Override
    public void Colision() {
        if (!panel.misionCompletada && panel.misionActiva && panel.C.estaContando()) {
            panel.C.detener();
            panel.misionActiva = false;

            if (!panel.C.exceso()) {
                panel.misionCompletada = true;
                panel.Gui.Notificacion("🎉 ¡Gracias por entregarlo a tiempo!");
                panel.Gui.Notificacion("🏆 Misión completada con éxito.");
            } else {
                panel.Gui.Notificacion("⏰ Llegaste tarde...");
                panel.Gui.Notificacion("❌ Misión fallida.");
            }
        }
        panel.Gui.Notificacion(getDialogoActual());
        avanzarDialogo();
    }
}
