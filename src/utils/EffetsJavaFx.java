package utils;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Classe utilitaire de gestion de fontionnalités JavaFx
 */
public class EffetsJavaFx {

    /**
     * Lance une animation de fondu sur un élément
     * @param node élément
     * @param dureeSec durée de l'animation
     * @param delaySec délai avant animation
     * @return transition générée
     */
    public static Transition fadeOut(Node node, double dureeSec, double delaySec){
        FadeTransition ft = new FadeTransition(Duration.seconds(dureeSec), node);
        ft.setFromValue(node.getOpacity());
        ft.setToValue(0.0);
        ft.setDelay(Duration.seconds(delaySec));
        ft.play();
        return ft;
    }

    /**
     * Lance une animation de fondu sur un élément
     * @param node élément
     * @param dureeSec durée de l'animation
     * @param delaySec délai avant animation
     * @return transition générée
     */
    public static Transition fadeIn(Node node, double dureeSec, double delaySec){
        FadeTransition ft = new FadeTransition(Duration.seconds(dureeSec), node);
        ft.setFromValue(node.getOpacity());
        ft.setToValue(1.0);
        ft.setDelay(Duration.seconds(delaySec));
        ft.play();
        return ft;
    }
}
