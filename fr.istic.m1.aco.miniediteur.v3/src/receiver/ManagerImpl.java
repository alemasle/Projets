package receiver;

import java.util.Stack;

import command.Command;
import etats.State;

/**
 * 
 * Classe ManagerImpl implementant Manager
 * 
 * @since v3
 * @author Alexis LE MASLE et Fanny PRIEUR
 *
 */
public class ManagerImpl implements Manager {

	private Stack<State> defaireStack = new Stack<State>();
	private Stack<State> refaireStack = new Stack<State>();

	private State stateCourant = new State(this);

	private Moteur moteur;

	private boolean play = false;

	public ManagerImpl(Moteur moteur) {
		this.moteur = moteur;
	}

	/**
	 * Appelle rejouer en mode defaire
	 */
	public void defaire() {
		if (!defaireStack.isEmpty()) {
			rejouer(true);
			System.out.println("UNDO");
		}
	}

	/**
	 * Appelle rejouer en mode refaire
	 */
	public void refaire() {
		if (!refaireStack.isEmpty()) {
			rejouer(false);
			System.out.println("REDO");
		}
	}

	/**
	 * 
	 * Permet de repasser a un State a refaire ou a defaire
	 * 
	 * @param st
	 *            le State a recuperer
	 * @param b
	 *            true si on defait, false si on refait
	 */
	private void rejouer(boolean b) {

		State sold = new State(this);
		sold = stateCourant;

		State st = null;

		if (b) {
			defaireStack.push(sold);
			st = defaireStack.pop();
		} else {
			refaireStack.push(sold);
			st = refaireStack.pop();
		}

		if (!st.getLcmd().isEmpty()) {
			Command cmdCurrent = null;
			setPlay(true);

			for (int i = 0; i < st.getLcmd().size() - 1; i++) {
				cmdCurrent = st.getLcmd().get(i);
				cmdCurrent.setMemento(st.getLmem().get(i));
				cmdCurrent.execute();
			}

			setPlay(false);
		}

		moteur.setBuffer(st.getBuf());
		int len = st.getBuf().getBuffer().length();
		moteur.getSelect().setDebut(len);
		moteur.getSelect().setFin(len);
	}

	public Moteur getMoteur() {
		return moteur;
	}

	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}

	public boolean getPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public State getStateCourant() {
		return stateCourant;
	}

	public void setStateCourant(State state) {
		defaireStack.push(stateCourant);
		this.stateCourant = state;
		moteur.setBuffer(state.getBuf());
	}
}