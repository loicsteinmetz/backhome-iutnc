package models;

/**
 * Modélisation d'un pnj étant de type "Superboss" au combat
 */
public class SuperBoss extends Boss {

    private Arme armeLegendaire;

    /**
     * Constructeur
     * @param id l'id du superboss
     */
    public SuperBoss(int id){
        super(id);
    }
    
    //todo
    public SuperBoss(String nom,int pv,ArmeDistance a) {
    	super(nom,pv,a);
    }

    /**
     * Getter
     * @return l'arme légendaire du superboss
     */
    public Arme getArmeLegendaire() {
        return armeLegendaire;
    }

    /**
     * Initialise les superboss
     */
    @Override
    public void initConfiguration() {

    }
}
