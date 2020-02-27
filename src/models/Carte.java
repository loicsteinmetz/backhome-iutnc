package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Singleton générant la carte et son interface.
 */
public class Carte extends Application {

    private static Carte CARTE = new Carte();

    /**
     * Constructeur privé
     */
    private Carte(){};

    /**
     * Getter de l'instance de la carte (singleton)
     * @return l'instance de la carte
     */
    public static Carte getCarte(){
        if(CARTE == null) {
            CARTE = new Carte();
        }
        return CARTE;
    }


    /**
     * Génère l'interface de la carte
     * @param stage primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/Carte.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("assets/css/BackHome.css");
        stage.setScene(scene);
    }
}
