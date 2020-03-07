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
		while(ennemi.enVie() && heros.enVie())
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
			ennemi.attaque(heros);
			System.out.println("l'ennemi vous inflige "+ (ennemi.getArme().getDegats()-Inventaire.getArmure().getResistance()));
			
			System.out.println("il vous reste "+heros.getPv());
			System.out.println("il reste "+ennemi.getPv()+" a votre ennemi");
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
		Brute goblin = new Brute("goblin",15,a);
		System.out.println(getHeros().getPv());
		System.out.println(goblin.getPv());
		c.combattre(goblin);
	 }
}
