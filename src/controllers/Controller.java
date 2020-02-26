package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.JavaFxAssistant;

/**
 * Controleur principal
 */
public class Controller {

    @FXML
    private Label texte;
    @FXML
    private Button startBtn;
    @FXML
    private ImageView vaisseau, starsBg1,starsBg2;


    /**
     * Anime l'image d'arrière-plan de l'écran d'accueil
     */
    private void animerBg() {
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
     * Initialisation de l'animation de l'écran d'accueil
     */
    public void initialize(){
        animerBg();
    }

    @FXML
    private void lancerJeu(ActionEvent event) {
        String[] scenario =
        startBtn.setVisible(false);
        texte.setVisible(false);
        JavaFxAssistant.fadeOut(vaisseau, 1.5);
    }
}
