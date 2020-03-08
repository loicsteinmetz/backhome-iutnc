package controllers;

import javafx.animation.*;
import javafx.application.Application;
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
    private AnchorPane pane;
    @FXML
    private ImageView bg;
    @FXML
    private HBox niveauVieEnnemi, niveauVieHeros, ennemiBox, herosBox, printBox, titre;
    @FXML
    private Label pvEnnemi, pvHeros, nomEnnemi, descEnnemi, print;
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
        chargeBg();
        ennemiBox.setVisible(false);
        herosBox.setVisible(false);
        printBox.setVisible(false);
        chargeCombat();
    }

    @FXML
    private void chargeCombat(){
        ennemiBox.setVisible(true);
        herosBox.setVisible(true);
        printBox.setVisible(true);
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
}
