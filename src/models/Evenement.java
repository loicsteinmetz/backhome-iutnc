package models;

import java.util.ArrayList;

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

    public ArrayList<String> getScenario() {
        return scenario;
    }
}
