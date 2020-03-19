package models;

/**
 * Modélisation d'un pnj étant de type "Boss" au combat
 */
public class Boss extends Ennemi {

    /**
     * Constructeur
     * @param id l'id du boss
     */
    public Boss(int id){
        super(id);
    }

    /**
     * Constructeur servant uniquement aux tests
     * @param pv les pv du combattant
     * @param arme l'arme du combattant
     */
    public Boss(int pv, Arme arme) {
        super(pv, arme);
    }
}
