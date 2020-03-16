package utils;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe utilitaire servant au chargement des vues
 */
public class ViewLoader {

    /**
     * Accède à une nouvelle vue (méthode initiale)
     * @param view chemin de la view
     * @param primaryStage stage initial
     */
    public void switchTo(String view, Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            loader.load();
            ((Application)loader.getController()).start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Accède à une nouvelle vue à partir d'un événement
     * @param view chemin de la view
     * @param e événement déclencheur
     */
    public void switchTo(String view, Event e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            loader.load();
            ((Application)loader.getController()).start((Stage) ((Node)e.getSource()).getScene().getWindow());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Accède à une nouvelle vue à partir d'un événement, après un délai
     * @param view chemin de la view
     * @param e événement déclencheur
     * @param delaySec délai en secondes
     */
    public void switchTo(String view, Event e, double delaySec){
        PauseTransition wait = new PauseTransition(Duration.seconds(delaySec));
        wait.setOnFinished((a) -> switchTo(view, e));
        wait.play();
    }
}
