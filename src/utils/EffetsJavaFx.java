package utils;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
        ft.setFromValue(1.0);
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
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setDelay(Duration.seconds(delaySec));
        ft.play();
        return ft;
    }

    /**
     * Génère une suite de textes plein écran
     * @param s tableau contenant les différents écrans texte
     * @param p paneau sur lequel le texte est affiché
     * @return dernière transition générée
     */
    public static Transition textePleinEcran(String[] s, Pane p){
        Transition derniereTransition = null;
        double inDelay = 0, outDelay;
        for (int i = 0 ; i < s.length ; i++){
            inDelay = i == 0 ? 1.0 : inDelay + 5;
            outDelay = inDelay + 3;
            Label label = new Label(s[i]);
            label.setId("ecran");
            p.getChildren().add(label);
            EffetsJavaFx.fadeIn(label, 2.0, inDelay);
            derniereTransition = EffetsJavaFx.fadeOut(label, 2.0, outDelay);
        }
        return derniereTransition;
    }
}
