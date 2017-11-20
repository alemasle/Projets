package command;

import ihm.Ihm;
import memento.Memento;
import receiver.Enregistreur;
import receiver.Moteur;
import receiver.MoteurImpl;

/**
 * Concrete Command "Inserer" implementant l'interface Command
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 * 
 */
public class Ajouter implements Command {

	/**
	 * Nouvelle instance de l'interface Moteur declarant la methode inserer
	 * 
	 * @see Moteur
	 */
	private Moteur moteur;

	/**
	 * Nouvelle String a inserer
	 */
	private Ihm ihm;

	private Enregistreur enregistreur;

	private AjouterMemento memento;

	/**
	 * Constructeur de la classe Inserer
	 * 
	 * @param moteur
	 * @param str
	 */
	public Ajouter(Moteur moteur, Ihm ihm, Enregistreur enregistreur) {
		this.moteur = moteur;
		this.ihm = ihm;
		this.enregistreur = enregistreur;
	}

	// Operations

	/**
	 * Appel de la mise en oeuvre de la fonction "inserer" dans l'implementation
	 * Moteur.
	 * 
	 * @see MoteurImpl
	 * 
	 */
	public void execute() {
		String str = "";
		if (enregistreur.getPlay()) {
			str = memento.getTexte();
			moteur.ajouter(str);
		} else {
			str = ihm.getText();
			moteur.ajouter(str);
			if (enregistreur.getRecord()) {
				AjouterMemento m = getMemento();
				m.setTexte(str);
				enregistreur.addMemento(m);
				enregistreur.addCommand(this);
			}
		}
	}

	public void setIhm(Ihm ihm) {
		this.ihm = ihm;
	}

	@Override
	public AjouterMemento getMemento() {
		return new AjouterMemento();
	}

	private class AjouterMemento implements Memento<AjouterMemento> {

		String texte;

		public String getTexte() {
			return texte;
		}

		public void setTexte(String texte) {
			this.texte = texte;
		}

	}

	@Override
	public void setMemento(Memento<?> mem) {
		this.memento = (AjouterMemento) mem;
	}

}