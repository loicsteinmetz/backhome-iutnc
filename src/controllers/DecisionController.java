package controllers;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Decision;
import models.Situation;
import utils.ViewLoader;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
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

    @FXML
    private AnchorPane pane;

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
        System.out.println("Passage par DecisionController"); // todo : test
        if (MODELE != null) System.out.println("Modèle initialisé"); // todo : test
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    @FXML
    public void initialize(){
        MODELE = (Decision) getQuete().getProchainEvenement();
        pane.setOnMouseClicked((e)->{
            new ViewLoader().switchTo(CarteController.getView(), e);
        });
    }
}
