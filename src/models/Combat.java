package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Modélisation d'un combat
 */
public class Combat extends Evenement implements Configurable {

    private Ennemi ennemi;
    private int idIssue;

	/**
	 * Constructeur
	 * @param id l'id du combat
	 */
	public Combat(int id){
        super(id);
        initConfiguration();
    }

	/**
	 * Récupère les données de configuration du combat
	 */
	@Override
    public void initConfiguration() {
		String chemin = "/data/evenements.json";
		String cle = Integer.toString(id);
		JSONObject evenement = null;
		try {
			new JsonParser();
			evenement = JsonParser.parseObject(chemin, cle);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		if (evenement != null){
			idIssue = Integer.parseInt(evenement.get("idIssue").toString());
			scenario = (ArrayList<String>) evenement.get("scenario");
			this.configEnnemi(evenement);
		}
    }

	/**
	 * Récupère les données de configuration de l'ennemi du combat
	 * @param evenement l'événement JSONObject créé dans initConfiguration()
	 */
	public void configEnnemi(JSONObject evenement) {
		int idEnnemi = Integer.parseInt(evenement.get("idEnnemi").toString());
		switch (evenement.get("typeEnnemi").toString()) {
			case "tireur":
				ennemi = new Tireur(idEnnemi);
				break;
			case "brute":
				ennemi = new Brute(idEnnemi);
				break;
			case "boss":
				ennemi = new Boss(idEnnemi);
				break;
			case "superBoss":
				ennemi = new SuperBoss(idEnnemi);
				break;
		}
	}

	/**
	 * Getter
	 * @return l'id de l'événement suivant
	 */
	public int getIdIssue() {
		return idIssue;
	}

	/**
	 * Getter
	 * @return l'adversaire du héros
	 */
	public Ennemi getEnnemi() {
		return ennemi;
	}
}
