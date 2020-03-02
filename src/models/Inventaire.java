package models;

public class Inventaire {
	
	private static Inventaire Inventaire= new Inventaire();
	protected int carburant;
	protected ArmeCac armeCac;
	protected ArmeDistance armeDist;
	
	public Inventaire()
	{
		this.carburant = 0;
		this.armeCac = null;
		this.armeDist = null;
	}
	
	
	public static Inventaire getInventaire(){
        if(Inventaire== null) {
            Inventaire= new Inventaire();
        }
        return Inventaire;
    }
	
	
	
	/* Modifie l'attribut carburant en ajoutant
	 * @param carbu 
	 */
	public void modifierCarburant(int carbu)
	{
		this.carburant += carbu;
	}
	
	/*
	 * 	recupere le carburant 
	 */
	public int getCarburant()
	{
		return this.carburant;
	}
	
	public void modifierArmeCac(ArmeCac Arme)
	{
		this.armeCac=Arme;
	}
	
	public ArmeCac getArmeCac()
	{
		return  this.armeCac;
	}
	
	public void modifierArmeDist(ArmeDistance Arme)
	{
		this.armeDist=Arme;
	}
	
	public ArmeDistance getArmeDist()
	{
		return  this.armeDist;
	}
}

