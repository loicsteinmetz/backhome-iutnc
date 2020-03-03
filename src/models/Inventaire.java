package models;

public class Inventaire {
	
	private static Inventaire INVENTAIRE = new Inventaire();
	protected int carburant;
	protected ArmeCac armeCac;
	protected ArmeDistance armeDist;
	
	private Inventaire()
	{
		this.carburant = 0;
		this.armeCac = new ArmeCac(10,"petit couteau");
		this.armeDist = new ArmeDistance(15,"petit pistolet");
	}
	
	
	public static Inventaire getInventaire(){
        if(INVENTAIRE == null) {
            INVENTAIRE = new Inventaire();
        }
        return INVENTAIRE;
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
	
	public void setArmeCac(ArmeCac Arme)
	{
		this.armeCac=Arme;
	}
	
	public ArmeCac getArmeCac()
	{
		return  this.armeCac;
	}
	
	public void setArmeDist(ArmeDistance Arme)
	{
		this.armeDist=Arme;
	}
	
	public ArmeDistance getArmeDist()
	{
		return  this.armeDist;
	}
}

