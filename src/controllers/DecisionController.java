package controllers;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Decision;
import utils.EffetsJavaFx;
import utils.ViewLoader;

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
    @FXML
    private ImageView bg;

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

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    public void initialize(){
        MODELE = (Decision) getQuete().getProchainEvenement();
        Transition t = EffetsJavaFx.fade(bg, 4, 0, 0.15);
        t.setOnFinished((e)->{
            EffetsJavaFx.vibrance(bg, 6, 0.15, 0.05);
        });
        pane.setOnMouseClicked((e)->{
            new ViewLoader().switchTo(CarteController.getView(), e);
        });
    }
}
