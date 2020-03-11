package models;


import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;

import static models.Carte.getCarte;
import static models.Heros.getHeros;

/**
 * Modélisation du système de jeu
 */
public class BackHome implements Configurable {

    private String[] scenarioDebut;
    private String[] scenarioFin;
    private static boolean started = false;

    public BackHome(){
        initConfiguration();
    }

    /**
     * Getter
     * @return scenario
     */
    public String[] getScenarioDebut(){
        return scenarioDebut;
    }

    public String[] getScenarioFin(){
        return scenarioFin;
    }

    /**
     * Indique si la fin du jeu a été atteinte
     * @return true si la fin du jeu a été atteinte
     */
    public static boolean finJeu(){
        return getHeros().getLocalisation() == getCarte().getPlaneteParNom("Calypso");
    }

    /**
     * Récupère les données de configuration de la page d'accueil
     */
    public void initConfiguration() {
        String chemin = "/data/backhome.json";
        JsonParser parser = new JsonParser();
        String[] scenarioDebut = new String[0];
        String[] scenarioFin = new String[0];
        try {
            scenarioDebut = parser.parseStrings(chemin, "nouvellePartie");
            scenarioFin = parser.parseStrings(chemin, "finPartie");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.scenarioDebut = scenarioDebut;
        this.scenarioFin = scenarioFin;
    }

    public static void resetJeu(){
        Carte.reset();
        Heros.reset();
        Quete.reset();
        Inventaire.reset();
    }

    public static boolean getStarted(){
        return started;
    }

    public static void setStarted(){
        started = true;
    }
}
