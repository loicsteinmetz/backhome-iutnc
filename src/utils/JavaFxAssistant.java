package utils;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class JavaFxAssistant {

    public static void fadeOut(Node node, double dureeSec){
        FadeTransition ft = new FadeTransition(Duration.seconds(dureeSec), node);
        ft.setToValue(0.0);
        ft.play();
    }

    public static void fadeIn(Node node, double dureeSec){
        FadeTransition ft = new FadeTransition(Duration.seconds(dureeSec), node);
        ft.setToValue(1.0);
        ft.play();
    }
}
