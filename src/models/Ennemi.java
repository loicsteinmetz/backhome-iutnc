package models;

public abstract class Ennemi extends Personnage {

    private String description;
    private Arme arme;

    /**
     * Getter
     * @return la description de l'ennemi
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter
     * @return l'arme de l'ennemi
     */
    public Arme getArme() {
        return arme;
    }
}
