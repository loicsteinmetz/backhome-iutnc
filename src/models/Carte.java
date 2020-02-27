package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.Parser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Singleton générant la carte et son interface.
 */
public class Carte extends Application {

    private static Carte CARTE = new Carte();
    private ArrayList<Planete> planetes = new ArrayList<Planete>();

    /**
     * Constructeur privé
     */
    private Carte(){
        try {
            this.initialiserPlanetes();
            this.initialiserPlanetesVoisines();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    };

    /**
     * Getter de l'instance de la carte (singleton)
     * @return l'instance de la carte
     */
    public static Carte getCarte(){
        if(CARTE == null) {
            CARTE = new Carte();
        }
        return CARTE;
    }

    /**
     * Initialise les planètes de la carte
     * @throws IOException
     * @throws ParseException
     */
    private void initialiserPlanetes() throws IOException, ParseException {
        String fichierPlenetes = Parser.parseChemin("src/assets/config/planetes.json");
        JSONObject[] planetes = Parser.parseObjects(fichierPlenetes, "planetes");
        for (JSONObject p : planetes){
            this.nouvellePlanete(new Planete(
                    p.get("nom").toString(),
                    Integer.parseInt(p.get("niveau").toString())
            ));
        }
    }

    /**
     * Initialise les planètes voisines de chaque planète de la carte
     * @throws IOException
     * @throws ParseException
     */
    private void initialiserPlanetesVoisines() throws IOException, ParseException {
        String fichierPlenetes = Parser.parseChemin("src/assets/config/planetes.json");
        JSONObject[] planetes = Parser.parseObjects(fichierPlenetes, "planetes");
        for (int i = 0 ; i < this.planetes.size() ; i++){
            JSONArray arr = (JSONArray) planetes[i].get("planetesVoisines");
            for (Object p : arr){
                Planete voisine = this.getPlaneteParNom(p.toString());
                this.planetes.get(i).nouvellePlaneteVoisine(voisine);
            }
        }
    }

    /**
     * Getter
     * @return les planètes de la carte
     */
    public ArrayList<Planete> getPlanetes(){
        return this.planetes;
    }

    /**
     * Getter
     * @param nom nom de la planète
     * @return la planete de la carte portant le nom saisi
     */
    public Planete getPlaneteParNom(String nom){
        Planete res = null;
        for (Planete p : this.planetes){
            if (p.getNom().equalsIgnoreCase(nom)){
                res = p;
                break;
            }
        }
        return res;
    }

    /**
     * Ajoute une planète à la carte
     * @param p planète à ajouter
     */
    private void nouvellePlanete(Planete p){
        this.planetes.add(p);
    }

    /**
     * Génère l'interface de la carte
     * @param stage primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/Carte.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("assets/css/BackHome.css");
        stage.setScene(scene);
    }
}
