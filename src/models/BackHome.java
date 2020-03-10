package models;


import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;

/**
 * Modélisation du système de jeu
 */
public class BackHome implements Configurable {

    private String[] scenario;

    public BackHome(){
        initConfiguration();
    }

    /**
     * Getter
     * @return scenario
     */
    public String[] getScenario(){
        return scenario;
    }

    /**
     * Récupère les données de configuration de la page d'accueil
     */
    public void initConfiguration() {
        String chemin = "/data/backhome.json";
        JsonParser parser = new JsonParser();
        String[] scenario = new String[0];
        try {
            scenario = parser.parseStrings(chemin, "nouvellePartie");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.scenario = scenario;
    }
}
