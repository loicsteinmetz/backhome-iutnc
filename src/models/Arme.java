package models;

public abstract class Arme {

    private int degats;
    private String nom;

    public void attaquer(Personnage cible){
        cible.subirAttaque(this.degats);
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
