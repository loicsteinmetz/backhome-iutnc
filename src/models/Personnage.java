package models;

/**
 * Modélisation d'un personnage
 */
public abstract class Personnage {

    protected String nom;
    protected int pv;

    private static final int PV = 100;

    /**
     * Constructeur avec paramètres par défaut
     */
    public Personnage(){
        // ajout initialisation planète //
        this.pv = PV;
    }

    /**
     * Constructeur paramétrable
     * @param nom nom du personnage
     * @param pv points de vie du personnage
     */
    public Personnage(String nom, int pv) {
        this.nom = nom;
        this.pv = pv;
    }

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
     *
     */
    public abstract void attaquer(Personnage cible);
    public abstract void subirAttaque();
}
