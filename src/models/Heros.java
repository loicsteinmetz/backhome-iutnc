package models;

public class Heros {

    private static final int PV = 100;
    private static final int PUISSANCE = 1;
    private static final int ELOQUENCE = 1;

    private String nom;
    private int pv;
    private int puissance;
    private int eloquence;

    /**
     * pré-initialise l'instance du héros
     * avec stats par défaut, sans nom
     */
    private static Heros HEROS = new Heros();

    private Heros(){
        this.pv = PV;
        this.puissance = PUISSANCE;
        this.eloquence = ELOQUENCE;
    }

    /**
     * personnalise le nom du héros
     * @param n le nom choisi par le joueur
     */
    public static void nommer(String n){
        getHeros().nom = n;
    }

    public static Heros getHeros(){
        if(HEROS == null) {
            HEROS = new Heros();
        }
        return HEROS;
    }

    // getter //
    public static String getNom(){
        return getHeros().nom;
    }

    public static int getPv(){
        return getHeros().pv;
    }

    public static int getPuissance(){
        return getHeros().puissance;
    }

    public static int getEloquence(){
        return getHeros().eloquence;
    }

    // modificateurs stats (ajouts/retraits) //
    public void modifierPv(int pv) {
        this.pv += pv;
    }

    public void modifierPuissance(int puissance) {
        this.puissance += puissance;
    }

    public void modifierEloquence(int eloquence) {
        this.eloquence += eloquence;
    }
}
