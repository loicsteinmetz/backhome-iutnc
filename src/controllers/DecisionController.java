package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Decision;

import static models.Quete.getQuete;

/**
 * Controller des décisions
 */
public class DecisionController extends Application {

    @Controller
    private static final String VIEW = "/views/Decision.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Decision.css";
    @Controller
    private Decision MODELE;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface de prise de décision
     * @param stage primaryStage
     * @throws Exception chargement de la vue
     */
    @Override
    public void start(Stage stage) throws Exception{
        //MODELE = (Decision) getQuete().getProchainEvenement();
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }
}
