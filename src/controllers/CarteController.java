package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Carte;
import models.Planete;
import utils.EffetsJavaFx;

import static models.Heros.getHeros;

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
    @FXML
    private FlowPane flow;
    @FXML
    private Label loc;
    @FXML
    private Label dest;

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
        chargePlanetes();
        chargeInfos();
    }

    /**
     * Charge l'apparition des éléments de l'interface
     */
    @FXML
    private void chargeElementsInterface(){
        EffetsJavaFx.fadeIn(hud, 2, 0);
    }

    @FXML
    private void chargePlanetes(){
        for (Planete p : getHeros().getLocalisation().getPlanetesVoisines()){
            HBox box = new HBox();
            box.setOpacity(0);
            box.setId("planete");
            Label nom = new Label();
            nom.setText(p.getNom());
            box.getChildren().add(nom);
            flow.getChildren().add(box);
            EffetsJavaFx.fadeIn(box, 2, 0);
        }
    }

    @FXML
    private void chargeInfos(){
        loc.setText("LOCALISATION : " + getHeros().getLocalisation().getNom());
        EffetsJavaFx.fadeIn(dest, 2, 0);
        EffetsJavaFx.fadeIn(loc, 2, 0);
    }

    @FXML
    private void allerPlanete(){
        // todo
    }
}
