package models;

/**
 * Modélisation d'un pnj étant de type "Superboss" au combat
 */
public class SuperBoss extends Boss {

    private Arme armeLegendaire;

    public SuperBoss(int id){
        super(id);
    }

    /**
     * Getter
     * @return l'arme légendaire du superboss
     */
    public Arme getArmeLegendaire() {
        return armeLegendaire;
    }

    @Override
    public void initConfiguration() {

    }
}
