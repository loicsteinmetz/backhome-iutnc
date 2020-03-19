package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.Inventaire;
import views.View;

import static models.Inventaire.getInventaire;

/**
 * Controleur de l'inventaire
 */
public class InventaireController {

    @FXML
    private Label armeCacNom, armeCacDesc, armeDistNom, armeDistDesc,
            armureNom, armureDesc, niveauCarburantLitres;
    @FXML
    private HBox niveauCarburant;

    @Controller
    private Inventaire MODELE;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){

        // initialisation du modèle
        MODELE = getInventaire();

        // affiche le contenu de l'inventaire
        armeCacNom.setText(MODELE.getArmeCac().getNom());
        armeCacDesc.setText("DEGATS  :  " + MODELE.getArmeCac().getDegats());
        armeDistNom.setText(MODELE.getArmeDist().getNom());
        armeDistDesc.setText("DEGATS  :  " + MODELE.getArmeDist().getDegats());
        armureNom.setText(MODELE.getArmure().getNom());
        armureDesc.setText("PROTEC  :  " + MODELE.getArmure().getResistance());
        niveauCarburant.setMinWidth(MODELE.getCarburant());
        niveauCarburantLitres.setText(MODELE.getCarburant() + "L");
    }

    /**
     * Permet d'accéder à la carte
     */
    @FXML
    private void allerCarte(){
        new View().carteView();
    }
}
