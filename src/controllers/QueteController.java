package controllers;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Quete;
import models.Situation;
import utils.EffetsJavaFx;
import views.View;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Quete.getQuete;

/**
 * Controller de la quête
 */
public class QueteController {

    @Controller
    private Quete MODELE;

    @FXML
    private ImageView starsBg1,starsBg2;
    @FXML
    private Label nomPlanete;
    @FXML
    private Button startBtn;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){

        // initialise le modèle
        MODELE = getQuete();

        // affichage de l'arrière-plan, du nom de la planète et d'un bouton 'commencer'
        startBtn.setVisible(false);
        EffetsJavaFx.defilementBg(starsBg1, starsBg2);
        nomPlanete.setText(getHeros().getLocalisation().getNom().toUpperCase());
        EffetsJavaFx.fadeIn(starsBg1, 2, 0);
        EffetsJavaFx.fadeIn(starsBg2, 2, 0);
        Transition t = EffetsJavaFx.fadeIn(nomPlanete, 2, 1.5);
        t.setOnFinished((e) -> startBtn.setVisible(true));

        // initialisation du premier événement de la planète
        switch (getHeros().getSituation()){
            // si début de partie
            case DEBUT:
                MODELE.nouvellePlanete(getCarte().getPlaneteParNom("utopia"));
                break;
            // si cours de partie
            case VAISSEAU:
                MODELE.nouvellePlanete(getHeros().getLocalisation());
                break;
        }

        // mise à jour de la situation du héros
        getHeros().setSituation(Situation.EVENEMENT);
    }

    @FXML
    private void next(){
        new View().queteView();
    }

}
