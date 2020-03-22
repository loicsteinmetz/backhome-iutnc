package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;
import java.util.Random;
import java.io.IOException;

/**
 * Modélisation d'une arme générique
 * (utilisées pour les pnj)
 */
public class Arme extends Item implements Configurable {

    protected int degats;

    /**
     * Constructeur
     * @param id id de l'arme
     */
    public Arme(int id){
        super(id);
        this.initConfiguration();
    }

    /**
     * Lance une attaque sur un personnage, avec une arme choisie
     * @param cible victime de l'attaque
     */
    public int attaquer(Personnage cible){
    	int prctAttaque = this.rdm();
        return cible.subirAttaque(this.degats+prctAttaque);
    }

    /**
     * Getter
     * @return dégâts de l'arme
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Getter
     * @return nom de l'arme
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param nom nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Génère une altération aléatoire des dégats
     */
    public int rdm() {
    	int max = (int)this.degats/2;
    	int min = -10;
        Random r = new Random();
        int rmd =r.nextInt((max - min) + 1) + min;
        int prctAttaque = ((this.degats*rmd)/100);
        return(prctAttaque);
    }

    /**
     * Récupère les données de configuration de l'arme
     */
    @Override
    public void initConfiguration() {
        String chemin = "/data/armes.json";
        String cle = Integer.toString(id);
        JSONObject arme = null;
        try {
            new JsonParser();
            arme = JsonParser.parseObject(chemin, cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (arme != null){
            nom = arme.get("nom").toString();
            degats = (int) (long) arme.get("degats");
        }
    }

    /**
     * Constructeur servant uniquement aux tests
     * @param degats les dégâts de l'arme
     */
    public Arme(double degats){
        super();
        this.degats = (int) degats;
    }
}
