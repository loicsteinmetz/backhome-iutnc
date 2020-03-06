package models;

/**
 * Modélisation d'une armure
 * (utilisée par le héros)
 */
public class Armure {
	
	private int resistance;
	private String nom;
	private int id;

	/**
	 * Constructeur d'armure
	 * @param res resistance de l'armure
	 * @param nom nom de l'armure
	 */
	public Armure(int res, String nom)
	{
		this.resistance = res;
		this.nom = nom;
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
	 * Getter
	 * @return nom de l'armure
	 */
	public String getNom()
	{
		return this.nom;
	}
}
