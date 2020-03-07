package models;

/**
 * Modélisation d'un ennemi
 * (pnj pouvant être combattu)
 */
public abstract class Ennemi extends Personnage implements Configurable {

    protected String description;
    protected Arme arme;
    protected int id;

    /**
     * Constructeur
     * @param id l'id de l'ennemi
     */
    public Ennemi(int id){
        this.id = id;
        this.initConfiguration();
    }
    

    /**
     * Getter
     * @return la description de l'ennemi
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter
     * @return l'arme de l'ennemi
     */
    public Arme getArme() {
        return arme;
    }

    /**
     * Initialise les tireurs, brutes, et boss
     */
    @Override
    public void initConfiguration() {

    }
    
    public String attaque(Personnage heros){
    	return this.getArme().attaquer(heros);
    }

}
