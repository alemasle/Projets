package main;

import command.*;
import ihm.*;
import receiver.*;
import state.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		ClipBoard clip = new ClipboardImpl();
		Selection selection = new Selection();
		Ihm ihm = null;

		Moteur moteur = new MoteurImpl(buffer, clip, selection);

		Coller coller = new Coller(moteur);
		Copier copier = new Copier(moteur);
		Couper couper = new Couper(moteur);
		Inserer inserer = new Inserer(moteur, ihm);
		Selectionner selectionner = new Selectionner(moteur, ihm);
		Save save = new Save(moteur, ihm);
		Ajouter ajouter = new Ajouter(moteur, ihm);
		Delete delete = new Delete(moteur);
		Load load = new Load(moteur, ihm);

		Scanner input = new Scanner(System.in);

		ihm = new Ihm(copier, coller, couper, inserer, selectionner, buffer, selection, input, save, ajouter, delete,
				load);

		inserer.setIhm(ihm);
		selectionner.setIhm(ihm);
		save.setIhm(ihm);
		ajouter.setIhm(ihm);
		load.setIhm(ihm);

		ihm.executeCommand();

	}

}
