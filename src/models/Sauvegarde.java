package models;

import lib.org.json.simple.JSONArray;
import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.JSONParser;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static models.Carte.getCarte;
import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;

public class Sauvegarde {

    private final static String FORMAT = "dd-M-yyyy hh:mm";
    private final static String CHEMIN = "/data/sauvegardes.json";
    private final static int NB_SAUVEGARDES = 5;
    private int id;
    private Planete localisation;
    private String date;
    private static Sauvegarde[] SAUVEGARDES = new Sauvegarde[NB_SAUVEGARDES];

    /**
     * Constructeur
     * @param id id de la sauvegarde
     */
    public Sauvegarde(int id){
        this.id = id;
        JSONObject save = null;
        try {
            save = new JsonParser().parseObject(CHEMIN,Integer.toString(id));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (save != null){
            localisation = getCarte().getPlaneteParNom(save.get("localisation").toString());
            date = save.get("date").toString();
        }
    }

    /**
     * Initialise SAUVEGARDES, le tableau des sauvegardes
     * ( à appeler au début du jeu )
     */
    public static void chargerAffSauvegardes(){
        for (int i=0; i<NB_SAUVEGARDES ; i++) {
            if (SAUVEGARDES[i] == null) SAUVEGARDES[i] = new Sauvegarde(i+1);
        }
    }

    /**
     * Récupère l'état du jeu d'une sauvegarde
     */
    public void chargerSauvegarde(){
        JSONObject save = null;
        try {
            save = new JsonParser().parseObject(CHEMIN,Integer.toString(id));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (save != null){
            getInventaire().setArmeCac(new ArmeCac((int)(long)save.get("idArmeCac")));
            getInventaire().setArmeDist(new ArmeDistance((int)(long)save.get("idArmeDistance")));
            getInventaire().setArmure(new Armure((int)(long)save.get("idArmure")));
            getInventaire().modifierCarburant((int)(long)save.get("carbu"));
            getHeros().setLocalisation(getCarte().getPlaneteParNom(save.get("localisation").toString()));

            JSONArray a = (JSONArray) save.get("planetesVisitees");
            for(Object p : a){
                if (p != null) {
                    Planete planete = getCarte().getPlaneteParNom(p.toString());
                    planete.setVisitee();
                }
            }
        }
    }

    /**
     * Retranscrit l'état du jeu dans une sauvegarde dans sauvegardes.json
     * et modifie les attributs de l'objet Sauvegarde correspondant
     * ( appelle recupererEtat() et toJson() )
     */
    public void sauvegarder(){
        // todo utiliser chemin relatif pour le FileWriter
        // distingue la sauvegarde à modifier de celles à retranscrire
        JSONObject saveModifiee = null;
        JSONObject savesConst = null;
        try {
            saveModifiee = new JsonParser().parseObject(CHEMIN,Integer.toString(id));
            savesConst = (JSONObject) new JSONParser().parse(new InputStreamReader(getClass().getResourceAsStream(CHEMIN)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        recupererEtat(saveModifiee);
        // écrit les nouvelles valeurs dans la sauvegarde correspondante
        try (FileWriter file = new FileWriter("/home/friedrich/Programmation/java/projet_jeu_iutnc/src/data/sauvegardes.json")) {
            file.write("{ \"" + id + "\":" + saveModifiee.toJSONString());
            for (int i=1 ; i<=NB_SAUVEGARDES ; i++){
                if(i != id)
                    file.write(toJson((JSONObject) savesConst.get(Integer.toString(i)),i));
            }
            file.write("}");
            file.close();
            // actualise les attributs de l'objet sauvegarde
            localisation = getHeros().getLocalisation();
            date = new SimpleDateFormat(FORMAT).format(new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formatte l'écriture d'une sauvegarde dans le .json
     * @param save le JSONObject à formatter
     * @param id l'id de la sauvegarde correspondante
     * @return un string
     */
    private static String toJson(JSONObject save, int id){
        return ",\n \"" + id + "\":" + save.toJSONString();
    }

    /**
     * Affecte l'état actuel du jeu au JSONObject
     * @param saveModifiee JSONObject à traiter
     */
    private static void recupererEtat(JSONObject saveModifiee){
        saveModifiee.put("date", new SimpleDateFormat(FORMAT).format(new Date()));
        saveModifiee.put("idArmeDistance", getInventaire().getArmeDist().getId());
        saveModifiee.put("idArmeCac", getInventaire().getArmeCac().getId());
        saveModifiee.put("idArmure", getInventaire().getArmure().getId());
        saveModifiee.put("carbu", getInventaire().getCarburant());
        saveModifiee.put("localisation", getHeros().getLocalisation().getNom());
        ArrayList<Planete> list = getCarte().getPlanetes();
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++){
            if(list.get(i).getVisitee() == true) array[i] = list.get(i).getNom();
        }
        saveModifiee.put("planetesVisitees", array);
    }

    /**
     * Test si une sauvegarde est vide
     * @return true si la sauvegarde est vide
     */
    public boolean estVide(){
        return date == null;
    }

    /**
     * Efface une sauvegarde aux yeux du joueur
     */
    public void effacerSauvegarde(){
        date = null;
        localisation = null;
    }

    /**
     * Getter
     * @return id de la sauvegarde
     */
    public int getId() {
        return id;
    }

    /**
     * Getter
     * @return localisation du héros au moment de la sauvegarde
     */
    public Planete getLocalisation() {
        return localisation;
    }

    /**
     * Getter
     * @return date de la sauvegarde
     */
    public String getDate() {
        return date;
    }

    public static void main (String[] args) {
        chargerAffSauvegardes();
        Heros h = getHeros();
        Inventaire i = getInventaire();
        Sauvegarde save = SAUVEGARDES[0];
//      /*
        System.out.println(save.getId() + " : " + save.getDate() + " : " + save.getLocalisation().getNom());
        System.out.println(h.getLocalisation().getNom());
        System.out.println(i.getArmeCac().getNom());
        System.out.println(i.getArmeDist().getNom());
        System.out.println(i.getArmure().getNom());
        System.out.println(i.getCarburant());
//      */
        for(Sauvegarde s : SAUVEGARDES) s.effacerSauvegarde();
//      /*
        //todo pq il fait que le dernier ce fils de timp
        SAUVEGARDES[2].sauvegarder();
        System.out.println("*");
        SAUVEGARDES[3].sauvegarder();
        System.out.println("*");
        SAUVEGARDES[4].sauvegarder();
        System.out.println("*");
        SAUVEGARDES[0].sauvegarder();
        System.out.println("*");
        SAUVEGARDES[1].sauvegarder();
        System.out.println("*");
//      */
    }
}
