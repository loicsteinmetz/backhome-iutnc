package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Decision;
import models.Situation;

import static models.Heros.getHeros;
import static models.Quete.getQuete;

/**
 * Classe de gestion des vues, appelée dans les controleurs
 * @see controllers.Controller
 */
public class View {

    private static Stage primaryStage;

    /**
     * Génère la vue BackHome
     * @see BackHomeView
     */
    public void backHomeView() {
        try {
            new BackHomeView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère la vue Carte
     * @see CarteView
     */
    public void carteView(){
        try {
            new CarteView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère la vue Inventaire
     * @see InventaireView
     */
    public void inventaireView(){
        try {
            new InventaireView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère la vue Quete ou redirige vers la vue Combat/Decision
     * @see QueteView
     */
    public void queteView(){
        if (getHeros().getSituation() != Situation.EVENEMENT){
            try {
                new QueteView().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (getQuete().getProchainEvenement() instanceof Decision){
                decisionView();
            } else {
                combatView();
            }
        }
    }

    /**
     * Génère la vue Combat
     * @see CombatView
     */
    private void combatView(){
        try {
            new CombatView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère la vue Decision
     * @see DecisionView
     */
    private void decisionView(){
        try {
            new DecisionView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère la vue Sauvegarde
     * @see SauvegardeView
     */
    public void sauvegardeView(){
        try {
            new SauvegardeView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter
     * @param s stage initial généré pour l'application
     * @see app.Main
     */
    public static void setStage(Stage s){
        primaryStage = s;
    }

    /**
     * Gère la vue BackHome
     * @see View
     */
    private static class BackHomeView extends Application {

        private static final String VIEW = "/views/BackHome.fxml";
        private static final String STYLE = "/assets/css/BackHome.css";

        /**
         * Génère l'interface d'accueil
         * @see View
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
    }

    /**
     * Gère la vue Carte
     * @see View
     */
    private static class CarteView extends Application{

        private static final String VIEW = "/views/Carte.fxml";
        private static final String STYLE = "/assets/css/Carte.css";

        /**
         * Génère l'interface du vaisseau à partir de la carte
         * @see View
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
    }

    /**
     * Gère la vue Combat
     * @see View
     */
    private static class CombatView extends Application{

        private static final String VIEW = "/views/Combat.fxml";
        private static final String STYLE = "/assets/css/Combat.css";

        /**
         * Génère l'interface de combat
         * @see View
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
    }

    /**
     * Gère la vue Decision
     * @see View
     */
    private static class DecisionView extends Application{

        private static final String VIEW = "/views/Decision.fxml";
        private static final String STYLE = "/assets/css/Decision.css";

        /**
         * Génère l'interface de combat
         * @see View
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
    }

    /**
     * Gère la vue Inventaire
     * @see View
     */
    private static class InventaireView extends Application{

        private static final String VIEW = "/views/Inventaire.fxml";
        private static final String STYLE = "/assets/css/Inventaire.css";

        /**
         * Génère l'interface de l'inventaire
         * @see View
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
    }

    /**
     * Gère la vue Quete
     * @see View
     */
    private static class QueteView extends Application{

        private static final String VIEW = "/views/Quete.fxml";
        private static final String STYLE = "/assets/css/Quete.css";

        /**
         * Génère l'interface de quête
         * @see View
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
    }

    /**
     * Gère la vue Sauvegarde
     * @see View
     */
    private static class SauvegardeView extends Application{

        private static final String VIEW = "/views/Sauvegarde.fxml";
        private static final String STYLE = "/assets/css/Sauvegarde.css";

        /**
         * Génère l'interface de sauvegarde
         * @see View
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
    }
}
