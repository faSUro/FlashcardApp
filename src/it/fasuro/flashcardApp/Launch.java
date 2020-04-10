package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.fasuro.flashcardApp.view.StartMenuFrame;

/**
 * This class allows to start the entire program.
 * This is an application for the "flashcard" study method; it
 * allows to write questions and answers and to decide how often
 * you should exercise, basing on the difficulty of the
 * questions.
 * @version 1.1
 * @author Nicolò Fasulo
 * 
 */

public class Launch {

	/**
	 * The main method. It creates the map containing questions and
	 * answers from a text file and "sends" it to the GUI through
	 * the Controller class.
	 * 
	 */
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		StartMenuFrame startMenu = new StartMenuFrame();
		
		startMenu.getCreateDeckButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Controller(StartMenuOptions.CREATE_DECK);
				
				startMenu.dispose();
			}			
		});
		
		startMenu.getStudyDeckButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Controller(StartMenuOptions.STUDY_DECK);
				
				startMenu.dispose();
			}			
		});
	}

}
