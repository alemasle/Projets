package command;

import ihm.Ihm;
import receiver.Moteur;
import receiver.MoteurImpl;

/**
 * Concrete Command "Selectionner" implementant l'interface Command
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 * 
 */
public class Selectionner implements Command {

	/**
	 * Nouvelle instance de l'interface Moteur declarant la methode selectionner
	 * 
	 * @see Moteur
	 */
	private Moteur moteur;

	/**
	 * Ihm permettant d'obtenir les bornes de la selection
	 */
	private Ihm ihm;

	/**
	 * Constructeur de la classe Selectionner
	 * 
	 * @param moteur
	 * @param ihm
	 */
	public Selectionner(Moteur moteur, Ihm ihm) {
		this.moteur = moteur;
		this.ihm = ihm;
	}

	// Operations

	/**
	 * Appel de la mise en oeuvre de la fonction "selectionner" dans
	 * l'implementation Moteur.
	 * 
	 * @see MoteurImpl
	 * 
	 */
	@Override
	public void execute() {
		int deb = ihm.getDebut();
		int fin = ihm.getFin();
		if (deb > fin) {
			moteur.selectionner(fin, deb);
		} else {
			moteur.selectionner(deb, fin);
		}
	}

	public void setIhm(Ihm ihm) {
		this.ihm = ihm;
	}

}
