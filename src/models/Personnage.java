package models;

/**
 * Modélisation d'un personnage
 */
public class Personnage {

    protected String nom;
    protected int pv, puissance, eloquence;

    private static final int PV = 100;
    private static final int PUISSANCE = 1;
    private static final int ELOQUENCE = 1;

    /**
     * Constructeur avec paramètres par défaut
     */
    public Personnage(){
        // ajout initialisation planète //
        this.pv = PV;
        this.puissance = PUISSANCE;
        this.eloquence = ELOQUENCE;
    }

    /**
     * Constructeur paramétrable
     * @param nom nom du personnage
     * @param pv points de vie du personnage
     * @param puissance puissance du personnage
     * @param eloquence éloquence du personnage
     */
    public Personnage(String nom, int pv, int puissance, int eloquence) {
        this.nom = nom;
        this.pv = pv;
        this.puissance = puissance;
        this.eloquence = eloquence;
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
     * Getter
     * @return puissance du personnage
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * Getter
     * @return éloquence du personnage
     */
    public int getEloquence() {
        return eloquence;
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
     * Ajoute ou retire des points de puissance au personnage
     * @param puissance points de puissance
     */
    public void modifierPuissance(int puissance) {
        this.puissance += puissance;
    }

    /**
     * Ajoute ou retire des points d'éloquence au personnage
     * @param eloquence points d'éloquence
     */
    public void modifierEloquence(int eloquence) {
        this.eloquence += eloquence;
    }
}
