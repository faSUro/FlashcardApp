package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.fasuro.gordonscards.view.MainMenuFrame;
import it.fasuro.gordonscards.view.StartMenuFrame;

/**
 * This class allows to start the entire program.
 * This is an application for the "flashcard" study method; it
 * allows to write questions and answers and to decide how often
 * you should exercise, basing on the difficulty of the
 * questions.
 * @version 1.3
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 * 
 */

public class Launcher {

	/**
	 * The main method. It creates the map containing questions and
	 * answers from a text file and "sends" it to the GUI through
	 * the Controller class.
	 * 
	 */
	public static void main(String args[]) {	
		//block to set SystemLookAndFeel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		String[] list1 = {"Deck 1", "Deck 2", "Deck 3"};
		String[] list2 = {"Domanda 1", "Domanda 2", "Domanda 3"};
		
		new MainMenuFrame(list1, list2);
		
		/*
		 * The next lines create the menu frame and add its action listeners:
		 * one for the creation of new flashcards and one to select a 
		 * deck to study.
		 * 
		 */
		
		/*StartMenuFrame startMenu = new StartMenuFrame();
		
		startMenu.getCreateDeckButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Controller(StartMenuOptions.CREATE_FLASHCARDS);
				
				startMenu.dispose();
			}			
		});
		
		startMenu.getStudyDeckButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Controller(StartMenuOptions.STUDY_DECK);
				
				startMenu.dispose();
			}			
		});*/
	}

}
