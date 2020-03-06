package models;

/**
 * Modélisation d'une arme générique
 * (utilisées pour les pnj)
 */
public class Arme {

    private int degats;
    private String nom;
    private int id;

    /**
     * Constructeur d'arme générique
     * @param puissance puissance de l'arme
     */
    public Arme(int puissance){
        degats = puissance;
    }

    /**
     * Lance une attaque sur un personnage, avec une arme choisie
     * @param cible victime de l'attaque
     */
    public void attaquer(Personnage cible){
        cible.subirAttaque(this.degats);
    }

    /**
     * Getter
     * @return dégâts de l'arme
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Getter
     * @return nom de l'arme
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param nom nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
