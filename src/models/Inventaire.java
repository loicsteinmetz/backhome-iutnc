package models;

public class Inventaire {

	@Singleton
	private static Inventaire INVENTAIRE = new Inventaire();

	private int carburant;
	private ArmeCac armeCac;
	private ArmeDistance armeDist;
	private Armure armure;

	/**
	 * Constructeur privé
	 */
	private Inventaire(){
		this.carburant = 0;
		this.armeCac = new ArmeCac(100);
		this.armeDist = new ArmeDistance(101);
		this.armure = new Armure(100);
	}

	/**
	 * Getter de l'instance de l'inventaire (singleton)
	 * @return instance de l'inventaire
	 */
	@Singleton
	public static Inventaire getInventaire(){
        if(INVENTAIRE == null) {
            INVENTAIRE = new Inventaire();
        }
        return INVENTAIRE;
    }

	/**
	 * Ajoute du carburant
	 * @param carburant quantité de carburant
	 */
	public void modifierCarburant(int carburant){
		this.carburant += carburant;
	}
	
	/**
	 *  Getter
	 * @return niveau de carburant
	 */
	public int getCarburant(){
		return this.carburant;
	}

	/**
	 * Setter
	 * @param Arme arme au corps à corps
	 */
	public void setArmeCac(ArmeCac Arme){
		this.armeCac=Arme;
	}

	/**
	 * Getter
	 * @return arme au corps à corps
	 */
	public ArmeCac getArmeCac(){
		return  this.armeCac;
	}

	/**
	 * Setter
	 * @param Arme arme à distance
	 */
	public void setArmeDist(ArmeDistance Arme){
		this.armeDist=Arme;
	}

	/**
	 * Getter
	 * @return arme à distance
	 */
	public ArmeDistance getArmeDist(){
		return  this.armeDist;
	}

	/**
	 * Getter
	 * @return armure
	 */
	public Armure getArmure(){
		return this.armure;
	}

	/**
	 * Setter
	 * @param armure armure
	 */
	public void setArmure(Armure armure){
		this.armure=armure;
	}
}

