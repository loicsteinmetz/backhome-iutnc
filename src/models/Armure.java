package models;

/**
 * Modélisation d'une armure
 * (utilisée par le héros)
 */
public class Armure extends Item implements Configurable {
	
	private int resistance;


	public Armure(int id) {
		super(id);
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



	@Override
	public void initConfiguration() {

	}
}
