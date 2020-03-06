package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Combat;
import models.Decision;
import models.Quete;
import utils.ViewLoader;

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
     * Génère l'interface de combat
     * @param stage primaryStage
     * @throws Exception chargement de la vue
     */
    @Override
    public void start(Stage stage) throws Exception{
        System.out.println("Passage par CombatController"); // todo : test
        if (MODELE != null) System.out.println("Modèle initialisé"); // todo : test
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    @FXML
    public void initialize(){
        MODELE = (Combat) getQuete().getProchainEvenement();
        pane.setOnMouseClicked((e)->{
            new ViewLoader().switchTo(CarteController.getView(), e);
        });
    }
}
