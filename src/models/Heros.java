package models;

import static models.Carte.getCarte;

/**
 * Singleton générant le héros du jeu
 */
public class Heros extends Personnage {

    private static Heros HEROS = new Heros();
    private Planete localisation;

    /**
     * Constructeur privé
     */
    private Heros(){
        super();
        this.localisation = getCarte().getPlaneteParNom("utopia");
    }

    /**
     * Getter
     * @return localisation du héros
     */
    public Planete getLocalisation() {
        return localisation;
    }

    /**
     * Setter
     * @param localisation localisation du héros
     */
    public void setLocalisation(Planete localisation) {
        this.localisation = localisation;
    }

    /**
     * Getter de l'instance du héros (singleton)
     * @return l'instance du héros
     */
    public static Heros getHeros(){
        if(HEROS == null) {
            HEROS = new Heros();
        }
        return HEROS;
    }
}
