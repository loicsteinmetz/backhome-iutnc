package models;

/**
 * Modélisation d'une armure
 * (utilisée par le héros)
 */
public class Armure extends Item implements Configurable {
	
	private int resistance;

	/**
	 * Constructeur
	 * @param id l'id de l'armure
	 */
	public Armure(int id) {
		super(id);
		//todo
		this.resistance=id;
		this.initConfiguration();
	}

	/**
	 * Getter
	 * @return resistance de l'armure
	 */
	public int getResistance()
	{
		return this.resistance;
	}

	/**
	 * Initialise les armures
	 */
	@Override
	public void initConfiguration() {

	}
}
