package controllers;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.BackHome;
import utils.EffetsJavaFx;
import utils.ViewLoader;

/**
 * Controleur principal
 */
public class BackHomeController extends Application {

    @Controller
    private static final String VIEW = "/views/BackHome.fxml";
    @Controller
    private static final String STYLE = "/assets/css/BackHome.css";
    @Controller
    private static BackHome MODELE;

    @FXML
    private AnchorPane pane;
    @FXML
    private Label titre;
    @FXML
    private Button startBtn;
    @FXML
    private ImageView vaisseau, starsBg1,starsBg2;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface d'accueil
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
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){
        MODELE = new BackHome();
        EffetsJavaFx.defilementBg(starsBg1, starsBg2);
    }

    /**
     * Lance le jeu lors du clic sur le boutton dédié
     */
    @FXML
    private void bouttonJouer(Event event) {
        startBtn.setVisible(false);
        titre.setVisible(false);
        Transition fadeOut = EffetsJavaFx.fadeOut(vaisseau, 1.5, 0);
        fadeOut.setOnFinished((e) -> {
            Label label = new Label(MODELE.getScenario()[0]);
            label.setId("ecran");
            label.setOpacity(0);
            label.setUserData(0);
            label.setOnMouseClicked(this::passeTexte);
            pane.getChildren().add(label);
            EffetsJavaFx.fadeIn(label, 2.0, 1.5);
        });
    }

    /**
     * Saute la page de texte pour aller à la prochaîne ou vers la prochaîne vue
     * @param event clic
     */
    @FXML
    private void passeTexte(Event event){
        Label label = (Label) event.getSource();
        int index = (int) label.getUserData() + 1;
        if (index < MODELE.getScenario().length){
            label.setOpacity(0);
            label.setText(MODELE.getScenario()[index]);
            EffetsJavaFx.fadeIn(label, 2, 0);
            label.setUserData(index);
        } else if (index == MODELE.getScenario().length) {
            label.setDisable(true);
            label.setVisible(false);
            new ViewLoader().switchTo(QueteController.getView(), event, 2);
        }
    }
}
