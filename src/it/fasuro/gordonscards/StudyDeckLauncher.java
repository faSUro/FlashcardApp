package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.model.Flashcard;
import it.fasuro.gordonscards.view.studydeck.StudyDeckFrame;

/**
 * This class connects the different layers of the program: the model
 * (that contains the flashcards) and the GUI.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class StudyDeckLauncher {
	
	private MainController mainController;
	
	private StudyDeckFrame STUDY_DECK_GUI;
	private Deck DECK_MODEL;
	
	private TreeMap<String, Flashcard> FULL_DECK;
	private TreeMap<String, String> DECK_TO_STUDY;	
	private int COUNTER = 0;
	private int TOTAL_QUESTIONS;
	private ArrayList<String> KEY_SET;
	

	/**
	 * Creates a new frame to select the folder containing the flashcard
	 * (with a JFileChooser); then it calls the method that allows to
	 * create new flashcards/study an existent deck, depending on the
	 * option passed as argument.
	 * @param mainController 
	 * @param option
	 * 
	 */
	public StudyDeckLauncher(Deck deck, MainController mainController) {
		DECK_MODEL = deck;	
		
		this.mainController = mainController;
		
		start();
	}

	/**
	 * Initializes the DECK_MODEL variable and the GUI to study it.
	 * 
	 */
	public void start() {	
		STUDY_DECK_GUI = new StudyDeckFrame();
		
		if (DECK_MODEL.getFullDeck() == null) { //in case the deck folder is empty, the method "stops" here
			return;								//an exception has been thrown already
		}
		
		FULL_DECK = DECK_MODEL.getFullDeck();
		DECK_TO_STUDY = DECK_MODEL.getDeckToStudy();
		
		KEY_SET = new ArrayList<String>(DECK_TO_STUDY.keySet());
		TOTAL_QUESTIONS = KEY_SET.size();
		
		STUDY_DECK_GUI.getShowAnswerButton().addActionListener(FIRST_QUESTION_LISTENER); //adds the listener for the first click to start studying
	}
	
	private void end() {
		JOptionPane.showOptionDialog(null, "You've studied the entire deck!", "Congratulations!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (true) {
			mainController.refreshMainMenu();
			STUDY_DECK_GUI.dispose();
		}
	}

	/**
	 * Removes the previous listeners and resets the listener that shows the answer to the show answer button.
	 * 
	 */
	private void setShowAnswerListener() {
		STUDY_DECK_GUI.getShowAnswerButton().removeActionListener(FIRST_QUESTION_LISTENER);
		
		STUDY_DECK_GUI.getShowAnswerButton().removeActionListener(SHOW_ANSWER_LISTENER);
		STUDY_DECK_GUI.getShowAnswerButton().addActionListener(SHOW_ANSWER_LISTENER);
	}

	/**
	 * Removes and resets the listeners of the three buttons that lead to the next question.
	 * 
	 */
	private void setNextQuestionListener() {
		STUDY_DECK_GUI.getEasyButton().removeActionListener(NEXT_QUESTION_LISTENER_EASY);		
		STUDY_DECK_GUI.getEasyButton().addActionListener(NEXT_QUESTION_LISTENER_EASY);		
		
		STUDY_DECK_GUI.getMediumButton().removeActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		STUDY_DECK_GUI.getMediumButton().addActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		
		STUDY_DECK_GUI.getHardButton().removeActionListener(NEXT_QUESTION_LISTENER_HARD);		
		STUDY_DECK_GUI.getHardButton().addActionListener(NEXT_QUESTION_LISTENER_HARD);		
	}	
	
	/**
	 * Listener that set the first question and changes the button text to "Show answer".
	 * Then it set the following listener.
	 * 
	 */
	private ActionListener FIRST_QUESTION_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (TOTAL_QUESTIONS == 0) {
				end();
			} else {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.resetShowAnswerButtonText();
			
				setShowAnswerListener();
			}
		}	
	};
	
	/**
	 * Listener that reveals the answer to the current question.
	 * Then it set the following listener.
	 * 
	 */
	private ActionListener SHOW_ANSWER_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			STUDY_DECK_GUI.setAnswer(DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			
			setNextQuestionListener();	
		}		
	};
	
	/**
	 * Listener that sets the new question and that modifies the date in the current flashcard (easy case).
	 * Then it set the following listener.
	 * 
	 */
	private ActionListener NEXT_QUESTION_LISTENER_EASY = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.EASY);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.setAnswer("");
			
				setShowAnswerListener();	
			} else {
				end();
			}
		}		
	};
	
	/**
	 * Listener that sets the new question and that modifies the date in the current flashcard (medium case).
	 * Then it set the following listener.
	 * 
	 */
	private ActionListener NEXT_QUESTION_LISTENER_MEDIUM = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.MEDIUM);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.setAnswer("");
			
				setShowAnswerListener();	
			} else {
				end();
			}
		}		
	};
	
	/**
	 * Listener that sets the new question and re-adds the current question to the key set (hard case).
	 * Then it set the following listener.
	 * 
	 */
	private ActionListener NEXT_QUESTION_LISTENER_HARD = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			KEY_SET.add(KEY_SET.get(COUNTER));
			DECK_TO_STUDY.put(KEY_SET.get(COUNTER), DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			TOTAL_QUESTIONS++;
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.setAnswer("");
			
				setShowAnswerListener();	
			} else {
				end();
			}
		}		
	};

}
