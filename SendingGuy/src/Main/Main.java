package Main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Panel Game = new Panel();
        Group root = new Group(Game);
        Scene sce = new Scene(root, Panel.SCREEN_WIDTH, Panel.SCREEN_HEIGHT);
        Controles.InputSet(sce);
        primaryStage.setScene(sce);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Sending Guy");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
