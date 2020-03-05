package models;

/**
 * Modélisation d'une arme à distance
 * (utilisée pour le héros)
 */
public class ArmeDistance extends Arme {

    /**
     * Constructeur d'arme à distance
     * @param puissance puissance de l'arme
     * @param nom nom de l'arme
     */
    public ArmeDistance(int puissance, String nom){
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
            cible.subirAttaque(this.getDegats() * 1.5);
        }
        if (cible instanceof Tireur){
            cible.subirAttaque(this.getDegats() * 0.75);
        }
    }

}
