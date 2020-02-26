package controllers;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Controleur principal
 */
public class Controller {

    @FXML
    private ImageView starsBg1,starsBg2;

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
     * Initialisation de l'écran d'accueil
     */
    public void initialize(){
        animerBg();
    }
}
