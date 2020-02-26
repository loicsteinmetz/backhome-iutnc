package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Carte extends Application {

    private static Carte CARTE = new Carte();

    private Carte(){};

    public static Carte getCarte(){
        if(CARTE == null) {
            CARTE = new Carte();
        }
        return CARTE;
    }


    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/Carte.fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
