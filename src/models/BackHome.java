package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.org.json.simple.parser.ParseException;
import utils.Fonts;

import java.io.IOException;

/**
 * Classe principale du programme ; Modélisation du système de jeu
 */
public class BackHome extends Application {

    /**
     * Génère l'interface du jeu
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/BackHome.fxml"));
        Fonts.load();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/assets/css/BackHome.css");
        primaryStage.setTitle("Back Home");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Méthode exécutable
     * @param args arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        launch(args);
    }
}
