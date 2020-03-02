package models;

public class ArmeCac extends Arme {

    @Override
    public void attaquer(Personnage cible) {
        cible.subirAttaque();
    }
}
