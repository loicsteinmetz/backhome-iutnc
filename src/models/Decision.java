package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

public class Decision extends Evenement implements Configurable {

    private int idIssueA;
    private int idIssueB;
    private String optionA;
    private String optionB;

    /**
     * Constructeur
     * @param id l'id de la décision
     */
    public Decision(int id){
        super(id);
        initConfiguration();
    }

    /**
     * Récupère les données de configuration de la décision
     */
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
            initIdIssues(evenement);
            initChoix(evenement);
            scenario = (ArrayList<String>) evenement.get("scenario");
        }
    }

    private void initIdIssues(JSONObject evenement){
        if (evenement.get("idIssueA") == null){
            idIssueA = -1;
        } else if (evenement.get("idIssueA").toString().equals("VAISSEAU")){
            idIssueA = 0;
        } else {
            idIssueA = Integer.parseInt(evenement.get("idIssueA").toString());
        }
        if (evenement.get("idIssueB") == null){
            idIssueB = -1;
        } else if (evenement.get("idIssueB").toString().equals("VAISSEAU")){
            idIssueB = 0;
        } else {
            idIssueB = Integer.parseInt(evenement.get("idIssueB").toString());
        }
    }

    private void initChoix(JSONObject evenement){
        if (evenement.get("optionA") != null){
            optionA = evenement.get("optionA").toString();
        } else {
            optionA = null;
        }
        if (evenement.get("optionB") != null){
            optionB = evenement.get("optionB").toString();
        } else {
            optionB = null;
        }
    }

    /**
     * Getter
     * @return l'id de l'issue A
     */
    public int getIdIssueA() {
        return idIssueA;
    }

    /**
     * Getter
     * @return l'id de l'issue B
     */
    public int getIdIssueB() {
        return idIssueB;
    }

    /**
     * Getter
     * @return le texte de l'option A
     */
    public String getOptionA() {
        return optionA;
    }

    /**
     * Getter
     * @return le texte de l'option B
     */
    public String getOptionB() {
        return optionB;
    }
}
