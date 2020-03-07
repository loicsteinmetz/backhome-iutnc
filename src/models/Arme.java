package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

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
    public String attaquer(Personnage cible){
         return cible.subirAttaque(this.degats);
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
     * Récupère les données de configuration de la carte
     */
    @Override
    public void initConfiguration() {
        String chemin = "/data/armes.json";
        String cle = Integer.toString(id);
        JSONObject arme = null;
        try {
            arme = new JsonParser().parseObject(chemin, cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (arme != null){
            nom = arme.get("nom").toString();
            degats = (int) (long) arme.get("degats");
        }
    }
}
