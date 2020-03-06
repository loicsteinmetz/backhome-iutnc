package controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Inventaire;
import utils.ViewLoader;

import static models.Inventaire.getInventaire;

public class InventaireController extends Application {

    @FXML
    private Label armeCacNom;
    @FXML
    private Label armeCacDesc;
    @FXML
    private Label armeDistNom;
    @FXML
    private Label armeDistDesc;
    @FXML
    private Label armureNom;
    @FXML
    private Label armureDesc;
    @FXML
    private HBox niveauCarburant;
    @FXML
    private Label niveauCarburantLitres;

    @Controller
    private static final String VIEW = "/views/Inventaire.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Inventaire.css";
    @Controller
    private Inventaire MODELE;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface d'accueil
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
        MODELE = getInventaire();
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
     * @param event clic sur le bouton
     */
    @FXML
    private void allerCarte(Event event){
        new ViewLoader().switchTo(CarteController.getView(), event);
    }
}
