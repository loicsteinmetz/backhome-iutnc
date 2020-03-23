package controllers;

import javafx.animation.Transition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import models.BackHome;
import models.Decision;
import utils.EffetsJavaFx;
import views.View;

import static models.Heros.getHeros;
import static models.Quete.getQuete;

/**
 * Controller des décisions
 */
public class DecisionController {

    @Controller
    private Decision MODELE;

    @FXML
    private ImageView bg;
    @FXML
    private Button issueA, issueB;
    @FXML
    private Label ecran, cliquez;
    @FXML
    private HBox btnBox;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){

        // initialisation du modèle
        MODELE = (Decision) getQuete().getProchainEvenement();

        // instruction 'cliquez' masquée par défaut
        cliquez.setVisible(false);

        // gère l'arrière-plan et l'affichage du scénario
        Transition t = EffetsJavaFx.fade(bg, 4, 0, 0.15);
        t.setOnFinished((e)-> EffetsJavaFx.vibrance(bg, 6, 0.15, 0.05));
        ecran.setText(MODELE.getScenario().get(0));
        ecran.setUserData(0);
        ecran.setOnMouseClicked(this::passeTexte);
        EffetsJavaFx.fadeIn(ecran, 2.0, 1);
        issueA.setOpacity(0);
        issueB.setOpacity(0);

        // instruction 'cliquez affichée si mouvement de souris
        ecran.setOnMouseMoved((e)->cliquez.setVisible(true));
    }

    /**
     * Charge le scenario et ses débouchés
     * @param event clic
     */
    @FXML
    private void passeTexte(Event event){

        // affiche le prochain écran et masque par défaut l'instruction 'cliquez'
        cliquez.setVisible(false);

        // récupère et affiche le scénario
        int index = (int) ecran.getUserData() + 1;
        ecran.setOpacity(0);
        ecran.setText(MODELE.getScenario().get(index));
        EffetsJavaFx.fadeIn(ecran, 2, 0);
        ecran.setUserData(index);

        // si dernier écran texte
        if (index == MODELE.getScenario().size() - 1){

            // affichage des deux boutons si deux issues possibles
            if (MODELE.getIdIssueA() != -1 && MODELE.getIdIssueB() != -1){
                ecran.setDisable(true);
                EffetsJavaFx.fadeIn(issueA, 2, 0);
                EffetsJavaFx.fadeIn(issueB, 2, 0);
                btnBox.setLayoutY(475);
                issueA.setText(MODELE.getOptionA());
                issueB.setText(MODELE.getOptionB());

            // affichage d'un bouton de retour au vaisseau si paramétrage VAISSEAU
            } else if (MODELE.getIdIssueA() == 0) {
                ecran.setDisable(true);
                EffetsJavaFx.fadeIn(issueA, 2, 0);
                btnBox.getChildren().remove(1);
                btnBox.setLayoutY(475);
                issueA.setText("Aller au vaisseau");

            // affichage d'un seul bouton si une seule issue possible
            } else if (MODELE.getIdIssueA() != 0) {
                ecran.setDisable(true);
                EffetsJavaFx.fadeIn(issueA, 2, 0);
                btnBox.getChildren().remove(1);
                btnBox.setLayoutY(475);
                issueA.setText(MODELE.getOptionA());

            // poursuite de la quête avec un clic si aucun choix à faire
            } else {
                ecran.setOnMouseClicked((e)-> new View().queteView());
            }
        } else {
            ecran.setOnMouseMoved((e)->cliquez.setVisible(true));
        }
    }

    /**
     * Traite le choix de l'issue A
     */
    @FXML
    private void issueA(){

        // si dernier événement de la planète
        if (MODELE.getIdIssueA() == 0){

            // redirection vers le vaisseau, après attribution récompenses si cours de partie
            if (!BackHome.finJeu()){
                getHeros().getLocalisation().recompenses();
                new View().carteView();

            // redirection vers la vue dédiée si fin de partie
            } else {
                new View().backHomeView();
            }

        // prochain événement si il en reste sur la planète
        } else {
            getQuete().prochainEvenement(MODELE.getIdIssueA());
            new View().queteView();
        }
    }

    /**
     * Traite le choix de l'issue B
     */
    @FXML
    private void issueB(){

        // prohcain événement sur la planète
        getQuete().prochainEvenement(MODELE.getIdIssueB());
        new View().queteView();
    }
}
