package command;

import memento.Memento;
import receiver.Enregistreur;

/**
 * Concrete Command Rejouer implementant l'interface Command
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 *
 */
public class Rejouer implements Command {

	/**
	 * Nouvelle instance de l'enregistreur
	 * 
	 * @see Enregistreur
	 */
	private Enregistreur enregistreur;

	private RejouerMemento memento;

	/**
	 * Constructeur de la classe Rejouer
	 * 
	 * @param enregistreur
	 */
	public Rejouer(Enregistreur enregistreur) {
		this.enregistreur = enregistreur;
	}

	/**
	 * Appel a la methode rejouer de l'enregistreur.
	 */
	public void execute() {
		enregistreur.rejouer();

	}

	@Override
	public RejouerMemento getMemento() {
		return new RejouerMemento();
	}

	private class RejouerMemento implements Memento<RejouerMemento> {

	}

	@Override
	public void setMemento(Memento<?> mem) {
		this.memento = (RejouerMemento) mem;
	}

}
