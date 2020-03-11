package controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private VBox sauvegardes;

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

    @FXML
    private void initialize(){
        for(Node sauvegarde : sauvegardes.getChildren()){
            HBox s = (HBox)sauvegarde;
            if (s.getStyleClass().contains("vide")) s.setDisable(true);
        }
    }

    @FXML
    private void selectionner(Event e){
        for(Node sauvegarde : sauvegardes.getChildren()){
            HBox s = (HBox)sauvegarde;
            s.setStyle("-fx-border-color:transparent;");
            if (!s.getStyleClass().contains("vide")) s.setDisable(false);
        }
        HBox selectionee = (HBox)e.getSource();
        selectionee.setStyle("-fx-border-color:white;");
        selectionee.setDisable(true);
        idSelection = Integer.parseInt(selectionee.getUserData().toString());
    }

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
