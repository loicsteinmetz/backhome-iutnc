package models;

public class Planete {

    private String nom;
    private int niveau;
    private Planete[] planetesVoisines;

    public Planete(String nom, int niveau, Planete[] planeteVoisines) {
        this.nom = nom;
        this.niveau = niveau;
        this.planetesVoisines = planeteVoisines;
    }

    // getters //

    public String getNom() {
        return nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public Planete[] getPlanetesVoisines() {
        return planetesVoisines;
    }

}
