package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Carte;
import utils.EffetsJavaFx;

/**
 * Controller de la carte
 */
public class CarteController extends Application {

    @Controller
    private static final String VIEW = "/views/Carte.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Carte.css";
    @Controller
    private static final Carte MODELE = Carte.getCarte();

    @FXML
    private ImageView hud;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface de la carte
     * @param stage primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    /**
     * Initialisation de l'animation de l'écran d'accueil
     */
    public void initialize(){
        chargeElementsInterface();
    }

    /**
     * Charge l'apparition des éléments de l'interface
     */
    @FXML
    private void chargeElementsInterface(){
        EffetsJavaFx.fadeIn(hud, 2, 0);
    }

    @FXML
    private void allerPlanete(){
        // todo
    }
}
