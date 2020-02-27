package models;

/**
 * Modélisation d'une planète
 */
public class Planete {

    private String nom;
    private int niveau;
    private Planete[] planetesVoisines;

    /**
     * Constructeur
     * @param nom nom de la planète
     * @param niveau niveau d'accès requis
     * @param planeteVoisines planètes accessible depuis la planète créée
     */
    public Planete(String nom, int niveau, Planete[] planeteVoisines) {
        this.nom = nom;
        this.niveau = niveau;
        this.planetesVoisines = planeteVoisines;
    }

    /**
     * Getter
     * @return nom de la planète
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter
     * @return niveau d'accès requis
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Getter
     * @return planètes accessible depuis la planète créée
     */
    public Planete[] getPlanetesVoisines() {
        return planetesVoisines;
    }

}
