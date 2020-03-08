package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import static models.Carte.getCarte;

public class Quete implements Configurable {

    @Singleton
    private static Quete QUETE = new Quete();

    private Evenement prochainEvenement;
    private int idProchainEvenement;

    private Quete(){
    }

    /**
     * Getter de l'instance de Quete (singleton)
     * @return l'instance de quete
     */
    @Singleton
    public static Quete getQuete(){
        if(QUETE == null) {
            QUETE = new Quete();
        }
        return QUETE;
    }

    public void nouvellePlanete(Planete planete){
        idProchainEvenement = planete.getIdPremierEvenement();
        initConfiguration();
    }

    @Override
    public void initConfiguration() {
        String chemin = "/data/evenements.json";
        String cle = Integer.toString(idProchainEvenement);
        JSONObject evenement = null;
        try {
            evenement = new JsonParser().parseObject(chemin, cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (evenement != null){
            switch (evenement.get("type").toString()){
                case "decision":
                    prochainEvenement = new Decision(idProchainEvenement);
                    break;
                case "combat" :
                    prochainEvenement = new Combat(idProchainEvenement);
                    break;
            }
        }
    }

    public Evenement getProchainEvenement() {
        return prochainEvenement;
    }

    public void prochainEvenement(int id){
        idProchainEvenement = id;
        initConfiguration();
    }
}
