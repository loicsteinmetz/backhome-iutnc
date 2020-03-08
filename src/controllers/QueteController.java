package controllers;

import com.sun.javafx.event.RedirectedEvent;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Combat;
import models.Decision;
import models.Quete;
import models.Situation;
import utils.EffetsJavaFx;
import utils.ViewLoader;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Quete.getQuete;

/**
 * Controller de la quête
 */
public class QueteController extends Application {

    @Controller
    private static final String VIEW = "/views/Quete.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Quete.css";
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
     * Génère l'interface de quête
     * @param stage primaryStage
     * @throws Exception chargement de la vue
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    public void initialize(){
        MODELE = getQuete();
        startBtn.setVisible(false);
        if (getHeros().getSituation() != Situation.EVENEMENT){
            EffetsJavaFx.defilementBg(starsBg1, starsBg2);
            nomPlanete.setText(getHeros().getLocalisation().getNom().toUpperCase());
            EffetsJavaFx.fadeIn(starsBg1, 2, 0);
            EffetsJavaFx.fadeIn(starsBg2, 2, 0);
            Transition t = EffetsJavaFx.fadeIn(nomPlanete, 2, 1.5);
            t.setOnFinished((e)->{
                startBtn.setVisible(true);
            });
        } else {
            nomPlanete.setText("Cliquer pour continuer...");
            EffetsJavaFx.fadeIn(nomPlanete, 0.5, 0.5);
            pane.setOnMouseClicked(this::switchType);
        }
    }

    @FXML
    private void lancerQuete(Event e){
        switchType(e);
    }

    private void switchType(Event e){
        switch (getHeros().getSituation()){
            case DEBUT:
                MODELE.nouvellePlanete(getCarte().getPlaneteParNom("utopia"));
                break;
            case VAISSEAU:
                MODELE.nouvellePlanete(getHeros().getLocalisation());
                break;
        }
        getHeros().setSituation(Situation.EVENEMENT);
        ViewLoader vl = new ViewLoader();
        if (MODELE.getProchainEvenement() instanceof Decision){
            vl.switchTo(DecisionController.getView(), e);
        } else {
            vl.switchTo(CombatController.getView(), e);
        }
    }

}
