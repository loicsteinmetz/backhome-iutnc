package app;

import controllers.BackHomeController;
import controllers.CarteController;
import controllers.InventaireController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lib.org.json.simple.parser.ParseException;
import utils.FontsLoader;
import utils.ViewLoader;
import models.*;
import static models.Inventaire.getInventaire;

import java.io.IOException;

/**
 * Classe principale du programme
 */
public class Main extends Application {

    private static final String START_VIEW = BackHomeController.getView();

    /**
     * Génère la fenêtre du programme et l'initialise
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage){
        // configuration générale
        FontsLoader.load();
        primaryStage.setTitle("Back Home");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/img/vaisseau.png"));
        // initialisation de l'interface de lancement
        new ViewLoader().switchTo(CarteController.getView(), primaryStage);
        // création de la fenête
        primaryStage.show();
    }

    /**
     * Méthode exécutable
     * @param args arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        launch(args);
    }
}

