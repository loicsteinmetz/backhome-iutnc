package models;

/**
 * Modélisation d'un personnage
 */
public abstract class Personnage {

    protected String nom;
    protected int pv;

    /**
     * Getter
     * @return nom du personnage
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter
     * @return points de vie du personnage
     */
    public int getPv() {
        return pv;
    }

    /**
     * Setter
     * @param nom nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Ajoute ou retire des points de vie au personnage
     * @param pv points de vie
     */
    public void modifierPv(int pv) {
        this.pv += pv;
    }

    /**
     * Lance une attaque sur un personnage, avec une arme choisie
     * @param cible victime de l'attaque
     * @param a arme utilisée
     */
    public int attaquer(Personnage cible, Arme a){
        return a.attaquer(cible);
    }

    /**
     * Gère les effets d'une attaque sur un personnage
     * @param dommages les pv perdus par le personnage
     */
    public int subirAttaque(double dommages){
        this.modifierPv((int) - dommages);
        return (int)dommages;
    }
    
    /**
     *  @return true si pv<=0 @return false sinon
     */
    public boolean enVie() {
    	return 0<this.pv;
    }
}
