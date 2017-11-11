package Receiver;

import Memento.Memento;

/**
 * Interface Enregistreur
 *
 * @author Alexis LE MASLE et Fanny PRIEUR
 */
public interface Enregistreur {

    /**
     * Methode demarrer commune a tous les enregistreurs Permet de demarrer
     * l'enregistrement.
     */
    void demarrer();

    /**
     * Methode permettant de stopper l'enregistrement en cours.
     */
    void stopper();

    /**
     * Metohde permettant de rejouer le dernier enregistrement.
     */
    void rejouer();

    boolean getRecord();

    void addMemento(Memento m);

}
