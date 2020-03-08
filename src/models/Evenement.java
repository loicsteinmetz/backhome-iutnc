package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

public class Evenement {

    protected int id;
    protected ArrayList<String> scenario;

    /**
     * Constructeur
     * @param id l'id de l'événement
     */
    public Evenement(int id) {
        this.id = id;
    }

    /**
     * Configure l'événement suivant
     * @param id l'idIssue de l'événement en cours
     * @return l'événement suivant
     */
    public Evenement configIssue(int id){
        String chemin = "/data/evenements.json";
        String cle = Integer.toString(id);
        JSONObject evenement = null;
        Evenement issue = null;
        try {
            evenement = new JsonParser().parseObject(chemin, cle);
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
        if(evenement !=null) {
            String typeIssue = evenement.get("type").toString();
            switch (typeIssue) {
                case "combat":
                    issue = new Combat(id);
                    break;
                case "decision":
                    issue = new Decision(id);
                    break;
            }
        }
        return issue;
    }

    public ArrayList<String> getScenario() {
        return scenario;
    }
}
