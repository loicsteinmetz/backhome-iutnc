package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.BackHome;
import models.Sauvegarde;
import views.View;

/**
 * Controleur de l'interface sauvegardes
 */
public class SauvegardeController {

    private int idSelection;

    @FXML
    private VBox sauvegardes, btns;
    @FXML
    private Button btn1, btn2;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){
        Sauvegarde.init();
        btn1.setDisable(true);
        btn2.setDisable(true);
        Sauvegarde[] allSauvegardes = Sauvegarde.getAllSauvegardes();
        HBox box;
        Label date, localisation;
        for (int i = 0 ; i < sauvegardes.getChildren().size() ; i++){
            box = (HBox)sauvegardes.getChildren().get(i);
            if (!allSauvegardes[i].estVide()){
                date = (Label)box.getChildren().get(0);
                date.setText(allSauvegardes[i].getDate());
                localisation = (Label)box.getChildren().get(1);
                localisation.setText(allSauvegardes[i].getLocalisation());
            } else {
                box.getStyleClass().add("vide");
            }
        }
        if (!BackHome.getStarted()){
            btn1.setOnAction(this::charger);
            for(Node sauvegarde : sauvegardes.getChildren()){
                HBox s = (HBox)sauvegarde;
                if (s.getStyleClass().contains("vide")) s.setDisable(true);
            }
        } else {
            btn1.setOnAction(this::sauvegarder);
            btn1.setText("Sauvegarder");
            btns.getChildren().remove(1);
        }
    }

    /**
     * Gère la sélection d'une sauvegarde pour ensuite la charger
     * la supprimer ou l'écraser pour un nouveau jeu de données
     * @param e clic sur une sauvegarde
     */
    @FXML
    private void selectionner(Event e){
        btn1.setDisable(false);
        btn2.setDisable(false);
        for(Node sauvegarde : sauvegardes.getChildren()){
            HBox s = (HBox)sauvegarde;
            s.setStyle("-fx-border-color:transparent;");
            if (!s.getStyleClass().contains("vide") || BackHome.getStarted()) s.setDisable(false);
        }
        HBox selectionee = (HBox)e.getSource();
        selectionee.setStyle("-fx-border-color:white;");
        selectionee.setDisable(true);
        idSelection = Integer.parseInt(selectionee.getUserData().toString());
        if (BackHome.getStarted()){
            if (!selectionee.getStyleClass().contains("vide")){
                btn1.setText("Ecraser");
            } else {
                btn1.setText("Sauvegarder");
            }
        }
    }

    /**
     * Permet le retour à la carte ou à l'écran d'accueil, suivant le contexte
     */
    @FXML
    private void retour(){
        if (BackHome.getStarted()){
            new View().carteView();
        } else {
            new View().backHomeView();
        }
    }

    /**
     * Supprime la sauvegarde sélectionnée et met à jour la vue
     */
    @FXML
    private void supprimer(){
        HBox sauvegarde = (HBox)sauvegardes.getChildren().get(idSelection - 1);
        Sauvegarde.supprimerSauvegarde(idSelection);
        sauvegarde.getStyleClass().add("vide");
        sauvegarde.setStyle("-fx-border-color:transparent;");
        Label date = (Label)sauvegarde.getChildren().get(0);
        date.setText("Emplacement vide");
        Label localisation = (Label)sauvegarde.getChildren().get(1);
        localisation.setText("");
        sauvegarde.setDisable(true);
        btn1.setDisable(true);
        btn2.setDisable(true);
    }

    /**
     * Charge la sauvegarde sélectionnée et redirige vers la carte
     * @param e bouton charger
     */
    @FXML
    private void charger(Event e){
        new Sauvegarde(idSelection).charger();
        new View().carteView();
    }

    /**
     * Enregistre la partie en cours et redirige vers la carte
     * (écrase éventuellement une sauvegarde existante)
     * @param e bouton sauvegarder
     */
    @FXML
    private void sauvegarder(Event e){
        Sauvegarde.sauvegarder(idSelection);
        new View().carteView();
    }


}
