package models;

import static models.Carte.getCarte;
import static models.Inventaire.getInventaire;

/**
 * Singleton modélisant le héros du jeu
 */
public class Heros extends Personnage {

    @Singleton
    private static Heros HEROS = new Heros();

    private Planete localisation;
    private Situation situation;

    /**
     * Constructeur privé
     */
    private Heros(){
        pv = 100;
        localisation = getCarte().getPlaneteParNom("utopia");
        situation = Situation.DEBUT;
    }

    /**
     * Getter de l'instance du héros (singleton)
     * @return l'instance du héros
     */
    @Singleton
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
    
    /**
     * @Override de la méthode subir attaque
     * pour prendre en compte l'armure du héros
     */
    public int subirAttaque(double dommages){
        this.modifierPv((int) -(dommages-getInventaire().getArmure().getResistance()));
        return (int) (dommages-getInventaire().getArmure().getResistance());
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
