package models;

import java.util.ArrayList;

import static models.Inventaire.getInventaire;

/**
 * Modélisation d'une planète
 */
public class Planete {

    private String nom;
    private int niveau;
    private ArrayList<Planete> planetesVoisines = new ArrayList<>();
    private String description;
    private int idPremierEvenement;
    private int idRecompenseArmeCac;
    private int idRecompenseDistance;
    private int idRecompenseArmure;
    private boolean soinDisponible;

    /**
     * Constructeur
     * @param nom nom de la planète
     * @param niveau niveau d'accès requis
     */
    public Planete(String nom, int niveau, String description,
                   int idPremierEvenement, int idRecompenseArmeCac,
                   int idRecompenseDistance, int idRecompenseArmure) {
        this.nom = nom;
        this.niveau = niveau;
        this.description = description;
        this.idPremierEvenement = idPremierEvenement;
        this.idRecompenseArmeCac = idRecompenseArmeCac;
        this.idRecompenseDistance = idRecompenseDistance;
        this.idRecompenseArmure = idRecompenseArmure;
        this.soinDisponible = true;
    }

    /**
     * Getter
     * @return nom de la planète
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter
     * @return niveau d'accès requis
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Getter
     * @return planètes accessible depuis la planète créée
     */
    public ArrayList<Planete> getPlanetesVoisines() {
        return planetesVoisines;
    }

    /**
     * Getter
     * @return description de la planète
     */
    public String getDescription(){
        return description;
    }

    /**
     * Ajoute une planète voisine
     * @param p Planète à ajouter aux planètes voisines
     */
    public void nouvellePlaneteVoisine(Planete p) {
        this.planetesVoisines.add(p);
    }

    /**
     * Getter
     * @return l'id du premier événement de la planète
     */
    public int getIdPremierEvenement() {
        return idPremierEvenement;
    }

    public void recompenses(){
        if (idRecompenseArmeCac != -1){
            getInventaire().setArmeCac(new ArmeCac(idRecompenseArmeCac));
        }
        if (idRecompenseDistance != -1){
            getInventaire().setArmeDist(new ArmeDistance(idRecompenseDistance));
        }
        if (idRecompenseArmure != -1){
            getInventaire().setArmure(new Armure(idRecompenseArmure));
        }
    }

    public boolean soinDisponible(){
        return soinDisponible;
    }

    public void consommerSoin(){
        soinDisponible = false;
    }
}
