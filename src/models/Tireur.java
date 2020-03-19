package models;

/**
 * Modélisation d'un pnj étant de type "Tireur" au combat
 */
public class Tireur extends Ennemi {

    /**
     * Constructeur
     * @param id l'id du tireur
     */
    public Tireur(int id){
        super(id);
    }

    /**
     * Constructeur servant uniquement aux tests
     * @param pv les pv du combattant
     * @param arme l'arme du combattant
     */
    public Tireur(int pv, Arme arme) {
        super(pv, arme);
    }
}
