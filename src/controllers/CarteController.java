package controllers;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Carte;
import models.Planete;
import models.Situation;
import utils.EffetsJavaFx;
import utils.ViewLoader;

import static models.Carte.getCarte;
import static models.Heros.getHeros;

/**
 * Controller de la carte
 */
public class CarteController extends Application {

    @Controller
    private static final String VIEW = "/views/Carte.fxml";
    @Controller
    private static final String STYLE = "/assets/css/Carte.css";
    @Controller
    private static Carte MODELE;

    @FXML
    private ImageView hud;
    @FXML
    private FlowPane flow;
    @FXML
    private Label loc;
    @FXML
    private Label dest;

    /**
     * Retourne la vue associée au controller
     * @return chemin de la vue
     */
    @Controller
    public static String getView(){
        return VIEW;
    }

    /**
     * Génère l'interface de la carte
     * @param stage primaryStage
     * @throws Exception chargement de la vue
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(VIEW));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(STYLE);
        stage.setScene(scene);
    }

    /**
     * Initialisation de la vue et du modèle
     */
    @FXML
    private void initialize(){
        MODELE = getCarte();
        getHeros().setSituation(Situation.VAISSEAU);
        chargeElementsInterface();
        chargeLocalisation();
        chargePlanetesDisponibles();
    }

    /**
     * Charge l'apparition des éléments de l'interface
     */
    @FXML
    private void chargeElementsInterface(){
        EffetsJavaFx.fadeIn(hud, 2, 0);
    }

    /**
     * Charge la localisation du héros
     */
    @FXML
    private void chargeLocalisation(){
        loc.setText("LOCALISATION : " + getHeros().getLocalisation().getNom());
        EffetsJavaFx.fadeIn(dest, 2, 0);
        EffetsJavaFx.fadeIn(loc, 2, 0);
    }

    /**
     * Charge les planètes disponibles pour le héros
     */
    @FXML
    private void chargePlanetesDisponibles(){
        for (Planete p : getHeros().getLocalisation().getPlanetesVoisines()){
            HBox box = new HBox();
            box.setOnMouseClicked(this::allerPlanete);
            box.setUserData(p.getNom());
            box.setOpacity(0);
            box.getStyleClass().add("planete");
            Label nom = new Label();
            nom.getStyleClass().add("nomPlanete");
            nom.setText(p.getNom());
            Label desc = new Label();
            desc.getStyleClass().add("descriptionPlanete");
            desc.setText(p.getDescription());
            box.getChildren().add(nom);
            box.getChildren().add(desc);
            flow.getChildren().add(box);
            EffetsJavaFx.fadeIn(box, 2, 0);
        }
    }

    /**
     * Fait naviguer le héros vers l'une des planètes disponibles
     * @param e clic sur l'une des options
     */
    @FXML
    private void allerPlanete(MouseEvent e){
        HBox box = (HBox) e.getSource();
        Planete nouvellePlanete = MODELE.getPlaneteParNom((String) box.getUserData());
        getHeros().setLocalisation(nouvellePlanete);
        new ViewLoader().switchTo(QueteController.getView(), e, 0.5);
    }

    /**
     * Permet d'accéder à l'inventaire
     * @param e clic sur le bouton
     */
    @FXML
    private void allerInventaire(Event e){
        new ViewLoader().switchTo(InventaireController.getView(), e, 0);
    }
}
