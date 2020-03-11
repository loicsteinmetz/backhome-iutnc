package controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BackHome;
import models.Sauvegarde;
import utils.ViewLoader;

public class SauvegardeController extends Application {

    private int idSelection;

    @Controller
    private static final String VIEW = "/views/Sauvegarde.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Sauvegarde.css";
    @Controller
    private static Sauvegarde MODELE;

    @FXML
    private VBox sauvegardes, btns;
    @FXML
    private Button btn1, btn2, btn3;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface propres aux sauvegardes
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
        btn1.setDisable(true);
        btn2.setDisable(true);
        if (!BackHome.getStarted()){
            for(Node sauvegarde : sauvegardes.getChildren()){
                HBox s = (HBox)sauvegarde;
                if (s.getStyleClass().contains("vide")) s.setDisable(true);
            }
        } else {
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
     * @param e clic sur le bouton retour
     */
    @FXML
    private void retour(Event e){
        ViewLoader vl = new ViewLoader();
        if (BackHome.getStarted()){
            vl.switchTo(CarteController.getView(), e);
        } else {
            vl.switchTo(BackHomeController.getView(), e);
        }
    }
}
