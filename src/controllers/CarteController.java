package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import models.Planete;
import utils.JavaFxAssistant;

import java.util.ArrayList;

import static models.Heros.getHeros;

/**
 * Controller de la carte
 */
public class CarteController {

     @FXML
     private ImageView hud;
     @FXML
     private Button prevBtn, nextBtn, goBtn;

     private Planete planeteSelectionnee;
     private int idPlaneteSelectionnee;

    /**
     * Initialisation de l'animation de l'écran d'accueil
     */
    public void initialize(){
        chargeInterface();
        planeteSelectionnee = getHeros().getLocalisation().getPlanetesVoisines().get(0);
        idPlaneteSelectionnee = 0;
        chargePlanete(planeteSelectionnee);
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
    private void chargePlanete(Planete p){
        ArrayList<Planete> planetesVoisines = getHeros().getLocalisation().getPlanetesVoisines();
        if (idPlaneteSelectionnee == 0){
            prevBtn.setDisable(true);
        } else if (idPlaneteSelectionnee == planetesVoisines.size() - 1){
            nextBtn.setDisable(true);
        }
        // todo
    }

    @FXML
    private void planeteSuivante(){
        idPlaneteSelectionnee++;
        planeteSelectionnee = getHeros().getLocalisation().getPlanetesVoisines().get(idPlaneteSelectionnee);
        chargePlanete(planeteSelectionnee);
    }

    @FXML
    private void planetePrecedente(){
        idPlaneteSelectionnee--;
        planeteSelectionnee = getHeros().getLocalisation().getPlanetesVoisines().get(idPlaneteSelectionnee);
        chargePlanete(planeteSelectionnee);
    }

    @FXML
    private void allerPlanete(){
        // todo
    }
}
