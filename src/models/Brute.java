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

    /**
     * Constructeur servant uniquement aux tests
     * @param pv les pv du combattant
     * @param arme l'arme du combattant
     */
    public Brute(int pv, Arme arme) {
        super(pv, arme);
    }
}
