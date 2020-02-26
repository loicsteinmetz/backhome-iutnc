package models;

public class Heros extends Personnage {

    /**
     * pré-initialise l'instance du héros
     * avec stats par défaut, sans nom
     */
    private static Heros HEROS = new Heros();

    private Heros(){
        super();
    }

    public static Heros getHeros(){
        if(HEROS == null) {
            HEROS = new Heros();
        }
        return HEROS;
    }
}
