package models;


import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Modélisation du système de jeu
 */
public class BackHome {

    private String[] scenario;

    public BackHome(){
        recupereDonnees();
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
    @Configuration
    private void recupereDonnees() {
        String chemin = "/assets/config/scenario.json";
        JsonParser parser = new JsonParser();
        String[] scenario = new String[0];
        try {
            scenario = parser.parseStrings(chemin, "nouvellePartie");
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
        }
        this.scenario = scenario;
    }
}
