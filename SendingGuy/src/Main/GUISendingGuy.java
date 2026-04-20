package Main;

import Entidades.Entidades;
import Mision.ContraReloj;
import Objetos.OBJ_Carnet;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GUISendingGuy {
    private final boolean mostrarCronometro = false;
    private final int contadorCronometro = 0;
    private final double cronX = Panel.SCREEN_WIDTH - 220;
    private final double cronY = 20;
    private final double cronWidth = 180;
    private final double cronHeight = 50;
    private final double dialogX = 80;
    private final double dialogY = Panel.SCREEN_HEIGHT - 120;
    private final double dialogWidth = Panel.SCREEN_WIDTH - 160;
    private final double dialogHeight = 80;
    private final Color fondoDialogo = Color.rgb(20, 20, 20, 0.8);
    private final Color bordeDialogo = Color.ANTIQUEWHITE;
    private final Font fontDialogo = Font.font("Arial", FontWeight.BOLD, 18);
    private int duracion = 120;
    Panel pan;
    Font font;
    Image ima;
    String Mensaje = "";
    boolean Men = false;
    OBJ_Carnet CAR;
    public ContraReloj CR;
    double playTime, playTime2;
    int MessageCounter = 0;
    private String MensajeCompleto = "";
    private String MensajeVisible = "";
    private int letraIndex = 0;
    private int velocidadTexto = 1;
    private LinkedList<String> Mensajes;
    private boolean mostrandoMensaje = false;
    DecimalFormat DF = new DecimalFormat("#0.000");
    private Runnable eventoPendiente;
    public GUISendingGuy(Panel pan, ContraReloj CR){
        this.CR = CR;
        this.pan = pan;
        font = Font.font("Arial",18);
        this.CAR = new OBJ_Carnet();
        this.ima = CAR.image;
        this.Mensajes = new LinkedList<>();
    }
    public void DibujarCronometro(GraphicsContext G){
        if (!CR.estaContando()) return;

        G.setFill(Color.rgb(0, 0, 0, 0.7));
        G.fillRoundRect(cronX, cronY, cronWidth, cronHeight, 15, 15);

        G.setStroke(Color.LIGHTGREEN);
        G.setLineWidth(2);
        G.strokeRoundRect(cronX, cronY, cronWidth, cronHeight, 15, 15);

        G.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
        G.setFill(Color.ANTIQUEWHITE);
        G.fillText("⏱ " + CR.info(), cronX + 15, cronY + 30);
    }
    public void Notificacion(String Texto){
        if (!Mensajes.contains(Texto)) {
        Mensajes.add(Texto);
        }
        if (!mostrandoMensaje) {
            SiguienteMensaje();
        }
    }
    public void SiguienteMensaje(){
        if(Mensajes.isEmpty()){
            mostrandoMensaje = false;
            Men = false;
            if (eventoPendiente != null) {
                eventoPendiente.run();
                eventoPendiente = null;
            }
            return;
        }
        MensajeCompleto = Mensajes.poll();
        MensajeVisible = "";
        letraIndex = 0;
        Men = true;
        MessageCounter = 0;
        mostrandoMensaje = true;
    }
    public void Imprime(GraphicsContext G){
        G.setFont(font);
        G.setFill(Color.ANTIQUEWHITE);
        G.drawImage(ima, Panel.ORIGINAL_TILE_SIZE/2, Panel.TILE_SIZE/2, Panel.TILE_SIZE, Panel.TILE_SIZE);
        G.fillText("Carnet: "+pan.player.HashObject, 75, 56);
        if(!pan.Pausa){
           playTime +=(double)1/60;  
        }
        G.fillText("Tiempo: "+DF.format(playTime), 500, 56);
        if(Men){
            G.setFill(Color.rgb(30, 30, 30, 0.85));
            G.fillRoundRect(dialogX, dialogY, dialogWidth, dialogHeight, 20, 20);

            G.setStroke(Color.ANTIQUEWHITE);
            G.setLineWidth(2);
            G.strokeRoundRect(dialogX, dialogY, dialogWidth, dialogHeight, 20, 20);

            if (letraIndex < MensajeCompleto.length() && MessageCounter % velocidadTexto == 0) {
                MensajeVisible += MensajeCompleto.charAt(letraIndex);
                letraIndex++;
            }

            G.setFill(Color.ANTIQUEWHITE);
            G.setFont(fontDialogo);
            G.fillText(MensajeVisible, dialogX + 20, dialogY + 30);

            MessageCounter++;
            if (MessageCounter > duracion) {
                SiguienteMensaje();
            }
        }
        DibujarCronometro(G);
    }
}
