package models;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;

public class Sauvegarde implements models.Configurable {

    private int id;
    private Date date;
    private Planete localisation;
    private boolean[] statusPlanete;
    private ArmeCac armeCac;
    private ArmeDistance armeDistance;
    private Armure armure;
    private int carburant;
    private boolean vide;

    public Sauvegarde(int id){
        this.id = id;
        this.initConfiguration();
    }

    @Override
    public void initConfiguration() {
        String chemin = "/data/sauvegardes.json";
        String cle = Integer.toString(id);
        JSONObject sauvegarde = null;
        try {
            sauvegarde = new JsonParser().parseObject(chemin, cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (sauvegarde != null){
            if (sauvegarde.get("date") != null){
                vide = false;
                SimpleDateFormat parser = new SimpleDateFormat("dd-MM-YYYY - HH:mm");
                try {
                    date = parser.parse(sauvegarde.get("date").toString());
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                localisation = getCarte().getPlaneteParNom(sauvegarde.get("localisation").toString());
                JSONArray arr = (JSONArray) sauvegarde.get("statusPlanetes");
                statusPlanete = new boolean[arr.size()];
                for (int i = 0 ; i < arr.size() ; i++){
                    statusPlanete[i] = (boolean)arr.get(i);
                }
                armeCac = new ArmeCac(Integer.parseInt(sauvegarde.get("idArmeCac").toString()));
                armeDistance = new ArmeDistance(Integer.parseInt(sauvegarde.get("idArmeDistance").toString()));
                armure = new Armure(Integer.parseInt(sauvegarde.get("idArmure").toString()));
                carburant = Integer.parseInt(sauvegarde.get("carburant").toString());
            } else {
                vide = true;
            }
        }
    }

    public static Sauvegarde[] getAllSauvegardes(){
        Sauvegarde[] sauvegardes = new Sauvegarde[5];
        for (int i = 0 ; i < sauvegardes.length ; i++){
            sauvegardes[i] = new Sauvegarde(i + 1);
        }
        return sauvegardes;
    }

    public static void sauvegarder(int id){
        JSONObject sauvegardes = new JSONObject();
        JSONObject sauvegarde = initSauvegarde();
        sauvegardes.put(String.valueOf(id), sauvegarde);
        ajouterAnciennesSauvegardes(id, sauvegardes);

        try {
            new JsonParser().ecrireJson("/data/sauvegardes.json", sauvegardes);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerSauvegarde(int id){
        JSONObject sauvegardes = new JSONObject();
        JSONObject sauvegarde = initSauvegardeVide();
        sauvegardes.put(String.valueOf(id), sauvegarde);
        ajouterAnciennesSauvegardes(id, sauvegardes);
        try {
            new JsonParser().ecrireJson("/data/sauvegardes.json", sauvegardes);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void ajouterAnciennesSauvegardes(int id, JSONObject sauvegardes){
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-YYYY - HH:mm");
        JSONObject prev;
        Sauvegarde[] prevSauvegardes = getAllSauvegardes();
        for (int i = 0 ; i < prevSauvegardes.length ; i++){
            if (i != id - 1){
                prev = new JSONObject();
                if (prevSauvegardes[i].date != null){
                    prev.put("date", parser.format(prevSauvegardes[i].date));
                    prev.put("localisation", prevSauvegardes[i].localisation.getNom());
                    prev.put("statusPlanetes", prevSauvegardes[i].statusPlanete);
                    prev.put("idArmeCac", prevSauvegardes[i].armeCac.getId());
                    prev.put("idArmeDistance", prevSauvegardes[i].armeDistance.getId());
                    prev.put("idArmure", prevSauvegardes[i].armure.getId());
                    prev.put("carburant", prevSauvegardes[i].carburant);
                } else {
                    prev.put("date", null);
                }
                sauvegardes.put(String.valueOf(prevSauvegardes[i].id), prev);
            }
        }
    }

    private static JSONObject initSauvegarde(){
        JSONObject sauvegarde = new JSONObject();
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-YYYY - HH:mm");
        sauvegarde.put("date", parser.format(new Date()));
        sauvegarde.put("localisation", getHeros().getLocalisation().getNom());
        sauvegarde.put("statusPlanetes", getCarte().getAllStatus());
        sauvegarde.put("idArmeCac", getInventaire().getArmeCac().getId());
        sauvegarde.put("idArmeDistance", getInventaire().getArmeDist().getId());
        sauvegarde.put("idArmure", getInventaire().getArmure().getId());
        sauvegarde.put("carburant", getInventaire().getCarburant());
        return sauvegarde;
    }

    private static JSONObject initSauvegardeVide(){
        JSONObject sauvegarde = new JSONObject();
        sauvegarde.put("date", null);
        return sauvegarde;
    }

    public boolean estVide(){
        return vide;
    }

    public void charger(){
        getHeros().setLocalisation(localisation);
        getCarte().setAllStatus(statusPlanete);
        getInventaire().setArmeCac(armeCac);
        getInventaire().setArmeDist(armeDistance);
        getInventaire().setArmure(armure);
        getInventaire().setCarburant(carburant);
    }

    public static void main (String[] ags){
        sauvegarder(2);
    }
}
