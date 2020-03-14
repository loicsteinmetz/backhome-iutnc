package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;

import static models.Heros.getHeros;

/**
 * Modélisation d'un ennemi
 * (pnj pouvant être combattu)
 */
public abstract class Ennemi extends Personnage implements Configurable {

    protected String description;
    protected Arme arme;
    protected int id;

    /**
     * Constructeur
     * @param id l'id de l'ennemi
     */
    public Ennemi(int id){
        this.id = id;
        this.initConfiguration();
    }

    /**
     * Getter
     * @return la description de l'ennemi
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter
     * @return l'arme de l'ennemi
     */
    public Arme getArme() {
        return arme;
    }

    /**
     * Récupère les données de configuration de l'ennemi
     */
    @Override
    public void initConfiguration() {
        String chemin = "/data/ennemis.json";
        String cle = Integer.toString(id);
        JSONObject ennemi = null;
        try {
            new JsonParser();
            ennemi = JsonParser.parseObject(chemin, cle);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (ennemi != null){
            nom = ennemi.get("nom").toString();
            description = ennemi.get("description").toString();
            pv = (int) (long) ennemi.get("pv");
            int idArme = (Integer.parseInt(ennemi.get("idArme").toString()));
            arme = new Arme(idArme);
        }
    }

    /**
     * Traite l'attaque d'un ennemi sur le héros
     * @return degats
     */
    public int attaque(){
    	return this.getArme().attaquer(getHeros());
    }

}
