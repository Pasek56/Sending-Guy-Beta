package Entidades;

import Main.Panel;
import java.io.InputStream;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class NPC_Administradora extends Entidades{
    private static final double WIDTH = 64;
    private static final double HEIGHT = 64;
    private final Panel panel;
    private Image ima;
    private String[] Dialogo;
    private int In = 0;
    private boolean visible = true;
    private String Direccion;
    
    public NPC_Administradora(double x, double y, Panel panel, String[] dialogo) {
        super(x, y, 0);
        this.HitBox = new Rectangle2D(8, 8, 32, 32);
        this.panel = panel;
        this.Dialogo = dialogo;
        this.HitBoxX = 32;
        this.HitBoxY = 32;
        CargaSprite("/NPC/Administradora.png");
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
    
    @Override
    public void Colision() {
        if (!panel.misionActiva && !panel.misionCompletada) {
            panel.Gui.Notificacion(getDialogoActual());
            avanzarDialogo();
            panel.Gui.Notificacion("📦 Misión iniciada: entrega urgente");
            panel.Gui.Notificacion("⏱️ Tienes 25 segundos para completarla.");
            panel.C.reiniciar();
            panel.C.iniciar(25000);
            panel.misionActiva = true;
        } else if (panel.C.estaContando()){
            panel.Gui.Notificacion("⚠️ ¡Apresúrate! El tiempo corre...");
        }else{
            panel.Gui.Notificacion(getDialogoActual());
            panel.Gui.Notificacion("Gracias por entregarlo");
        }
    }
}
