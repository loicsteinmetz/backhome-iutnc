package controllers;

import javafx.animation.*;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Combat;
import utils.EffetsJavaFx;
import utils.ViewLoader;

import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;
import static models.Quete.getQuete;

/**
 * Controller des combats
 */
public class CombatController extends Application {

    @Controller
    private static final String VIEW = "/views/Combat.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Combat.css";
    @Controller
    private Combat MODELE;

    @FXML
    private FlowPane flow;
    @FXML
    private ImageView bg;
    @FXML
    private HBox niveauVieEnnemi, niveauVieHeros, ennemiBox, herosBox, printBox, titre;
    @FXML
    private Label pvEnnemi, pvHeros, nomEnnemi, descEnnemi, print, ecran;
    @FXML
    private Button attaqueCac, attaqueDist;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface de combat
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
    private void initialize(){
        MODELE = (Combat) getQuete().getProchainEvenement();
        flow.setVisible(false);
        chargeBg();
        ecran.setText(MODELE.getScenario().get(0));
        ecran.setUserData(0);
        ecran.setOnMouseClicked(this::passeTexte);
        EffetsJavaFx.fadeIn(ecran, 2.0, 1);
    }

    @FXML
    private void chargeCombat(){
        flow.setVisible(true);
        chargeEnnemi();
        chargeHeros();
        chargePrint();
        EffetsJavaFx.translationX(ennemiBox, -800, 0, 0.5, 0);
        EffetsJavaFx.translationX(herosBox, 800, 0, 0.5, 0);
        EffetsJavaFx.fadeIn(printBox, 2, 0);
        EffetsJavaFx.fadeIn(titre, 2, 0);
    }

    @FXML
    private void chargeBg(){
        Transition t = EffetsJavaFx.fade(bg, 4, 0, 0.6);
        t.setOnFinished((e)->{
            EffetsJavaFx.vibrance(bg, 4, 0.6, 0.4);
        });
    }

    @FXML
    private void chargeEnnemi(){
        int ePv = MODELE.getEnnemi().getPv();
        pvEnnemi.setText(ePv + "/" + ePv + " PV");
        nomEnnemi.setText(MODELE.getEnnemi().getNom());
        descEnnemi.setText(MODELE.getEnnemi().getDescription());
    }

    @FXML
    private void chargeHeros(){
        attaqueCac.setText(getInventaire().getArmeCac().getNom());
        attaqueDist.setText(getInventaire().getArmeDist().getNom());
        int hPv = getHeros().getPv();
        pvHeros.setText(hPv + "/100 PV");
    }

    @FXML
    private void chargePrint(){
        String ennemi = MODELE.getEnnemi().getNom();
        print.setText(ennemi + " vous menace !\nChoisissez bien votre option d'attaque...");
    }

    @FXML
    private void passeTexte(Event event){
        Label label = (Label) event.getSource();
        int index = (int) label.getUserData() + 1;
        if (index < MODELE.getScenario().size()){
            label.setOpacity(0);
            label.setText(MODELE.getScenario().get(index));
            EffetsJavaFx.fadeIn(label, 2, 0);
            label.setUserData(index);
        } else if (index == MODELE.getScenario().size()) {
            label.setDisable(true);
            label.setVisible(false);
            Transition t = new PauseTransition(Duration.seconds(1));
            t.setOnFinished((e)->{
                chargeCombat();
            });
            t.play();
        }
    }
}
