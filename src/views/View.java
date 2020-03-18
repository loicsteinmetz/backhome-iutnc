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

public class View {

    private static Stage primaryStage;

    public void backHomeView() {
        try {
            new BackHomeView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carteView(){
        try {
            new CarteView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void combatView(){
        try {
            new CombatView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decisionView(){
        try {
            new DecisionView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inventaireView(){
        try {
            new InventaireView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queteView(){
        try {
            new QueteView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sauvegardeView(){
        try {
            new SauvegardeView().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setStage(Stage s){
        primaryStage = s;
    }

    private static class BackHomeView extends Application {

        private static final String VIEW = "/views/BackHome.fxml";
        private static final String STYLE = "/assets/css/BackHome.css";

        /**
         * Génère l'interface d'accueil
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

    private static class CarteView extends Application{

        private static final String VIEW = "/views/Carte.fxml";
        private static final String STYLE = "/assets/css/Carte.css";

        /**
         * Génère l'interface du vaisseau à partir de la carte
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

    private static class CombatView extends Application{

        private static final String VIEW = "/views/Combat.fxml";
        private static final String STYLE = "/assets/css/Combat.css";

        /**
         * Génère l'interface de combat
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

    private static class DecisionView extends Application{

        private static final String VIEW = "/views/Decision.fxml";
        private static final String STYLE = "/assets/css/Decision.css";

        /**
         * Génère l'interface de combat
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

    private static class InventaireView extends Application{

        private static final String VIEW = "/views/Inventaire.fxml";
        private static final String STYLE = "/assets/css/Inventaire.css";

        /**
         * Génère l'interface de l'inventaire
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

    private static class QueteView extends Application{

        private static final String VIEW = "/views/Quete.fxml";
        private static final String STYLE = "/assets/css/Quete.css";

        /**
         * Génère l'interface de quête
         * @param stage primaryStage
         * @throws Exception chargement de la vue
         */
        @Override
        public void start(Stage stage) throws Exception{
            if (getHeros().getSituation() != Situation.EVENEMENT){
                Parent root = FXMLLoader.load(getClass().getResource(VIEW));
                Scene scene = new Scene(root, 800, 600);
                scene.getStylesheets().add(STYLE);
                stage.setScene(scene);
            } else {
                if (getQuete().getProchainEvenement() instanceof Decision){
                    new View().decisionView();
                } else {
                    new View().combatView();
                }
            }
        }
    }

    private static class SauvegardeView extends Application{

        private static final String VIEW = "/views/Sauvegarde.fxml";
        private static final String STYLE = "/assets/css/Sauvegarde.css";

        /**
         * Génère l'interface de sauvegarde
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
