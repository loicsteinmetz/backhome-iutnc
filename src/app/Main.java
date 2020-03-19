package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.FontsLoader;
import views.View;


/**
 * Classe principale du programme
 */
public class Main extends Application {

    /**
     * Génère la fenêtre du programme et l'initialise
     * @param primaryStage stage initial
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // configuration générale
        FontsLoader.load();
        primaryStage.setTitle("Back Home");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/img/vaisseau.png"));
        View.setStage(primaryStage);
        // initialisation de l'interface de lancement
        new View().backHomeView();
        // création de la fenête
        primaryStage.show();
    }

    /**
     * Méthode exécutable
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
}