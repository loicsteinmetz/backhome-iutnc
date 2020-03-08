package models;

import static models.Inventaire.getInventaire;

/**
 * Modélisation d'une arme à distance (utilisée pour le héros)
 */
public class ArmeDistance extends Arme {

	/**
	 * Constructeur
	 * 
	 * @param id l'id de l'arme
	 */
	public ArmeDistance(int id) {
		super(id);
	}

	/**
	 * Lance une attaque sur un personnage
	 * 
	 * @param cible victime de l'attaque
	 */
	@Override
	public int attaquer(Personnage cible) {
		if (cible instanceof Brute) {
			cible.subirAttaque(this.getDegats() * 1.5);
			return (int)(this.getDegats() * 1.5);
		} else {
			if (cible instanceof Tireur) {
				cible.subirAttaque(this.getDegats() * 0.75);
				return (int)(this.getDegats() * 0.75);
			} else {
				if (cible instanceof Heros) {
					cible.subirAttaque(this.getDegats());
					return this.getDegats() - getInventaire().getArmure().getResistance();
				} else {
					cible.subirAttaque(this.getDegats());
					return this.getDegats();
				}
			}
		}
	}
}
