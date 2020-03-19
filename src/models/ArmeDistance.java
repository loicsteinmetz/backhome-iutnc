package models;

import static models.Inventaire.getInventaire;

/**
 * Modélisation d'une arme à distance (utilisée pour le héros)
 */
public class ArmeDistance extends Arme {

	/**
	 * Constructeur
	 * @param id l'id de l'arme
	 */
	public ArmeDistance(int id) {
		super(id);
	}

	/**
	 * Lance une attaque sur un personnage
	 * @param cible victime de l'attaque
	 */
	@Override
	public int attaquer(Personnage cible) {		
	int prctAttaque = this.rdm();
		if (cible instanceof Brute) {
			cible.subirAttaque(this.getDegats() * 1.5+prctAttaque);
			return (int)(this.getDegats() * 1.5+prctAttaque);
		} else {
			if (cible instanceof Tireur) {
				cible.subirAttaque(this.getDegats() * 0.75+prctAttaque);
				return (int)(this.getDegats() * 0.75+prctAttaque);
			} else {
				if (cible instanceof Heros) {
					cible.subirAttaque(this.getDegats()+prctAttaque);
					return this.getDegats() - getInventaire().getArmure().getResistance()+prctAttaque;
				} else {
					cible.subirAttaque(this.getDegats()+prctAttaque);
					return this.getDegats()+prctAttaque;
				}
			}
		}
	}

	/**
	 * Constructeur servant uniquement aux tests
	 * @param id l'id de l'arme
	 * @param degats les degats de l'arme
	 */
	public ArmeDistance(int id, int degats){
		super(id);
		this.degats = degats;
	}
}
