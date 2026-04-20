package Entidades;
import Main.Controles;
import Main.GUISendingGuy;
import Main.Panel;
import Mision.ContraReloj;
import Mosaicos.ManejoMosaico;
import java.io.InputStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Jugador extends Entidades {
    private static final double WIDTH = 48;
    private static final double HEIGHT = 48;

    private Panel panel;
    private Image PlayerC;
    private Image Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2;
    public static int HashObject = 0;
    public int UC = 0;
    public Timeline TimeSpeed;
    public ContraReloj CR;
    public GUISendingGuy Gu;
    boolean puedeMoverse = false;

    public boolean isPuedeMoverse() {
        return puedeMoverse;
    }
    
    public Jugador(double startX, double startY, Panel panel) {
        super(startX, startY, 15);
        this.panel = panel;
        this.HitBox = new Rectangle2D(0, 0, 16, 16);
        this.HitBoxX = 32;
        this.HitBoxY = 32;
        this.CR = new ContraReloj();
        this.Gu = new GUISendingGuy(panel, CR);
        cargarImagenBase("/Jugador/Lucho_mini.png");
        cargarAnimaciones();
    }

    private void cargarImagenBase(String ruta) {
        InputStream is = getClass().getResourceAsStream(ruta);
        if (is == null) {
            System.err.println("Imagen base del jugador no encontrada: " + ruta);
            PlayerC = null;
            return;
        }
        PlayerC = new Image(is, WIDTH, HEIGHT, true, true);
    }

    private void cargarAnimaciones() {
        boolean ratio = true;
        boolean smooth = true;

        Up1 = cargar("/Jugador/Up1.png", ratio, smooth);
        Up2 = cargar("/Jugador/Up2.png", ratio, smooth);
        Down1 = cargar("/Jugador/Down1.png", ratio, smooth);
        Down2 = cargar("/Jugador/Down2.png", ratio, smooth);
        Left1 = cargar("/Jugador/Left1.png", ratio, smooth);
        Left2 = cargar("/Jugador/Left2.png", ratio, smooth);
        Right1 = cargar("/Jugador/Right1.png", ratio, smooth);
        Right2 = cargar("/Jugador/Right2.png", ratio, smooth);
    }

    private Image cargar(String ruta, boolean ratio, boolean smooth) {
        InputStream is = getClass().getResourceAsStream(ruta);
        if (is == null) {
            System.err.println("Imagen no encontrada: " + ruta);
            return null;
        }
        return new Image(is, WIDTH, HEIGHT, ratio, smooth);
    }

    @Override
    public void update() {
        boolean moved = false;
        if (Controles.upPressed) {
            Direccion = "Up";
            moved = true;
        } else if (Controles.downPressed) {
            Direccion = "Down";
            moved = true;
        } else if (Controles.leftPressed) {
            Direccion = "Left";
            moved = true;
        } else if (Controles.RightPressed) {
            Direccion = "Right";
            moved = true;
        }
        if (moved) {
            colisionON = false;
            double nextX = x;
            double nextY = y;
            switch (Direccion) {
                case "Up" -> nextY -= Speed;
                case "Down" -> nextY += Speed;
                case "Left" -> nextX -= Speed;
                case "Right" -> nextX += Speed;
            }
            Rectangle2D jugadorArea = new Rectangle2D(
                nextX + HitBox.getMinX(),
                nextY + HitBox.getMinY(),
                HitBox.getWidth(),
                HitBox.getHeight()
            );
            for (Entidades n : panel.NPCs) {
                if (n != null && n.mapIndex == ManejoMosaico.MapaActual) {
                    Rectangle2D npcArea = new Rectangle2D(
                        n.x + n.HitBox.getMinX(),
                        n.y + n.HitBox.getMinY(),
                        n.HitBox.getWidth(),
                        n.HitBox.getHeight()
                    );
                    if (npcArea.intersects(jugadorArea)) {
                        if (n.solido) {
                            colisionON = true;
                        }
                        n.Colision();
                    }
                }
            }
            panel.coli.chequeoMosaico(this);
            panel.coli.ColisionNPC(this);
            int Object = panel.coli.ColisionObjeto(this, true);
            AgarreObjeto(Object);
            if (!colisionON) {
                switch (Direccion) {
                    case "Up" -> y -= Speed;
                    case "Down" -> y += Speed;
                    case "Left" -> x -= Speed;
                    case "Right" -> x += Speed;
                }
                CountFrames++;
                if (CountFrames > 10) {
                    numFrames = (numFrames == 1) ? 2 : 1;
                    CountFrames = 0;
                }
            }
        }
    }
    public void AgarreObjeto(int i){
        long Limite;
        if(i != -1){
            String NombreObjeto = panel.OBJ[i].nombreObject;
            switch(NombreObjeto){
                case "Key" -> {
                    HashObject = 1;
                    panel.OBJ[i] = null;
                    panel.Gui.Notificacion("Conseguiste el carnet!");
                }
                case "Reja" -> {
                    if(HashObject == 1){
                        panel.OBJ[i] = null;
                    }
                }
                case "Botas" -> {
                    panel.OBJ[i] = null;
                    if(TimeSpeed != null){
                        TimeSpeed.stop();
                    }
                    this.Speed = 16.5;
                    TimeSpeed = new Timeline(new KeyFrame(Duration.seconds(7), e ->{
                        this.Speed = 8.6;
                        TimeSpeed = null;
                    }));
                    TimeSpeed.setCycleCount(1);
                    TimeSpeed.play();
                }
                case "Carro" ->{
                    panel.OBJ[i].colisionObject = true;
                    panel.Gui.Notificacion("Dato Curioso");
                    panel.Gui.Notificacion("La primera programadora fue Ada loveLace");
                }
                case "Banca" ->{
                    panel.OBJ[i].colisionObject = true;
                }
                case "Faro" ->{
                    panel.OBJ[i].colisionObject = true;
                    panel.Gui.Notificacion("Este faro esta encendido de dia");
                    panel.Gui.Notificacion("No tiene sentido");
                }
                case "Mesa" ->{
                    panel.OBJ[i].colisionObject = true;
                }
                case "Alumna" ->{
                    panel.OBJ[i].colisionObject = true;
                    panel.Gui.Notificacion("Oye, ¿como estas?, sabias que La mayoria");
                    panel.Gui.Notificacion("De estudiantes aqui son de ingenieria informatica?");
                }
                
            }
        }
    }
    @Override
    public void draw(GraphicsContext g) {
        Image image = PlayerC;

        if (Direccion != null) {
            switch (Direccion) {
                case "Up" -> image = (numFrames == 1) ? Up1 : Up2;
                case "Down" -> image = (numFrames == 1) ? Down1 : Down2;
                case "Left" -> image = (numFrames == 1) ? Left1 : Left2;
                case "Right" -> image = (numFrames == 1) ? Right1 : Right2;
            }
        }

        double camX =  (x - Panel.SCREEN_WIDTH / 2);
        double camY =  (y - Panel.SCREEN_HEIGHT / 2);
        double screenX = Panel.SCREEN_WIDTH / 2 - (int)(WIDTH / 2);
        double screenY = Panel.SCREEN_HEIGHT / 2 - (int)(HEIGHT / 2);

        if (image != null) {
            g.drawImage(image, screenX, screenY, WIDTH, HEIGHT);
        }
        panel.Gui.DibujarCronometro(g);
    }

    @Override
    public void Colision(){
    }

    public void setPuedeMoverse(boolean b) {
        this.puedeMoverse = b;
    }
}


