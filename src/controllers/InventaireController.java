package controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.BackHome;
import models.Carte;
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

    @Controller
    private static final String VIEW = "/views/Inventaire.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Inventaire.css";
    @Controller
    private static final Inventaire MODELE = getInventaire();

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
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    @FXML
    private void initialize(){
        armeCacNom.setText(MODELE.getArmeCac().getNom());
        armeCacDesc.setText("DEGATS  :  " + MODELE.getArmeCac().getDegats());
        armeDistNom.setText(MODELE.getArmeDist().getNom());
        armeDistDesc.setText("DEGATS  :  " + MODELE.getArmeDist().getDegats());
        //armureNom.setText(MODELE.getArmure().getNom());
        //armureDesc.setText("PROTEC  :  " + MODELE.getArmure().getResistance());
    }

    @FXML
    private void allerCarte(Event event){
        new ViewLoader().switchTo(CarteController.getView(), event);
    }
}
