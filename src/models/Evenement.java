package models;

import java.util.ArrayList;

/**
 * Modélisation d'un événement
 */
public class Evenement {

    protected int id;
    protected ArrayList<String> scenario;

    /**
     * Constructeur
     * @param id l'id de l'événement
     */
    public Evenement(int id) {
        this.id = id;
    }

    /**
     * Getter
     * @return scénario
     */
    public ArrayList<String> getScenario() {
        return scenario;
    }
}
