package models;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;

import static models.Carte.getCarte;
import static models.Inventaire.getInventaire;

public class Sauvegarde {

    public static void chargeSauvegarde(int id){
        String chemin = "/data/sauvegardes.json";
        String cle = Integer.toString(id);
        JSONObject save = null;
        try {
            save = new JsonParser().parseObject(chemin,cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (save != null){
            getInventaire().setArmeCac(new ArmeCac((int)(long)save.get("idArmeCac")));
            getInventaire().setArmeDist(new ArmeDistance((int)(long)save.get("idArmeDistance")));
            getInventaire().setArmure(new Armure((int)(long)save.get("idArmure")));
            getInventaire().modifierCarburant((int)(long)save.get("carbu"));

            JSONArray a = (JSONArray) save.get("planetes");
            for(Object p : a){
                if(p.visitee) {
                    getCarte().getPlaneteParNom(p.toString()).setVisitee();
                }
            }
        }
    }
}
