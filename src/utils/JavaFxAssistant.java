package utils;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe utilitaire de gestion de fontionnalités JavaFx
 */
public class JavaFxAssistant {

    /**
     * Lance une animation fade out sur un élément
     * @param node élément
     * @param dureeSec durée de l'animation
     * @param delaySec délai avant animation
     * @return la transition générée
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
     * Lance une animation fade in sur un élément
     * @param node élément
     * @param dureeSec durée de l'animation
     * @param delaySec délai avant animation
     * @return la transition générée
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
     * Redirige vers une nouvelle interface
     * @param app la classe gérant l'interface à générée
     * @param e l'événement déclenchant la redirection
     */
    public static void switchTo(Application app, Event e){
        try {
            app.start((Stage) ((Node) e.getSource()).getScene().getWindow());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Redirige vers une nouvelle interface après un délai
     * @param app la classe gérant l'interface à générée
     * @param e l'événement déclenchant la redirection
     * @param delaySec délai avant redirection
     */
    public static void switchTo(Application app, Event e, double delaySec){
        PauseTransition wait = new PauseTransition(Duration.seconds(delaySec));
        wait.setOnFinished((a) -> {
            switchTo(app, e);
        });
        wait.play();
    }

    /**
     * Génère un texte défilant
     * @param s le tableau contenant les différents écrans texte
     * @param p le paneau sur lequel le texte est affiché
     * @return la dernière transition générée
     */
    public static Transition transitionTexteDeroulant(String[] s, Pane p){
        Transition derniereTransition = null;
        double inDelay = 0, outDelay;
        for (int i = 0 ; i < s.length ; i++){
            inDelay = i == 0 ? 1.0 : inDelay + 5;
            outDelay = inDelay + 3;
            Label label = new Label(s[i]);
            label.setId("ecran");
            p.getChildren().add(label);
            JavaFxAssistant.fadeIn(label, 2.0, inDelay);
            derniereTransition = JavaFxAssistant.fadeOut(label, 2.0, outDelay);
        }
        return derniereTransition;
    }
}
