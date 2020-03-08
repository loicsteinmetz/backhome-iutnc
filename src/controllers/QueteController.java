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
import models.Decision;
import models.Quete;
import models.Situation;
import utils.EffetsJavaFx;
import utils.ViewLoader;

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
        System.out.println("Passage par QueteController"); // todo : test
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
        EffetsJavaFx.defilementBg(starsBg1, starsBg2);
        nomPlanete.setText(getHeros().getLocalisation().getNom().toUpperCase());
        EffetsJavaFx.fadeIn(starsBg1, 2, 0);
        EffetsJavaFx.fadeIn(starsBg2, 2, 0);
        Transition t = EffetsJavaFx.fadeIn(nomPlanete, 2, 1.5);
        t.setOnFinished((e)->{
            startBtn.setVisible(true);
        });
    }

    @FXML
    private void lancerQuete(Event e){
        switchType(e);
    }

    private void switchType(Event e){
        switch (getHeros().getSituation()){
            case VAISSEAU:
                MODELE.nouvellePlanete(getHeros().getLocalisation());
                getHeros().setSituation(Situation.EVENEMENT);
                break;
        }
        ViewLoader vl = new ViewLoader();
        if (MODELE.getProchainEvenement() instanceof Decision){
            vl.switchTo(DecisionController.getView(), e);
        } else {
            vl.switchTo(CombatController.getView(), e);
        }
    }

}
