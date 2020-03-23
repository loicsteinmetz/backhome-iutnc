package models;

/**
 * Modélisation d'un item
 */
public abstract class Item {

    protected String nom;
    protected int id;

    /**
     * Constructeur
     * @param id l'id de l'item
     */
    public Item(int id){
        this.id = id;
    }

    /**
     * Getter
     * @return nom de l'item
     */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * Getter
     * @return id de l'arme
     */
    public int getId() {
        return id;
    }

    /**
     * Constructeur servant uniquement aux tests
     */
    public Item(){}
}
