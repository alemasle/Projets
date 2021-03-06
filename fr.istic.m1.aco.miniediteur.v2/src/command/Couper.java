package command;

import memento.Memento;
import receiver.Enregistreur;
import receiver.Moteur;
import receiver.MoteurImpl;

/**
 * Concrete Command "Couper" implementant l'interface Command
 *
 * @author Alexis LE MASLE et Fanny PRIEUR
 */
public class Couper implements Command {

	/**
	 * Nouvelle instance de l'interface Moteur declarant la methode couper
	 *
	 * @see Moteur
	 */
	private Moteur moteur;

	private Enregistreur enregistreur;

	private CouperMemento memento;

	/**
	 * Constructeur de la classe Couper
	 * 
	 * @param moteur
	 * @param enregistreur
	 */
	public Couper(Moteur moteur, Enregistreur enregistreur) {
		this.moteur = moteur;
		this.enregistreur = enregistreur;
	}

	// Operations

	/**
	 * Appel de la mise en oeuvre de la fonction "couper" dans l'implementation
	 * Moteur.
	 *
	 * @see MoteurImpl
	 */
	@Override
	public void execute() {
		moteur.couper();
		if (enregistreur.getRecord()) {
			enregistreur.addMemento(getMemento());
			enregistreur.addCommand(this);
		}
	}

	/**
	 * Cree un nouveau CouperMemento
	 */
	@Override
	public CouperMemento getMemento() {
		return new CouperMemento();
	}

	/**
	 * Classe CouperMemento implementant Memento et ne servant qu'a la classe Couper
	 * 
	 * @author Alexis LE MASLE et Fanny PRIEUR
	 *
	 */
	public class CouperMemento implements Memento<CouperMemento> {

	}

	@Override
	public void setMemento(Memento<?> mem) {
		this.setCouperMemento((CouperMemento) mem);
	}

	public CouperMemento getCouperMemento() {
		return memento;
	}

	public void setCouperMemento(CouperMemento memento) {
		this.memento = memento;
	}

}
