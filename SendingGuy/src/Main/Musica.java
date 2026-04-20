package Main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author migue
 */
public class Musica {
    private MediaPlayer repro;
    public Musica(String Archivo){
        try{
            Media media = new Media(getClass().getResource(Archivo).toExternalForm());
            repro = new MediaPlayer(media);
            repro.setCycleCount(MediaPlayer.INDEFINITE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void reproducir(){
        if(repro != null){
            repro.play();
        }
    }
    public void pause(){
        if(repro != null){
            repro.pause();
        }
    }
    public void detener(){
        if(repro != null){
            repro.stop();
        }
    }
}
