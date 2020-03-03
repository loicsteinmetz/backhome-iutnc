package models;

public class Armure {
	
	private int resistance;
	private String nom;
	
	public Armure(int res,String nom)
	{
		this.resistance = res;
		this.nom = nom;
	}
	
	public int getResistance()
	{
		return this.resistance;
	}
	
	public void setResistance(int res)
	{
		this.resistance = res;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}

}
