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
    
    public Ennemi(String nom,int pv,Arme a) {
    	//todo
    	super(nom,pv);
    	this.arme=a;
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
    
    public void attaque(Personnage heros){
    	this.getArme().attaquer(heros);
    }

}
