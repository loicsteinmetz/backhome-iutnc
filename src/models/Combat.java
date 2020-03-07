package models;

import lib.org.json.simple.JSONObject;
import lib.org.json.simple.parser.ParseException;
import utils.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;

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
	 * Récupère les données du combat
	 */
	@Override
    public void initConfiguration() {
		String chemin = "/data/evenements.json";
		String cle = Integer.toString(id);
		JSONObject evenement = null;
		try {
			evenement = new JsonParser().parseObject(chemin, cle);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		if (evenement != null){
			//this.configEnnemi(evenement);// todo
			idIssue = Integer.parseInt(evenement.get("idIssue").toString());
			scenario = (ArrayList<String>) evenement.get("scenario");
		}
    }

	/**
	 * Récupère les données de configuration de l'ennemi du combat
	 * @param evenement l'événement JSONObject créé dans initConfiguration()
	 */
	public void configEnnemi(JSONObject evenement) {
		int idEnnemi = (Integer.parseInt(evenement.get("idEnnemi").toString()));
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
			case "superboss":
				ennemi = new SuperBoss(idEnnemi);
				break;
		}
	}

	public boolean combattre(Ennemi ennemi) {
		Heros heros = getHeros();
		Inventaire Inventaire = getInventaire();
		Scanner sc = new Scanner(System.in);
		while(ennemi.enVie() && heros.	enVie())
		{
			System.out.println("vous avez "+heros.getPv()+" pv");
			System.out.println("votre ennemis a "+ennemi.getPv()+" pv");
			System.out.println("1 pour attaque CaC \n 2 Pour attaque dist");
			int attaque = sc.nextInt();
			switch(attaque){
				case 1 : 
					System.out.println("Votre tour : "+heros.attaquer(ennemi, Inventaire.getArmeCac()));
					break;
				case 2 : 
					System.out.println("Votre tour : "+heros.attaquer(ennemi,Inventaire.getArmeDist()));
					break;
			}
			System.out.println("tour adverse :"+ennemi.attaque(heros));
		}
		if(heros.enVie()) {
			System.out.println("gg");
			return true;
		}
		else {
			System.out.print("looser");
			return false;
		}
	}

	/**
	 * Getter
	 * @return l'id de l'événement suivant
	 */
	public int getIdIssue() {
		return idIssue;
	}
}
