package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.fasuro.flashcardApp.initialize.FirstBootFrame;
import it.fasuro.flashcardApp.model.Deck;
import it.fasuro.flashcardApp.model.IOHandler;

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
	
	private static String DECK_PATH = "";
	
	/**
	 * The main method. It creates the map containing questions and
	 * answers from a text file and "sends" it to the GUI through
	 * the Controller class.
	 * 
	 */
	public static void main(String args[]) {
	
		if(IOHandler.firstBoot()) {
			FirstBootFrame firstBoot = new FirstBootFrame();
			
			firstBoot.getOkButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DECK_PATH = firstBoot.getPath();
					IOHandler.setDeckPath(DECK_PATH);
					IOHandler.firstBootDone();
					
					firstBoot.dispose();					
					startApp();	
				}
			});			
		} else {
			DECK_PATH = IOHandler.getDeckPath();
			startApp();	
		}
	}

	private static void startApp() {
		
		new Controller(new Deck(DECK_PATH));
		
	}

}
