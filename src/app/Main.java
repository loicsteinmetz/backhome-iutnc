package app;

import controllers.BackHomeController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.FontsLoader;
import utils.ViewLoader;


/**
 * Classe principale du programme
 */
public class Main extends Application {

    private static final String START_VIEW = BackHomeController.getView();

    /**
     * Génère la fenêtre du programme et l'initialise
     * @param primaryStage stage initial
     */
    @Override
    public void start(Stage primaryStage){
        // configuration générale
        FontsLoader.load();
        primaryStage.setTitle("Back Home");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/img/vaisseau.png"));
        // initialisation de l'interface de lancement
        new ViewLoader().switchTo(START_VIEW, primaryStage);
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