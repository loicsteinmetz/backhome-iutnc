package models;

/**
 * Modélisation d'une arme générique
 * (utilisées pour les pnj)
 */
public class Arme extends Item implements Configurable {

    protected int degats;

    /**
     * Constructeur
     * @param id id de l'arme
     */
    /*public Arme(int id){
        super(id);
        this.initConfiguration();
    }*/

    public Arme(int id) {
    	super(id);
    	//todo
    	this.degats=id;
    }
    
    /**
     * Lance une attaque sur un personnage, avec une arme choisie
     * @param cible victime de l'attaque
     */
    public String attaquer(Personnage cible){
         return cible.subirAttaque(this.degats);
    }

    /**
     * Getter
     * @return dégâts de l'arme
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Getter
     * @return nom de l'arme
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param nom nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Initialise les armes
     */
    @Override
    public void initConfiguration() {

    }
}
