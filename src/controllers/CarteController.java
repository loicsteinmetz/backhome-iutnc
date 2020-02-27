package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import models.Planete;
import utils.JavaFxAssistant;

/**
 * Controller de la carte
 */
public class CarteController {

     @FXML
     private ImageView hud;
     @FXML
     private Button prevBtn, nextBtn, goBtn;
     private Planete planeteSelectionnee;

    /**
     * Initialisation de l'animation de l'écran d'accueil
     */
    public void initialize(){
        chargeInterface();
        chargePlanetes();
    }

    @FXML
    private void chargeInterface(){
        prevBtn.setOpacity(0);
        nextBtn.setOpacity(0);
        goBtn.setOpacity(0);
        JavaFxAssistant.fadeIn(hud, 2, 0);
        JavaFxAssistant.fadeIn(prevBtn, 1, 1);
        JavaFxAssistant.fadeIn(nextBtn, 1, 1);
        JavaFxAssistant.fadeIn(goBtn, 1, 1);
    }

    @FXML
    private void chargePlanetes(){
        // todo
        // this.planeteSelectionnee = ?
    }

    @FXML
    private void planeteSuivante(){
        // todo
    }

    @FXML
    private void planetePrecedente(){
        // todo
    }

    @FXML
    private void allerPlanete(){
        // todo
    }
}
