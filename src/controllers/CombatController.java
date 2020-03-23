package controllers;

import javafx.animation.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.BackHome;
import models.Combat;
import models.Situation;
import utils.EffetsJavaFx;
import views.View;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;
import static models.Quete.getQuete;

/**
 * Controller des combats
 */
public class CombatController {

    private int pvInitEnnemi;

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
    private HBox niveauVieEnnemi, niveauVieHeros, ennemiBox,
            herosBox, printBox, titre;
    @FXML
    private Label pvEnnemi, pvHeros, nomEnnemi, descEnnemi,
            print, ecran, soinTexte, cliquez;
    @FXML
    private Button attaqueCac, attaqueDist;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){

        // initialise le modèle
        MODELE = (Combat) getQuete().getProchainEvenement();

        // instruction 'cliquez' masquée par défaut
        cliquez.setVisible(false);

        // initialise les PV de l'ennemi et masque l'interface de combat
        pvInitEnnemi = MODELE.getEnnemi().getPv();
        flow.setVisible(false);

        // gère l'arrière-plan et l'affichage du scénario
        chargeBg();
        ecran.setText(MODELE.getScenario().get(0));
        ecran.setUserData(0);
        ecran.setOnMouseClicked(this::passeTexte);
        EffetsJavaFx.fadeIn(ecran, 2.0, 1);
        ecran.setOnMouseMoved((e)->cliquez.setVisible(true));
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

        // affiche le prochain écran et masque par défaut l'instruction 'cliquez'
        cliquez.setVisible(false);
        Label label = (Label) event.getSource();

        // instruction 'cliquez affichée si mouvement de souris
        label.setOnMouseMoved((e)->cliquez.setVisible(true));

        // récupère et affiche le scénario
        int index = (int) label.getUserData() + 1;
        // si il reste du texte
        if (index < MODELE.getScenario().size()){
            label.setOpacity(0);
            label.setText(MODELE.getScenario().get(index));
            EffetsJavaFx.fadeIn(label, 2, 0);
            label.setUserData(index);
        // si il ne reste plus de texte
        } else if (index == MODELE.getScenario().size()) {
            label.setDisable(true);
            label.setVisible(false);

            // affichage éventuel de l'interface de soin, puis de l'interface de combat
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
     */
    @FXML
    private void attaqueCac(){
        int degatsH = getHeros().attaquer(MODELE.getEnnemi(), getInventaire().getArmeCac());
        lancerAttaque(degatsH);
    }

    /**
     * Lance l'attaque à distance du joueur
     */
    @FXML
    private void attaqueDist(){
        int degatsH = getHeros().attaquer(MODELE.getEnnemi(), getInventaire().getArmeDist());
        lancerAttaque(degatsH);
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
     */
    @FXML
    private void lancerAttaque(int degatsH){
        attaqueCac.setDisable(true);
        attaqueDist.setDisable(true);
        print.setText("");

        // gère l'attaque du héros
        attaqueHeros(degatsH);

        // poursuite de la phase de jeu si l'ennemi est toujours en vie
        if (MODELE.getEnnemi().enVie()){
            int degatsE = MODELE.getEnnemi().attaque();

            // contre-attaque de l'ennemi
            attaqueEnnemi(degatsH, degatsE);

            // gère la fin de la phase de jeu en fonction du status du héros
            if (getHeros().enVie()){
                finAttaque(degatsH, degatsE);
            } else {
                herosMort();
            }

        // gère la fin du combat si l'ennemi est mort
        } else {
            ennemiMort();
        }
    }

    /**
     * Traite la partie de l'attque pendant laquelle le héros attaque l'ennemi
     * @param degats dégats infligés à l'ennemi
     */
    @FXML
    private void attaqueHeros(int degats){

        // gère l'affichage de la barre de PV de l'ennemi
        Timeline tl = EffetsJavaFx.barreEtat(
                niveauVieEnnemi,
                MODELE.getEnnemi().getPv(),
                pvInitEnnemi,
                300
        );

        // gère l'affichage des PV de l'ennemi et des infos relative à la phase de jeu
        tl.setOnFinished((e)->{
            int pv = Math.max(MODELE.getEnnemi().getPv(), 0);
            pvEnnemi.setText(pv + "/" + pvInitEnnemi + " PV");
            print.setText(
                    "Vous avez infligé " + degats +
                    " points de dégats à " + MODELE.getEnnemi().getNom() + " !"
            );
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

        // gère l'affichage des infos relative à la phase de jeu
        Transition t = new PauseTransition(Duration.seconds(1.5));
        t.setOnFinished((e)->{
            print.setText(
                    "Vous avez infligé " + degatsH + " points de dégats à " +
                    MODELE.getEnnemi().getNom() + " !\n" +
                    "L'ennemi contre-attaque avec " + MODELE.getEnnemi().getArme().getNom() +
                    " et vous inflige à son tour " + degatsE + " points de dégats..."
            );
            Transition tp = new PauseTransition(Duration.seconds(1));

            // gère l'affichage de la barre de PV du héros
            Timeline tl = EffetsJavaFx.barreEtat(niveauVieHeros, getHeros().getPv(), 100, 200);
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
     */
    @FXML
    private void ennemiMort(){
        Transition t = new PauseTransition(Duration.seconds(2));
        t.setOnFinished((e)->{
            print.setText(
                    "VOUS AVEZ VAINCU " +
                    MODELE.getEnnemi().getNom().toUpperCase() +
                    " !\nCliquez pour continuer."
            );
            finCombat();
        });
        t.play();
    }

    /**
     * Gère la mort du héros
     */
    @FXML
    private void herosMort(){
        Transition t = new PauseTransition(Duration.seconds(4));
        t.setOnFinished((e)->{

            // recommence immédiatement si début de partie
            if (getHeros().getLocalisation() == getCarte().getPlaneteParNom("utopia")){
                print.setText(
                        MODELE.getEnnemi().getNom().toUpperCase() +
                        " VOUS A VAINCU...\nCliquez pour recommencer la quête."
                );

            // redirige vers le vaisseau si partie avancée
            } else {
                print.setText(
                        MODELE.getEnnemi().getNom().toUpperCase() +
                        " VOUS A VAINCU...\nVous reviendrez...\nCliquez pour revenir au vaisseau."
                );
            }
            finCombat();
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
            print.setText(
                    "Vous avez infligé " + degatsH + " points de dégats à " +
                    MODELE.getEnnemi().getNom() + " !\n" +
                    "L'ennemi contre-attaque avec " + MODELE.getEnnemi().getArme().getNom() +
                    " et vous inflige à son tour " + degatsE + " points de dégats...\n" +
                    "C'est votre tour d'attaquer."
            );
            attaqueCac.setDisable(false);
            attaqueDist.setDisable(false);
        });
        t.play();
    }

    /**
     * Gère la fin du combat
     */
    @FXML
    private void finCombat(){
        pane.setOnMouseClicked((e)->{

            // si victoire
            if (getHeros().enVie()){

                // si dernier événement de la planète
                if (MODELE.getIdIssue() == 0){

                    // redirection vers le vaisseau si cours de jeu
                    if (!BackHome.finJeu()){
                        getHeros().setSituation(Situation.VAISSEAU);
                        getHeros().getLocalisation().recompenses();
                        new View().carteView();

                    // redirection vers la vue dédiée si fin du jeu
                    } else {
                        new View().backHomeView();
                    }

                // redirige vers le prochain événement s'il en reste pour la planète
                } else {
                    getQuete().prochainEvenement(MODELE.getIdIssue());
                    new View().queteView();
                }

            // si défaite
            } else {

                // recommence le parcours planète si début de partie
                if (getHeros().getLocalisation() == getCarte().getPlaneteParNom("utopia")){
                    getHeros().setSituation(Situation.DEBUT);
                    new View().queteView();

                // redirige vers le vaisseau si partie avancée
                } else {
                    getHeros().retour();
                    getHeros().setSituation(Situation.VAISSEAU);
                    new View().carteView();
                }
            }
        });
    }
}
