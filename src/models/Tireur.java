package models;

public class Tireur extends Personnage {

    Arme arme;

    public Tireur (Arme a) {
        super();
        arme = a;
    }

    public Arme getArme() {
        return arme;
    }

    public void attaquer(Personnage cible, Arme a){
        super.attaquer(cible, arme);
    }
}
