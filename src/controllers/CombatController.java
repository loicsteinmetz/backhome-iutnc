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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.BackHome;
import models.Combat;
import models.Situation;
import utils.EffetsJavaFx;
import utils.ViewLoader;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;
import static models.Quete.getQuete;

/**
 * Controller des combats
 */
public class CombatController extends Application {

    private int pvInitEnnemi;

    @Controller
    private static final String VIEW = "/views/Combat.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Combat.css";
    @Controller
    private Combat MODELE;

    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane soinPane;
    @FXML
    private FlowPane flow;
    @FXML
    private ImageView bg;
    @FXML
    private HBox niveauVieEnnemi, niveauVieHeros, ennemiBox, herosBox, printBox, titre;
    @FXML
    private Label pvEnnemi, pvHeros, nomEnnemi, descEnnemi, print, ecran, soinTexte;
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

    /**
     * Charge l'interface de combat
     */
    @FXML
    private void chargeCombat(){
        soinPane.setDisable(true);
        Transition t = EffetsJavaFx.fadeOut(soinPane, 1, 0);
        t.setOnFinished((e)->{
            attaqueDist.setDisable(true);
            attaqueCac.setDisable(true);
            chargeEnnemi();
            chargeHeros();
            chargePrint();
            ennemiBox.setTranslateX(-800);
            herosBox.setTranslateX(800);
            EffetsJavaFx.translationX(ennemiBox, -800, 0, 0.5, 0);
            EffetsJavaFx.translationX(herosBox, 800, 0, 0.5, 0);
            EffetsJavaFx.fadeIn(printBox, 2, 0);
            flow.setVisible(true);
            Transition t2 = EffetsJavaFx.fadeIn(titre, 2, 0);
            t2.setOnFinished((e2)->{
                attaqueDist.setDisable(false);
                attaqueCac.setDisable(false);
            });
        });
    }

    /**
     * Charge l'arrière plan
     */
    @FXML
    private void chargeBg(){
        Transition t = EffetsJavaFx.fade(bg, 4, 0, 0.6);
        t.setOnFinished((e)-> EffetsJavaFx.vibrance(bg, 4, 0.6, 0.4));
    }

    /**
     * Charge les éléments relatifs à l'ennemi
     */
    @FXML
    private void chargeEnnemi(){
        int ePv = MODELE.getEnnemi().getPv();
        pvEnnemi.setText(ePv + "/" + pvInitEnnemi + " PV");
        nomEnnemi.setText(MODELE.getEnnemi().getNom());
        descEnnemi.setText(MODELE.getEnnemi().getDescription());
    }

    /**
     * Charge les éléments relatifs au héros
     */
    @FXML
    private void chargeHeros(){
        attaqueCac.setText(getInventaire().getArmeCac().getNom());
        attaqueDist.setText(getInventaire().getArmeDist().getNom());
        int hPv = getHeros().getPv();
        niveauVieHeros.setMinWidth(getHeros().getPv() * 2);
        pvHeros.setText(hPv + "/100 PV");
    }

    /**
     * Charge l'affichage des informations du combat
     */
    @FXML
    private void chargePrint(){
        String ennemi = MODELE.getEnnemi().getNom();
        print.setText(ennemi + " vous menace !\nChoisissez bien votre option d'attaque...");
    }

    /**
     * Charge le scenario et ses débouchés
     * @param event clic
     */
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
                if (getHeros().getPv() < 100 && getHeros().getLocalisation().soinDisponible()){
                    optionSoin();
                } else {
                    chargeCombat();
                }
            });
            t.play();
        }
    }

    /**
     * Lance l'attaque cac du joueur
     * @param event clic sur le bouton d'attaque cac
     */
    @FXML
    private void attaqueCac(Event event){
        int degatsH = getHeros().attaquer(MODELE.getEnnemi(), getInventaire().getArmeCac());
        lancerAttaque(degatsH, event);
    }

    /**
     * Lance l'attaque à distance du joueur
     * @param event clic sur le bouton d'attaque à distance
     */
    @FXML
    private void attaqueDist(Event event){
        int degatsH = getHeros().attaquer(MODELE.getEnnemi(), getInventaire().getArmeDist());
        lancerAttaque(degatsH, event);
    }

    /**
     * Génère la boite de dialogue relative à l'option de soin
     */
    @FXML
    private void optionSoin(){
        EffetsJavaFx.translationY(soinPane, 0, -600, 0.25, 0);
        soinTexte.setText("VOS POINTS DE VIE : " + getHeros().getPv() + "/100\n\n" +
                        "Vous avez la possibilité de régénérer vos points de vie en " +
                        "consommant une cellule d'énergie. Votre vitalité sera " +
                        "alors rétablie à son niveau maximum, mais prenez garde : " +
                        "il ne sera possible de la recharger qu'à la fin de la quête !");
    }

    /**
     * Gère le soin
     */
    @FXML
    private void soin(){
        getHeros().soin();
        getHeros().getLocalisation().consommerSoin();
        chargeCombat();
    }

    /**
     * Gère le déroulement d'une attaque du joueur
     * @param degatsH dégats infligés à l'ennemi
     * @param event clic sur un bouton d'attaque
     */
    @FXML
    private void lancerAttaque(int degatsH, Event event){
        attaqueCac.setDisable(true);
        attaqueDist.setDisable(true);
        print.setText("");
        attaqueHeros(degatsH);
        if (MODELE.getEnnemi().enVie()){
            int degatsE = MODELE.getEnnemi().attaque();
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

    /**
     * Traite la partie de l'attque pendant laquelle le héros attaque l'ennemi
     * @param degats dégats infligés à l'ennemi
     */
    @FXML
    private void attaqueHeros(int degats){
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
            int pv = Math.max(MODELE.getEnnemi().getPv(), 0);
            pvEnnemi.setText(pv + "/" + pvInitEnnemi + " PV");
            print.setText("Vous avez infligé " + degats + " points de dégats à " + MODELE.getEnnemi().getNom() + " !");
        });
        tl.play();
    }

    /**
     * Traite la partie de l'attaque pendant laquelle l'ennemi contre-attaque
     * @param degatsH degats infligés à l'ennemi
     * @param degatsE dégats infligés au héros
     */
    @FXML
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
                tl.setOnFinished((e3)->{
                    int pv = Math.max(getHeros().getPv(), 0);
                    pvHeros.setText(pv + "/100 PV");
                });
                tl.play();
            });
            tp.play();
        });
        t.play();
    }

    /**
     * Gère la mort de l'ennemi
     * @param event clic sur un bouton d'attaque
     */
    @FXML
    private void ennemiMort(Event event){
        Transition t = new PauseTransition(Duration.seconds(2));
        t.setOnFinished((e)->{
            print.setText("VOUS AVEZ VAINCU " + MODELE.getEnnemi().getNom().toUpperCase() + " !\nCliquez pour continuer.");
            finCombat(event);
        });
        t.play();
    }

    /**
     * Gère la mort du héros
     * @param event clic sur un bouton d'attaque
     */
    @FXML
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

    /**
     * Gère la fin du déroulement de l'attaque si aucun des personnage n'est mort
     * @param degatsH dégats infligés à l'ennemi
     * @param degatsE dégats infligés au héros
     */
    @FXML
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

    /**
     * Gère la fin du combat
     * @param event clic sur un bouton d'attaque
     */
    @FXML
    private void finCombat(Event event){
        ViewLoader vl = new ViewLoader();
        pane.setOnMouseClicked((e)->{
            if (getHeros().enVie()){
                if (MODELE.getIdIssue() == 0){
                    if (!BackHome.finJeu()){
                        getHeros().setSituation(Situation.VAISSEAU);
                        getHeros().getLocalisation().recompenses();
                        getInventaire().modifierCarburant(125);
                        vl.switchTo(CarteController.getView(), event);
                    } else {
                        vl.switchTo(BackHomeController.getView(), event);
                    }
                } else {
                    getQuete().prochainEvenement(MODELE.getIdIssue());
                    vl.switchTo(QueteController.getView(), event);
                }
            } else {
                if (getHeros().getLocalisation() == getCarte().getPlaneteParNom("utopia")){
                    getHeros().setSituation(Situation.DEBUT);
                    vl.switchTo(QueteController.getView(), event);
                } else {
                    getHeros().setSituation(Situation.VAISSEAU);
                    vl.switchTo(CarteController.getView(), event);
                }
            }
        });
    }
}
