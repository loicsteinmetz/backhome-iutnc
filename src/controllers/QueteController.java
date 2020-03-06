package controllers;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    @Controller
    public static String getView(){
        return VIEW;
    }

    @FXML
    public void initialize(){
        MODELE = getQuete();
        animeBg();
        nomPlanete.setText(getHeros().getLocalisation().getNom());
        Transition t = EffetsJavaFx.fadeIn(starsBg1, 2, 0);
        EffetsJavaFx.fadeIn(starsBg2, 2, 0);
        EffetsJavaFx.fadeIn(nomPlanete, 2, 1);
        t.setOnFinished((e)->{
            pane.setOnMouseClicked(this::switchType);;
        });
    }

    private void switchType(Event e){
        switch (getHeros().getSituation()){
            case VAISSEAU:
                MODELE.initEvenementSuivant(getHeros().getLocalisation());
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
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.5), starsBg2);
        tt2.setFromY(0);
        tt2.setToY(600);
        tt2.setCycleCount(Timeline.INDEFINITE);
        tt2.setInterpolator(Interpolator.LINEAR);
        tt1.play();
        tt2.play();
    }
}
