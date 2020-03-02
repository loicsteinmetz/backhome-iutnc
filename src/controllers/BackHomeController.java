package controllers;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;
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
    private static final BackHome MODELE = new BackHome();

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
     * Initialisation de l'écran d'accueil
     */
    @FXML
    public void initialize(){
        animeBg();
    }

    /**
     * Anime l'image d'arrière-plan de l'écran d'accueil
     */
    @FXML
    private void animeBg() {
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1.5), starsBg1);
        tt1.setFromY(-600);
        tt1.setToY(0);
        tt1.setCycleCount( Timeline.INDEFINITE);
        tt1.setInterpolator(Interpolator.LINEAR);
        tt1.play();
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.5), starsBg2);
        tt2.setFromY(0);
        tt2.setToY(600);
        tt2.setCycleCount(Timeline.INDEFINITE);
        tt2.setInterpolator(Interpolator.LINEAR);
        tt2.play();
    }

    /**
     * Lance le jeu lors du clic sur le boutton dédié
     * @param event clic sur le boutton de lancement
     * @throws Exception
     */
    @FXML
    private void bouttonJouer(ActionEvent event) throws Exception {
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
            EffetsJavaFx.fadeIn(label, 2.0, 0);
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
            new ViewLoader().switchTo(CarteController.getView(), event, 2);
        }
    }
}
