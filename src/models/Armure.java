package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;

/**
 * Modélisation d'une armure
 * (utilisée par le héros)
 */
public class Armure extends Item implements Configurable {
	
	private int resistance;

	/**
	 * Constructeur
	 * @param id l'id de l'armure
	 */
	public Armure(int id) {
		super(id);
		this.initConfiguration();
	}

	/**
	 * Getter
	 * @return resistance de l'armure
	 */
	public int getResistance()
	{
		return this.resistance;
	}

	/**
	 * Récupère les données de configuration de l'armure
	 */
	@Override
	public void initConfiguration() {
		String chemin = "/data/armures.json";
		String cle = Integer.toString(id);
		JSONObject armure = null;
		try {
			new JsonParser();
			armure = JsonParser.parseObject(chemin, cle);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		if (armure != null){
			nom = armure.get("nom").toString();
			resistance = (int) (long) armure.get("resistance");
		}
	}
}
