package models;

public class ArmeCac extends Arme {

    @Override
    public void attaquer(Personnage cible) {
        if (cible instanceof Brute){
            cible.subirAttaque(this.getDegats() * 1.5);
        }
        if (cible instanceof Tireur){
            cible.subirAttaque(this.getDegats() * 0.75);
        }

    }
}
