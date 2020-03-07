package models;

import static models.Inventaire.getInventaire;
import static models.Heros.getHeros;
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
	
	 public static void main(String[] args) {
		Combat c = new Combat(1);
		ArmeCac a = new ArmeCac(5);
		ArmeDistance ad = new ArmeDistance(7);
		Brute goblin = new Brute("goblin",15,a);
		Tireur tir = new Tireur("tir",15,ad);
		Boss boss = new Boss("le malefique",15,a);
		SuperBoss Sb = new SuperBoss("tavernier",20,ad);
		c.combattre(goblin);
		c.combattre(tir);
		c.combattre(boss);
		c.combattre(Sb);
	 }
}
