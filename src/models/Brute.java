package models;

/**
 * Modélisation d'un pnj étant de type "Brute" au combat
 */
public class Brute extends Ennemi {

    /**
     * Constructeur
     * @param id l'id de la brute
     */
    public Brute(int id){
        super(id);
    }
    
    public Brute(String nom,int pv,ArmeCac a) {
    	super(nom,pv,a);
    }

}
