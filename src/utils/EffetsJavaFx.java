package utils;

import javafx.animation.*;
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
        ft.setFromValue(1);
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
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.setDelay(Duration.seconds(delaySec));
        ft.play();
        return ft;
    }

    /**
     * Lance une animation de fondu sur un élément
     * @param node élément
     * @param dureeSec durée de l'animation
     * @param opStart opacité de départ
     * @param opEnd opacité d'arrivée
     * @return transition générée
     */
    public static Transition fade(Node node, double dureeSec, double opStart, double opEnd){
        FadeTransition ft = new FadeTransition(Duration.seconds(dureeSec), node);
        ft.setFromValue(opStart);
        ft.setToValue(opEnd);
        ft.play();
        return ft;
    }

    /**
     * Lance une animation de vibrance sur un élément
     * @param node élément
     * @param dureeSec durée de l'animation
     * @param opStart opacité min
     * @param opEnd opacité max
     * @return la timeline générée
     */
    public static Timeline vibrance(Node node, double dureeSec, double opStart, double opEnd){
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(node.opacityProperty(), opStart)),
                new KeyFrame(Duration.seconds(dureeSec/2), new KeyValue(node.opacityProperty(), opEnd)),
                new KeyFrame(Duration.seconds(dureeSec), new KeyValue(node.opacityProperty(), opStart))
        );
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        return tl;
    }

    /**
     * Anime l'arrière plan, composé de deux images
     * @param img1 première image
     * @param img2 image identique
     */
    public static void defilementBg(Node img1, Node img2) {
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1.5), img1);
        tt1.setFromY(-600);
        tt1.setToY(0);
        tt1.setCycleCount( Timeline.INDEFINITE);
        tt1.setInterpolator(Interpolator.LINEAR);
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.5), img2);
        tt2.setFromY(0);
        tt2.setToY(600);
        tt2.setCycleCount(Timeline.INDEFINITE);
        tt2.setInterpolator(Interpolator.LINEAR);
        tt1.play();
        tt2.play();
    }

    /**
     * Lance une animation de translation sur un élément, sur l'axe des abscisses
     * @param node élément
     * @param x1 abscisse d'origine
     * @param x2 abscisse d'arrivée
     * @param dureeSec durée de l'animation
     * @param delaiSec délai avant l'animation
     * @return la timeline générée
     */
    public static Timeline translationX(Node node, int x1, int x2, double dureeSec, double delaiSec){
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(node.translateXProperty(), x1)),
                new KeyFrame(Duration.seconds(dureeSec), new KeyValue(node.translateXProperty(), x2))
        );
        tl.setDelay(Duration.seconds(delaiSec));
        tl.play();
        return tl;
    }

    /**
     * Lance une animation de translation sur un élément, sur l'axe des ordonnées
     * @param node élément
     * @param y1 ordonnée d'origine
     * @param y2 ordonnée d'arrivée
     * @param dureeSec durée de l'animation
     * @param delaiSec délai avant l'animation
     * @return la timeline générée
     */
    public static Timeline translationY(Node node, int y1, int y2, double dureeSec, double delaiSec){
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(node.translateYProperty(), y1)),
                new KeyFrame(Duration.seconds(dureeSec), new KeyValue(node.translateYProperty(), y2))
        );
        tl.setDelay(Duration.seconds(delaiSec));
        tl.play();
        return tl;
    }
}
