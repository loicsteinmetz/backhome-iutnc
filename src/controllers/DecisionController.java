package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Decision;

import static models.Quete.getQuete;

public class DecisionController extends Application {

    @Controller
    private static final String VIEW = "/views/Decision.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Decision.css";
    @Controller
    private static final Decision MODELE = (Decision) getQuete().getProchainEvenement();

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }
}
