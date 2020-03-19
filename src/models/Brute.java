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
     * @param arme l'arme du combattant
     */
    public Brute(Arme arme) {
        super(arme);
    }
}
