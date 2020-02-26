package models;

public class Personnage {

    protected String nom;
    protected Planete localisation;
    protected int pv, puissance, eloquence;

    private static final int PV = 100;
    private static final int PUISSANCE = 1;
    private static final int ELOQUENCE = 1;

    protected Personnage(){
        // ajout initialisation planète //
        this.pv = PV;
        this.puissance = PUISSANCE;
        this.eloquence = ELOQUENCE;
    }

    public Personnage(String nom, Planete localisation, int pv, int puissance, int eloquence) {
        this.nom = nom;
        this.localisation = localisation;
        this.pv = pv;
        this.puissance = puissance;
        this.eloquence = eloquence;
    }

    // getters //

    public String getNom() {
        return nom;
    }

    public Planete getLocalisation() {
        return localisation;
    }

    public int getPv() {
        return pv;
    }

    public int getPuissance() {
        return puissance;
    }

    public int getEloquence() {
        return eloquence;
    }

    // setters //

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLocalisation(Planete localisation) {
        this.localisation = localisation;
    }

    // modificateurs //

    public void modifierPv(int pv) {
        this.pv += pv;
    }

    public void modifierPuissance(int puissance) {
        this.puissance += puissance;
    }

    public void modifierEloquence(int eloquence) {
        this.eloquence += eloquence;
    }
}
