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
    private Planete localisationPrecedente;

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
        localisationPrecedente = this.localisation;
        this.localisation = localisation;
    }
    
    /**
     * Inflige des dégats au héros en prenant en compte son armure
     */
    @Override
    public int subirAttaque(double dommages){
        int modif = Math.max((int)(dommages - getInventaire().getArmure().getResistance()), 0);
        this.modifierPv(- modif);
        return modif;
    }

    /**
     * Getter
     * @return la situation du héros
     */
    public Situation getSituation() {
        return situation;
    }

    /**
     * Setter
     * @param situation situation du héros
     */
    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    /**
     * Régénère les pv du héros
     */
    public void soin(){
        pv = 100;
    }

    /**
     * Génère une nouvelle instance de Héros
     */
    public static void reset(){
        HEROS = new Heros();
    }

    /**
     * Fait revenir le héros sur la planète précédente
     */
    public void retour(){
        getInventaire().modifierCarburant(localisation.getNiveau() * 100);
        localisation.retablirSoin();
        localisation = localisationPrecedente;
    }
}
