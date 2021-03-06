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
public class Inserer implements Command {

	/**
	 * Nouvelle instance de l'interface Moteur declarant la methode inserer
	 * 
	 * @see Moteur
	 */
	private Moteur moteur;

	private Enregistreur enregistreur;

	private InsererMemento memento;

	/**
	 * Nouvelle String a inserer
	 */
	private Ihm ihm;

	/**
	 * Constructeur de la classe Inserer
	 * 
	 * @param moteur
	 * @param ihm
	 * @param enregistreur
	 */
	public Inserer(Moteur moteur, Ihm ihm, Enregistreur enregistreur) {
		this.moteur = moteur;
		this.ihm = ihm;
		this.enregistreur = enregistreur;
	}

	/**
	 * Appel de la mise en oeuvre de la fonction "inserer" dans l'implementation
	 * Moteur.
	 * 
	 * @see MoteurImpl
	 * 
	 */
	@Override
	public void execute() {
		String str = "";
		if (enregistreur.getPlay()) {
			str = memento.getTexte();
			moteur.inserer(str);
		} else {
			str = ihm.getText();
			moteur.inserer(str);
			if (enregistreur.getRecord()) {
				InsererMemento m = getMemento();
				m.setTexte(str);
				enregistreur.addMemento(m);
				enregistreur.addCommand(this);
			}
		}
	}

	public void setIhm(Ihm ihm) {
		this.ihm = ihm;
	}

	/**
	 * Cree un nouvea InsererMemento
	 */
	@Override
	public InsererMemento getMemento() {
		return new InsererMemento();
	}

	/**
	 * Classe InsererMemento implementant Memento et ne servant qu'a Inserer
	 * 
	 * @author Alexis LE MASLE et Fanny PRIEUR
	 *
	 */
	public class InsererMemento implements Memento<InsererMemento> {

		/**
		 * Le texte a sauvegarder lors d'un enregistrement
		 */
		private String texte;

		public String getTexte() {
			return texte;
		}

		public void setTexte(String texte) {
			this.texte = texte;
		}

	}

	@Override
	public void setMemento(Memento<?> mem) {
		this.memento = (InsererMemento) mem;
	}

}
