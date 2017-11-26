package receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import command.Command;
import memento.Memento;
import state.State;

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

	private State stateCourant;

	private Moteur moteur;

	private boolean play = false;

	public ManagerImpl(Moteur moteur) {
		this.moteur = moteur;
		this.stateCourant = new State(moteur.clone());
	}

	/**
	 * Revient a l'etat precedent la derniere commande.
	 */
	public void defaire() {

		List<Command> lcmd = stateCourant.getLcmd();
		List<Memento<?>> lmem = stateCourant.getLmem();

		if (lcmd.size() == 0) {
			if (!defaireStack.isEmpty()) {

				refaireStack.push(stateCourant.clone());

				stateCourant = defaireStack.pop();

				stateCourant.getLcmd().remove(stateCourant.getLcmd().size() - 1);
				stateCourant.getLmem().remove(stateCourant.getLmem().size() - 1);

				setPlay(true);
				moteur.recreer(stateCourant.clone());
				setPlay(false);
			}
		} else {

			refaireStack.push(stateCourant.clone());

			stateCourant.getLcmd().remove(lcmd.size() - 1);
			stateCourant.getLmem().remove(lmem.size() - 1);

			setPlay(true);
			moteur.recreer(stateCourant.clone());
			setPlay(false);

		}
	}

	/**
	 * Refait la derniere action defaite
	 */
	public void refaire() {
		if (!refaireStack.isEmpty()) {
			if (stateCourant.getLcmd().size() == 5) {
				defaireStack.push(stateCourant.clone());
			}
			stateCourant = refaireStack.pop();

			setPlay(true);
			moteur.recreer(stateCourant);
			setPlay(false);
		}
	}

	/**
	 * Sauvegarde l'etat courant dans la pile Defaire si il y'a 5 commandes
	 * enregistree
	 */
	public void saveState() {
		List<Command> lcmd = stateCourant.getLcmd();
		if (lcmd.size() == 5) {
			State oldSt = stateCourant.clone();
			defaireStack.push(oldSt);

			State ns = new State(moteur.clone());
			ns.setLcmd(new ArrayList<Command>());
			ns.setLmem(new ArrayList<Memento<?>>());
			stateCourant = ns;
			System.out.println("Save state");
		}
	}

	/**
	 * Vide la pile "refaire" si on execute une nouvelle commande
	 */
	public void emptyRedo() {
		if (!refaireStack.isEmpty()) {
			refaireStack = new Stack<State>();
		}
	}

	///////////////// Getter/Setter attributs /////////////////////

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

	public void setStateCourant(State s) {
		this.stateCourant = s;
	}

	public Stack<State> getRefaireStack() {
		return refaireStack;
	}

	public void setRefaireStack(Stack<State> refaireStack) {
		this.refaireStack = refaireStack;
	}

	public Stack<State> getDefaireStack() {
		return defaireStack;
	}

	public void setDefaireStack(Stack<State> defaireStack) {
		this.defaireStack = defaireStack;
	}

}