package controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utils.JavaFxAssistant;
import utils.Parser;

import static models.Carte.getCarte;

/**
 * Controleur principal
 */
public class Controller {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label texte;
    @FXML
    private Button startBtn;
    @FXML
    private ImageView vaisseau, starsBg1,starsBg2;

    /**
     * Initialisation de l'animation de l'écran d'accueil
     */
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
    private void lanceJeu(ActionEvent event) throws Exception {
        startBtn.setVisible(false);
        texte.setVisible(false);
        JavaFxAssistant.fadeOut(vaisseau, 1.5, 0);
        String[] scenario = Parser.parseStrings("src/assets/config/scenario.json", "nouvellePartie");
        Transition t;
        if ((t = JavaFxAssistant.transitionTexteDeroulant(scenario, pane)) == null){
            throw new RuntimeException();
        }
        t.setOnFinished((e) -> {
            JavaFxAssistant.switchTo(getCarte(), event, 2);
        });
    }
}
