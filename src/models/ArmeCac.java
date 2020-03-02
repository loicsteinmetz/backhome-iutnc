package models;

/**
 * Modélisation d'une arme au corps à corps
 * (utilisée pour le héros)
 */
public class ArmeCac extends Arme {

    /**
     * Constructeur d'arme au cac
     * @param puissance
     * @param nom
     */
    public ArmeCac(int puissance, String nom){
        super(puissance);
        this.setNom(nom);
    }

    /**
     * Lance une attaque sur un personnage
     * @param cible victime de l'attaque
     */
    @Override
    public void attaquer(Personnage cible) {
        if (cible instanceof Brute){
            cible.subirAttaque(this.getDegats() * 0.75);
        }
        if (cible instanceof Tireur){
            cible.subirAttaque(this.getDegats() * 1.5);
        }
    }

}
