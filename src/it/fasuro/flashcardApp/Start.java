package it.fasuro.flashcardApp;

import it.fasuro.flashcardApp.model.Deck;

/**
 * This class allows to start the entire program.
 * This is an application for the "flashcard" study method; it
 * allows to write questions and answers and to decide how often
 * you should exercise, basing on the difficulty of the
 * questions.
 * @version 1.0
 * @author Nicolò Fasulo
 * 
 */

public class Start {
	
	/**
	 * The main method. It creates the map containing questions and
	 * answers from a text file and "sends" it to the GUI through
	 * the Controller class.
	 * 
	 */
	public static void main(String args[]) {
		Deck d = new Deck();

		//new Controller(d);
	}

}
