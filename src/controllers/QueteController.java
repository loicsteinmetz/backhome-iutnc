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
    private AnchorPane pane;
    @FXML
    private Label nomPlanete;
    @FXML
    private Button startBtn;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    public void initialize(){
        MODELE = getQuete();
        startBtn.setVisible(false);
        EffetsJavaFx.defilementBg(starsBg1, starsBg2);
        nomPlanete.setText(getHeros().getLocalisation().getNom().toUpperCase());
        EffetsJavaFx.fadeIn(starsBg1, 2, 0);
        EffetsJavaFx.fadeIn(starsBg2, 2, 0);
        Transition t = EffetsJavaFx.fadeIn(nomPlanete, 2, 1.5);
        t.setOnFinished((e) -> startBtn.setVisible(true));
        switch (getHeros().getSituation()){
            case DEBUT:
                MODELE.nouvellePlanete(getCarte().getPlaneteParNom("utopia"));
                break;
            case VAISSEAU:
                MODELE.nouvellePlanete(getHeros().getLocalisation());
                break;
        }
        getHeros().setSituation(Situation.EVENEMENT);
    }

    @FXML
    private void next(){
        new View().queteView();
    }

}
