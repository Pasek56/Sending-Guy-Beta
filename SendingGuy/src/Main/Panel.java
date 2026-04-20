package Main;
import Entidades.Entidades;
import Entidades.Jugador;
import Mision.ContraReloj;
import Mosaicos.ManejoMosaico;
import Objetos.SObjetos;
import java.io.*;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author migue
 */
public class Panel extends Canvas{

    public enum EdoJuego{
        Inicio, Jugando, Pausa
    }
    
    EdoJuego edo = EdoJuego.Inicio;
    public static int ORIGINAL_TILE_SIZE = 48; 
    private static final int SCALE = 1;
    
    public static final int TILE_SIZE = SCALE * ORIGINAL_TILE_SIZE; 
    
    public static final int MAX_SCREEN_COL = 19;
    public static final int MAX_SCREEN_ROW = 10;

    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    public Jugador player;
    ManejoMosaico mosa;
    public Musica musi;
    public Musica musiMenu1;
    public Musica musiMenu2;
    private GraphicsContext G;
    private final AnimationTimer GameT;
    private AnimationTimer Looping;
    public SObjetos OBJ[];
    public Colision coli = new Colision(this);
    public SetObjetos SO;
    public GUISendingGuy Gui;
    public Eventos Evento;
    public ContraReloj C;
    private final long NANO_PER_SECOND = 1_000_000_000;
    private final long TARGET_TIME_NANO = NANO_PER_SECOND / 60;
    private long lastTime = 0;
    public boolean Pausa = false;
    public boolean misionActiva = false;
    public boolean misionCompletada = false;
    int Opciones = 0;
    public int Comando = 0;
    final String[] Option = {"Continuar", "Guardar", "Salir"};
    public Entidades[] NPCs;
    public setNPC SN;
    public int startX = 88*TILE_SIZE;
    public int startY = 95*TILE_SIZE;
    public Panel(){
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.NPCs = new Entidades[30];
        this.OBJ = new SObjetos[100];
        this.SO = new SetObjetos(this);
        this.SN = new setNPC(this);
        this.G = this.getGraphicsContext2D();
        this.player = new Jugador(startX,startY, this);
        this.mosa = new ManejoMosaico(this);
        this.C = new ContraReloj();   
        this.Gui = new GUISendingGuy(this, C);
        this.Evento = new Eventos(this);
        SO.establecerObjeto();
        SN.cargarNPC();
        this.musi = new Musica("/Music/MusicaFondo.wav");
        this.musiMenu1 = new Musica("/Music/TimeVersus.wav");
        this.musiMenu2 = new Musica("/Music/TimeVersus.wav");
        this.setFocusTraversable(true);
        Rectangle2D[] opcionesInicio = new Rectangle2D[] {
        new Rectangle2D(SCREEN_WIDTH / 2 - 110, SCREEN_HEIGHT / 2 + 10 - 30, 220, 40),
        new Rectangle2D(SCREEN_WIDTH / 2 - 110, SCREEN_HEIGHT / 2 + 100 - 30, 220, 40),
        new Rectangle2D(SCREEN_WIDTH / 2 - 110, SCREEN_HEIGHT / 2 + 190 - 30, 220, 40)
            };
            this.setOnMouseMoved(e -> {
                double x = e.getX();
                double y = e.getY();
                boolean sobreOpcion = false;
                for (int i = 0; i < opcionesInicio.length; i++) {
                    if (opcionesInicio[i].contains(x, y)) {
                        Comando = i;
                        sobreOpcion = true;
                        break;
                    }
                }
                this.setCursor(sobreOpcion ? Cursor.HAND : Cursor.DEFAULT);
            });
            this.setOnMouseClicked(e -> {
                double x = e.getX();
                double y = e.getY();
                for (int i = 0; i < opcionesInicio.length; i++) {
                    if (opcionesInicio[i].contains(x, y)) {
                        ejecutarOpcionInicio(i);
                        break;
                    }
                }
        });
        this.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case ENTER -> {
                    if(edo == EdoJuego.Inicio){
                        edo = EdoJuego.Jugando;
                    }
                }
                case ESCAPE -> {
                    if(edo == EdoJuego.Jugando){
                        edo = EdoJuego.Pausa;
                    }else if(edo == EdoJuego.Pausa){
                        edo = EdoJuego.Jugando;
                    }
                }
                case UP -> {
                    if(edo == EdoJuego.Pausa){
                        Opciones = (Opciones + Option.length - 1) % Option.length;
                    }
                }
                case DOWN ->{
                    if(edo == EdoJuego.Pausa){
                        Opciones = Opciones = (Opciones + 1) % Option.length;
                    }
                }
                case TAB ->{
                    Eject();
                }
            }
        if (edo == EdoJuego.Jugando) {
            switch (e.getCode()) {
                case W -> Controles.upPressed = true;
                case S -> Controles.downPressed = true;
                case A -> Controles.leftPressed = true;
                case D -> Controles.RightPressed = true;
            }
        }
    });
        GameT = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(lastTime == 0){
                    lastTime = now;
                    return;
                }
                long Elapsed = now - lastTime;
                if(Elapsed >= TARGET_TIME_NANO){
                    G.clearRect(0, 0, getWidth(), getHeight());
                    G.fillRect(0, 0, getWidth(), getHeight());
                    switch(edo){
                        case Inicio -> {
                            Inicio();
                        }
                        case Pausa -> {
                            MenuPausa();
                        }
                        case Jugando -> {
                            musi.reproducir();
                            mosa.draw(G);
                            Gui.Imprime(G);
                            for(Entidades N : NPCs){
                                if(N != null && N.mapIndex == ManejoMosaico.MapaActual){
                                    N.draw(G);
                                }
                            }
                            for(SObjetos s: OBJ){
                                if(s != null && s.mapIndex == ManejoMosaico.MapaActual){
                                    s.Draw(G, Panel.this);
                                }
                            }
                            player.update();
                            player.draw(G);
                            Evento.ChequeoEve();
                        }
                    }
                }
            }
        };
        GameT.start();
    }
    
    public void Inicio(){
        if(edo == EdoJuego.Inicio){
            Image image = new Image(getClass().getResourceAsStream("/Assets/Logo.png"));
            Image image2 = new Image(getClass().getResourceAsStream("/Assets/Lucho.png"));

            G.setFill(Color.BLUE);
            G.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            G.setFont(Font.font("TickerBit", FontWeight.BOLD, 30F));
            G.setFill(Color.GREEN);
            G.fillText("  S  E  N  D  I  N  G   G  U  Y", SCREEN_WIDTH / 2 - 214, SCREEN_HEIGHT / 2 - 149);
            G.setFill(Color.WHITE);
            G.fillText("  S  E  N  D  I  N  G   G  U  Y", SCREEN_WIDTH / 2 - 215, SCREEN_HEIGHT / 2 - 150);

            G.drawImage(image, SCREEN_WIDTH / 2 - 450, SCREEN_HEIGHT / 2 - 140);

            String[] opciones = {"CargarJuego", "Nuevo Juego", "Salir"};
            int[] posicionesY = {SCREEN_HEIGHT / 2 + 10, SCREEN_HEIGHT / 2 + 100, SCREEN_HEIGHT / 2 + 190};

            for (int i = 0; i < opciones.length; i++) {
                G.fillText(opciones[i], SCREEN_WIDTH / 2 - 110, posicionesY[i]);
                if (Comando == i) {
                    G.fillText("> ", SCREEN_WIDTH / 2 - 145, posicionesY[i]);
                }
            }
        }
        
    }
    private void ejecutarOpcionInicio(int i) {
        if(edo == EdoJuego.Inicio){
            switch (i) {
                case 0 -> {
                    Cargar("Data.dat");
                    break;
                }
                case 1 -> {
                    edo = EdoJuego.Jugando;
                    player.x = startX;
                    player.y = startY;
                    mosa.cargarDatos("/BinMaps/Map00" + (ManejoMosaico.MaxMap+1) + ".txt", ManejoMosaico.MapaActual);
                    break;
                }
                case 2 -> {
                    System.exit(0);
                    break;
                }
            }
        }
    }
    public void Pause(){
        Pausa = !Pausa;
    }
    public void MenuPausa(){
         G.setFill(Color.rgb(0, 0, 0, 0.8));
         G.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
         G.setFill(Color.WHITE);
         G.setFont(Font.font("Consolas",FontWeight.BOLD, 22));
         G.fillText("P  A  U  S  A", SCREEN_WIDTH / 2 - 80, SCREEN_HEIGHT / 2 - 60);
         for(int i = 0; i<Option.length; i++){
             String Text = (i==Opciones ? ">":"  ") + Option[i];
             G.fillText(Text, SCREEN_WIDTH / 2 - 60, SCREEN_HEIGHT / 2 - 20 + i * 30);
         }
    }   
    public void Eject(){
        switch(Opciones){
            case 0 -> Pause();
            case 1 -> guardar("Data.dat");
            case 2 -> System.exit(0);
        }
    }
    public void guardar(String Route){
        try(ObjectOutputStream Data = new ObjectOutputStream(new FileOutputStream(Route))){
            DataGame data = new DataGame();
            data.playerX = (int) player.x;
            data.playerY = (int) player.y;
            data.mapaActual = ManejoMosaico.MapaActual;
            for(SObjetos s: OBJ){
                if(s != null){
                    data.objetos.put(s.nombreObject, s.colisionObject);
                }
            }
            for(Entidades n: NPCs){
                if(n != null){
                    data.npcEstados.put(n.getClass().getSimpleName()+"_"+n.mapIndex, n.colisionON);
                }
            }
            data.tiempoJuego = (int) Gui.playTime;
            Data.writeObject(data);
        } catch (IOException ex) {
            System.getLogger(Panel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public void Cargar(String Route){
        try(ObjectInputStream Datos = new ObjectInputStream(new FileInputStream(Route))){
            DataGame Dat = (DataGame) Datos.readObject();
            player.x = Dat.playerX;
            player.y = Dat.playerY;
            ManejoMosaico.MapaActual = Dat.mapaActual;
            mosa.cargarDatos("/BinMaps/Map00" + (Dat.mapaActual+1) + ".txt", Dat.mapaActual);
            for(SObjetos O: OBJ){
                if(O != null && Dat.objetos.containsKey(O.nombreObject)){
                    O.colisionObject = Dat.objetos.get(O.nombreObject);
                }
            }
            for(Entidades P: NPCs){
                if(P!=null){
                    String C = P.getClass().getSimpleName()+"_"+P.mapIndex;
                    if(Dat.npcEstados.containsKey(C)){
                        P.colisionON = Dat.npcEstados.get(C);
                    }
                }
            }
            Gui.playTime = Dat.tiempoJuego;
            edo = EdoJuego.Jugando;
            
        } catch (IOException | ClassNotFoundException ex) {
            System.getLogger(Panel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}