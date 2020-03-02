package models;


import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Modélisation du système de jeu
 */
public class BackHome implements Scenarise {

    public String[] getScenario() {
        String chemin = "/assets/config/scenario.json";
        JsonParser parser = new JsonParser();
        String[] scenario = new String[0];
        try {
            scenario = parser.parseStrings(chemin, "nouvellePartie");
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return scenario;
    }
}
