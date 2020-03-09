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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Decision;
import models.Quete;
import utils.EffetsJavaFx;
import utils.ViewLoader;

import static models.Quete.getQuete;

/**
 * Controller des décisions
 */
public class DecisionController extends Application {

    @Controller
    private static final String VIEW = "/views/Decision.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Decision.css";
    @Controller
    private Decision MODELE;

    @FXML
    private ImageView bg;
    @FXML
    private Button issueA, issueB;
    @FXML
    private Label ecran;
    @FXML
    private HBox btnBox;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface de prise de décision
     * @param stage primaryStage
     * @throws Exception chargement de la vue
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    public void initialize(){
        MODELE = (Decision) getQuete().getProchainEvenement();
        Transition t = EffetsJavaFx.fade(bg, 4, 0, 0.15);
        t.setOnFinished((e)->{
            EffetsJavaFx.vibrance(bg, 6, 0.15, 0.05);
        });
        ecran.setText(MODELE.getScenario().get(0));
        ecran.setUserData(0);
        ecran.setOnMouseClicked(this::passeTexte);
        EffetsJavaFx.fadeIn(ecran, 2.0, 1);
        issueA.setOpacity(0);
        issueB.setOpacity(0);
    }

    @FXML
    private void passeTexte(Event event){
        int index = (int) ecran.getUserData() + 1;
        ecran.setOpacity(0);
        ecran.setText(MODELE.getScenario().get(index));
        EffetsJavaFx.fadeIn(ecran, 2, 0);
        ecran.setUserData(index);
        if (index == MODELE.getScenario().size() - 1){
            if (MODELE.getIdIssueA() != -1 && MODELE.getIdIssueB() != -1){
                ecran.setDisable(true);
                EffetsJavaFx.fadeIn(issueA, 2, 0);
                EffetsJavaFx.fadeIn(issueB, 2, 0);
                btnBox.setLayoutY(450);
                issueA.setText(MODELE.getOptionA());
                issueB.setText(MODELE.getOptionB());
            } else if (MODELE.getIdIssueA() == 0){
                ecran.setDisable(true);
                EffetsJavaFx.fadeIn(issueA, 2, 0);
                btnBox.getChildren().remove(1);
                btnBox.setLayoutY(450);
                issueA.setText("Aller au vaisseau");
            } else {
                ecran.setOnMouseClicked((e)->{
                    new ViewLoader().switchTo(QueteController.getView(), event);
                });
            }
        }
    }

    @FXML
    private void issueA(Event e){
        ViewLoader vl = new ViewLoader();
        if (MODELE.getIdIssueA() == 0){
            vl.switchTo(CarteController.getView(), e);
        } else {
            getQuete().prochainEvenement(MODELE.getIdIssueA());
            new ViewLoader().switchTo(QueteController.getView(), e);
        }
    }

    @FXML
    private void issueB(Event e){
        getQuete().prochainEvenement(MODELE.getIdIssueB());
        new ViewLoader().switchTo(QueteController.getView(), e);
    }
}
