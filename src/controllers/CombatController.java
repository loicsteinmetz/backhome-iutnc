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
import models.Situation;
import utils.EffetsJavaFx;
import utils.ViewLoader;

import java.sql.Time;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;
import static models.Quete.getQuete;

/**
 * Controller des combats
 */
public class CombatController extends Application {

    private static int pvInitEnnemi;

    @Controller
    private static final String VIEW = "/views/Combat.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Combat.css";
    @Controller
    private Combat MODELE;

    @FXML
    private AnchorPane pane;
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
        pvInitEnnemi = MODELE.getEnnemi().getPv();
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
        attaqueDist.setDisable(true);
        attaqueCac.setDisable(true);
        chargeEnnemi();
        chargeHeros();
        chargePrint();
        EffetsJavaFx.translationX(ennemiBox, -800, 0, 0.5, 0);
        EffetsJavaFx.translationX(herosBox, 800, 0, 0.5, 0);
        EffetsJavaFx.fadeIn(printBox, 2, 0);
        Transition t = EffetsJavaFx.fadeIn(titre, 2, 0);
        t.setOnFinished((e)->{
            attaqueDist.setDisable(false);
            attaqueCac.setDisable(false);
        });
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
        niveauVieHeros.setMinWidth(getHeros().getPv() * 2);
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

    @FXML
    private void attaqueCac(Event event){
        int degatsH = getHeros().attaquer(MODELE.getEnnemi(), getInventaire().getArmeCac());
        lancerAttaque(degatsH, event);
    }

    @FXML
    private void attaqueDist(Event event){
        int degatsH = getHeros().attaquer(MODELE.getEnnemi(), getInventaire().getArmeDist());
        lancerAttaque(degatsH, event);
    }

    private void lancerAttaque(int degatsH, Event event){
        attaqueCac.setDisable(true);
        attaqueDist.setDisable(true);
        print.setText("");
        Timeline t = attaqueHeros(degatsH);
        if (MODELE.getEnnemi().enVie()){
            int degatsE = MODELE.getEnnemi().attaque(getHeros());
            attaqueEnnemi(degatsH, degatsE);
            if (getHeros().enVie()){
                finAttaque(degatsH, degatsE);
            } else {
                herosMort(event);
            }
        } else {
            ennemiMort(event);
        }
    }

    private Timeline attaqueHeros(int degats){
        Timeline tl = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(niveauVieEnnemi.minWidthProperty(), niveauVieEnnemi.getMinWidth())),
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(
                                niveauVieEnnemi.minWidthProperty(),
                                (double)MODELE.getEnnemi().getPv() / pvInitEnnemi * 300))
        );
        tl.setOnFinished((e)->{
            print.setText("Vous avez infligé " + degats + " points de dégats à " + MODELE.getEnnemi().getNom() + " !");
        });
        tl.play();
        return tl;
    }

    private void attaqueEnnemi(int degatsH, int degatsE){
        Transition t = new PauseTransition(Duration.seconds(1.5));
        t.setOnFinished((e)->{
            print.setText("Vous avez infligé " + degatsH + " points de dégats à " + MODELE.getEnnemi().getNom() + " !\n" +
                    "L'ennemi contre-attaque avec " + MODELE.getEnnemi().getArme().getNom() + " et vous inflige à son tour " + degatsE + " points de dégats...");
            Transition tp = new PauseTransition(Duration.seconds(1));
            Timeline tl = new Timeline(
                    new KeyFrame(
                            Duration.ZERO,
                            new KeyValue(niveauVieHeros.minWidthProperty(), niveauVieHeros.getMinWidth())),
                    new KeyFrame(
                            Duration.seconds(0.5),
                            new KeyValue(
                                    niveauVieHeros.minWidthProperty(),
                                    getHeros().getPv() * 2))
            );
            tp.setOnFinished((e2)->{
                tl.play();
            });
            tp.play();
        });
        t.play();
    }

    private void ennemiMort(Event event){
        Transition t = new PauseTransition(Duration.seconds(2));
        t.setOnFinished((e)->{
            print.setText("VOUS AVEZ VAINCU " + MODELE.getEnnemi().getNom().toUpperCase() + " !\nCliquez pour continuer.");
            finCombat(event);
        });
        t.play();
    }

    private void herosMort(Event event){
        Transition t = new PauseTransition(Duration.seconds(4));
        t.setOnFinished((e)->{
            if (getHeros().getLocalisation() == getCarte().getPlaneteParNom("utopia")){
                print.setText(MODELE.getEnnemi().getNom().toUpperCase() + " VOUS A VAINCU...\nCliquez pour recommencer la quête.");
            } else {
                print.setText(MODELE.getEnnemi().getNom().toUpperCase() + " VOUS A VAINCU...\nVous reviendrez...\nCliquez pour revenir au vaisseau.");
            }
            finCombat(event);
        });
        t.play();
    }

    private void finAttaque(int degatsH, int degatsE){
        Transition t = new PauseTransition(Duration.seconds(3.5));
        t.setOnFinished((e)->{
            print.setText("Vous avez infligé " + degatsH + " points de dégats à " + MODELE.getEnnemi().getNom() + " !\n" +
                    "L'ennemi contre-attaque avec " + MODELE.getEnnemi().getArme().getNom() + " et vous inflige à son tour " + degatsE + " points de dégats...\n" +
                    "C'est votre tour d'attaquer.");
            attaqueCac.setDisable(false);
            attaqueDist.setDisable(false);
        });
        t.play();
    }

    private void finCombat(Event event){
        ViewLoader vl = new ViewLoader();
        pane.setOnMouseClicked((e)->{
            if (getHeros().getLocalisation() == getCarte().getPlaneteParNom("utopia")){
                getHeros().setSituation(Situation.DEBUT);
                vl.switchTo(QueteController.getView(), event);
            } else {
                getHeros().setSituation(Situation.VAISSEAU);
                vl.switchTo(CarteController.getView(), event);
            }
        });
    }
}
