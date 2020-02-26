package models;

public class Personnage {

    protected String nom;
    protected int pv;
    protected int puissance;
    protected int eloquence;

    private static final int PV = 100;
    private static final int PUISSANCE = 1;
    private static final int ELOQUENCE = 1;

    protected Personnage(){
        this.pv = PV;
        this.puissance = PUISSANCE;
        this.eloquence = ELOQUENCE;
    }

    public Personnage(String nom, int pv, int puissance, int eloquence) {
        this.nom = nom;
        this.pv = pv;
        this.puissance = puissance;
        this.eloquence = eloquence;
    }

    public String getNom() {
        return nom;
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

    // setter nom //

    public void setNom(String nom) {
        this.nom = nom;
    }

    // modificateurs stats (ajouts/retraits) //
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
