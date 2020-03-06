package models;

import static models.Carte.getCarte;

/**
 * Singleton modélisant le héros du jeu
 */
public class Heros extends Personnage {

    private static Heros HEROS = new Heros();
    private Planete localisation;
    private Situation situation;

    /**
     * Constructeur privé
     */
    private Heros(){
        super();
        localisation = getCarte().getPlaneteParNom("utopia");
        situation = Situation.DEBUT;
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

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
