package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;

public class Decision extends Evenement implements Configurable {

    private int idIssueA;
    private int idIssueB;

    public Decision(int id){
        super(id);
        initConfiguration();
    }

    public int getIssueA() {
        return idIssueA;
    }

    public int getIssueB() {
        return idIssueB;
    }

    @Override
    public void initConfiguration() {
        String chemin = "/data/evenements.json";
        String cle = Integer.toString(id);
        JSONObject evenement = null;
        try {
            evenement = new JsonParser().parseObject(chemin, cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (evenement != null){
            idIssueA = (int)(long) evenement.get("idIssueA");
            idIssueB = (int)(long) evenement.get("idIssueB");
        }
    }
}
