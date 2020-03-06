package models;

/**
 * Modélisation d'une arme générique
 * (utilisées pour les pnj)
 */
public class Arme extends Item implements Configurable {

    protected int degats;


    public Arme(int id){
        super(id);
        this.initConfiguration();
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

    @Override
    public void initConfiguration() {

    }
}
