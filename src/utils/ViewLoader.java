package utils;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewLoader {

    public void switchTo(String view, Stage primaryStage){
        try {
            System.out.println(getClass().getResource(view));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            loader.load();
            ((Application)loader.getController()).start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void switchTo(String view, Event e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            loader.load();
            ((Application)loader.getController()).start((Stage) ((Node)e.getSource()).getScene().getWindow());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void switchTo(String view, Event e, double delaySec){
        PauseTransition wait = new PauseTransition(Duration.seconds(delaySec));
        wait.setOnFinished((a) -> {
            switchTo(view, e);
        });
        wait.play();
    }
}
