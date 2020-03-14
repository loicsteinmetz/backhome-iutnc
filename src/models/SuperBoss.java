package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import static models.Heros.getHeros;

import java.io.IOException;
import java.lang.Math; 

/**
 * Modélisation d'un pnj étant de type "Superboss" au combat
 */
public class SuperBoss extends Boss {

    private Arme armeLegendaire;

    /**
     * Constructeur
     * @param id l'id du superboss
     */
    public SuperBoss(int id){
        super(id);
    }
    

    /**
     * Getter
     * @return l'arme légendaire du superboss
     */
    public Arme getArmeLegendaire() {
        return armeLegendaire;
    }
    
    /**
     * Traite l'attaque d'un ennemi sur le héros
     * @return degats
     */
    public int attaque(){
    	double rdm = Math.random();
    	if(rdm>0.75) {
    		return this.getArmeLegendaire().attaquer(getHeros());
    	}
    	else {
    		return this.getArme().attaquer(getHeros());
    	}    	
    }


    /**
     * Récupère les données de configuration du superboss
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
            int idArmeLegendraire = (Integer.parseInt(ennemi.get("idArmeLegendaire").toString()));
            arme = new Arme(idArme);
            armeLegendaire = new Arme(idArmeLegendraire);
        }
    }
}
