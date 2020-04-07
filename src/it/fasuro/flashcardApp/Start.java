package it.fasuro.flashcardApp;

import it.fasuro.flashcardApp.model.Deck;

public class Start {
	
	public static void main(String args[]) {
		Deck d = new Deck();

		new Controller(d);
	}

}
