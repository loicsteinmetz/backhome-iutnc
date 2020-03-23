package controllers;

import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import models.BackHome;
import utils.EffetsJavaFx;
import views.View;

/**
 * Controleur début et fin de jeu
 */
public class BackHomeController {

    @Controller
    private static BackHome MODELE;

    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane grid;
    @FXML
    private Label titre, cliquez;
    @FXML
    private Button startBtn, saveBtn;
    @FXML
    private ImageView vaisseau, starsBg1,starsBg2;

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){

        // initialise le modèle
        MODELE = new BackHome();

        // gère le mvt de l'arrière-plan
        EffetsJavaFx.defilementBg(starsBg1, starsBg2);
        cliquez.setVisible(false);

        // gère la fin du jeu
        if (BackHome.finJeu()){

            // retire les éléments de l'accueil
            grid.setDisable(true);
            grid.setVisible(false);

            // gère l'arrière-plan
            starsBg1.setOpacity(0);
            starsBg2.setOpacity(0);
            EffetsJavaFx.fadeIn(starsBg1, 2, 0);
            EffetsJavaFx.fadeIn(starsBg2, 2, 0);

            // affiche le scénario de fin / les crédits
            Label label = new Label(MODELE.getScenarioFin()[0]);
            label.setId("ecran");
            label.setOpacity(0);
            label.setUserData(0);
            label.setOnMouseClicked(this::passeTexte);
            pane.getChildren().add(label);
            EffetsJavaFx.fadeIn(label, 2.0, 1.5);
        }
    }

    /**
     * Lance le jeu lors du clic sur le boutton dédié
     */
    @FXML
    private void bouttonJouer() {

        // gère la transition
        startBtn.setVisible(false);
        saveBtn.setVisible(false);
        titre.setVisible(false);
        Transition fadeOut = EffetsJavaFx.fadeOut(vaisseau, 1.5, 0);

        // affiche le scénario du début de partie
        fadeOut.setOnFinished((e) -> {
            Label label = new Label(MODELE.getScenarioDebut()[0]);
            label.setId("ecran");
            label.setOpacity(0);
            label.setUserData(0);
            label.setOnMouseClicked(this::passeTexte);
            pane.getChildren().add(label);
            EffetsJavaFx.fadeIn(label, 2.0, 1.5);
            cliquez.setVisible(false);

            // instruction 'cliquez affichée si mouvement de souris
            label.setOnMouseMoved((e2)->cliquez.setVisible(true));
        });
    }

    /**
     * Redirige vers la view propre aux sauvegardes
     */
    @FXML
    private void sauvegardes(){
        new View().sauvegardeView();
    }

    /**
     * Saute la page de texte pour aller à la prochaîne ou vers la prochaîne vue
     * @param event clic
     */
    @FXML
    private void passeTexte(Event event){

        // affiche le prochain écran et masque par défaut l'instruction 'cliquez'
        cliquez.setVisible(false);
        Label label = (Label) event.getSource();

        // instruction 'cliquez affichée si mouvement de souris
        label.setOnMouseMoved((e)->cliquez.setVisible(true));

        // récupère et affiche le scénario
        int index = (int) label.getUserData() + 1;
        String[] scenario = BackHome.finJeu() ? MODELE.getScenarioFin() : MODELE.getScenarioDebut();

        // si il reste du texte
        if (index < scenario.length){
            label.setOpacity(0);
            label.setText(scenario[index]);
            EffetsJavaFx.fadeIn(label, 2, 0);
            label.setUserData(index);

        // si il ne reste plus de texte
        } else if (index == scenario.length) {
            label.setDisable(true);
            label.setVisible(false);

            // redirige vers la view de quête si début de partie
            if (!BackHome.finJeu()){
                new View().queteView();

            // réinitialise le jeu et redirige vers page d'accueil si fin de partie
            } else {
                BackHome.resetJeu();
                Transition p = new PauseTransition(Duration.seconds(2.5));
                p.setOnFinished((e)-> new View().backHomeView());
                p.play();
            }
        }
    }
}
