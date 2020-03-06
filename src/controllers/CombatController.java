package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Combat;
import models.Decision;

import static models.Quete.getQuete;

/**
 * Controller des combats
 */
public class CombatController extends Application {

    @Controller
    private static final String VIEW = "/views/Combat.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Combat.css";
    @Controller
    private Combat MODELE;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface de combat
     * @param stage primaryStage
     * @throws Exception chargement de la vue
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    /**
     * Initialise le controller :
     * Charge le modèle
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        MODELE = (Combat) getQuete().getProchainEvenement()
        super.init();
    }
}
