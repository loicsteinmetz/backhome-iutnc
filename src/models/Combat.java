package models;

import static models.Inventaire.getInventaire;
import static models.Heros.getHeros;
import models.Personnage;
import java.util.Scanner;

public class Combat extends Evenement implements Configurable {

    private Ennemi ennemi;
    private Evenement issue;

    public Combat(int id){
        super(id);
        initConfiguration();
    }

    @Override
    public void initConfiguration() {

    }
	
	public boolean combattre(Brute ennemi) {
		Heros heros = getHeros();
		Inventaire Inventaire = getInventaire();
		Scanner sc = new Scanner(System.in);		
		while(ennemi.enVie() || heros.enVie())
		{
			System.out.println("vous avez "+heros.getPv());
			System.out.println("votre ennemis a "+ennemi.getPv());
			System.out.println("1 pour attaque CaC \n 2 Pour attaqu dist");
			int attaque = sc.nextInt();
			switch(attaque){
				case 1 : 
					heros.attaquer(ennemi, Inventaire.getArmeCac());
					System.out.println("vous infligez "+Inventaire.getArmeCac().getDegats());
					break;
				case 2 : 
					heros.attaquer(ennemi,Inventaire.getArmeDist());
					System.out.println("vous infligez "+Inventaire.getArmeDist().getDegats());
					break;
			}
			ennemi.attaquer(heros);
			System.out.println("l'ennemi vous inflige "+ (ennemi.getArme().getDegats()-Inventaire.getArmure().getResistance()));
			
			System.out.println("il vous reste "+heros.getPv());
			System.out.println("il reste "+ennemi.getPv()+" a votre ennemi");
		}
		if(heros.enVie()) {
			return true;
		}
		else {
			return false;
		}
	}
}
