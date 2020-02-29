package models;


import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Modélisation du système de jeu
 */
public class BackHome {

    public String[] getScenario() throws ParseException, IOException, URISyntaxException {
        String chemin = "/assets/config/scenario.json";
        JsonParser parser = new JsonParser();
        String[] scenario = parser.parseStrings(chemin, "nouvellePartie");
        return scenario;
    }
}
