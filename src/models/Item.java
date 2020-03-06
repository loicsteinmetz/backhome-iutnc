package models;

public class Item {

    protected String nom;
    protected int id;

    public Item(int id){
        this.id = id;
    }

    /**
     * Getter
     * @return nom de l'armure
     */
    public String getNom()
    {
        return this.nom;
    }
}
