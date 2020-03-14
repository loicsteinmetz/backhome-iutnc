package models;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.JSONParser;
import lib.org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private static String FICHIER;

    public Sauvegarde(int id){
        this.id = id;
        this.initConfiguration();
    }

    public static void init(){
        URL url = Sauvegarde.class.getProtectionDomain()
                .getCodeSource()
                .getLocation();
        try {
            File dir = new File(url.toURI()).getParentFile();
            FICHIER = dir.getPath() + "/sauvegardes.json";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            new FileReader(FICHIER);
        } catch (FileNotFoundException e) {
            try {
                Files.copy(Sauvegarde.class.getResourceAsStream("/data/sauvegardes.json"),
                        Paths.get(FICHIER));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initConfiguration() {
        String cle = Integer.toString(id);
        Object obj = null;
        JSONParser JsonParser = new JSONParser();
        try {
            FileReader r = new FileReader(FICHIER);
            obj = JsonParser.parse(r);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        JSONObject json = (JSONObject) obj;
        assert json != null;
        JSONObject sauvegarde = (JSONObject) json.get(cle);
        if (sauvegarde != null){
            if (sauvegarde.get("date") != null){
                vide = false;
                SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
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
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FICHIER),
                StandardCharsets.UTF_8))) {
            writer.write(sauvegardes.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerSauvegarde(int id){
        JSONObject sauvegardes = new JSONObject();
        JSONObject sauvegarde = initSauvegardeVide();
        sauvegardes.put(String.valueOf(id), sauvegarde);
        ajouterAnciennesSauvegardes(id, sauvegardes);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FICHIER),
                StandardCharsets.UTF_8))) {
            writer.write(sauvegardes.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ajouterAnciennesSauvegardes(int id, JSONObject sauvegardes){
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
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
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
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

    public String getDate() {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        return parser.format(date);
    }

    public String getLocalisation() {
        return localisation.getNom();
    }

    public static void main(String[] args){
        System.out.println(Sauvegarde.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "sauvegardes.json");
    }
}
