package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Quete;

import static models.Quete.getQuete;


public class QueteController extends Application {

    @Controller
    private static final String VIEW = "/views/Quete.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Quete.css";
    @Controller
    private static final Quete MODELE = getQuete();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }
}
