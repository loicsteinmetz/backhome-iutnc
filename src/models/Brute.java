package models;

/**
 * Modélisation d'un pnj étant de type "Brute" au combat
 */
public class Brute extends Personnage {

    Arme arme;

    /**
     * Constructeur paramétrable
     * @param nom nom du personnage
     * @param pv points de vie du personnage
     * @param a arme du personnage
     */
    public Brute (String nom, int pv, Arme a) {
        super(nom, pv);
        arme = a;
    }

    /**
     * Getter
     * @return l'arme qu'utilise le personnage
     */
    public Arme getArme() {
        return arme;
    }

    /**
     * Lance une attaque sur un personnage, avec son arme
     * @param cible victime de l'attaque
     */
    public void attaquer(Personnage cible){
        super.attaquer(cible, arme);
    }

}
