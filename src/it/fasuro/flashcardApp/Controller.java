package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import it.fasuro.flashcardApp.model.Deck;
import it.fasuro.flashcardApp.model.Difficulty;
import it.fasuro.flashcardApp.model.Flashcard;
import it.fasuro.flashcardApp.view.StudyDeckFrame;

/**
 * This class connects the different layers of the program: the model
 * (that contains the flashcards) and the GUI.
 *
 */
public class Controller {
	
	private final static StudyDeckFrame GUI = new StudyDeckFrame();
	private static Deck MODEL;
	
	private static TreeMap<String, Flashcard> FULL_DECK;
	
	private static TreeMap<String, String> DECK_TO_STUDY;	
	private static int COUNTER = 0;
	private static int TOTAL_QUESTIONS;
	private static ArrayList<String> KEY_SET;
	
	private static ActionListener SHOW_ANSWER_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			GUI.getAnswerPanel().setAnswer(DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			
			setNextQuestionListener();	
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_EASY = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.EASY);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				GUI.getQuestionPanel().setQuestion(KEY_SET.get(COUNTER));
				GUI.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				System.out.println("Congratulations! You've studied the entire deck!");
			}
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_MEDIUM = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.MEDIUM);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				GUI.getQuestionPanel().setQuestion(KEY_SET.get(COUNTER));
				GUI.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				System.out.println("Congratulations! You've studied the entire deck!");
			}
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_HARD = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			KEY_SET.add(KEY_SET.get(COUNTER));
			DECK_TO_STUDY.put(KEY_SET.get(COUNTER), DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			TOTAL_QUESTIONS++;
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				GUI.getQuestionPanel().setQuestion(KEY_SET.get(COUNTER));
				GUI.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				System.out.println("Congratulations! You've studied the entire deck!");
			}
		}		
	};
	
	/**
	 * Initializes the DECK static variable and makes
	 * an array that contains the key set. It also 
	 * initializes the TOTAL_QUESTIONS variable (with 
	 * the length of the key set).
	 * @param deck
	 */
	public Controller(Deck deck) {
		MODEL = deck;
		
		FULL_DECK = MODEL.getFullDeck();
		DECK_TO_STUDY = MODEL.getDeckToStudy();
		
		KEY_SET = new ArrayList<String>(DECK_TO_STUDY.keySet());
		TOTAL_QUESTIONS = KEY_SET.size();
		
		setFirstQuestionListener();
	}

	private void setFirstQuestionListener() {
		GUI.getQuestionPanel().getShowAnswerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.getQuestionPanel().getQuestionLabel().setText(KEY_SET.get(COUNTER));
				GUI.getQuestionPanel().getShowAnswerButton().setText("  Show answer  ");
				
				setShowAnswerListener();
			}
		});
	}

	private static void setShowAnswerListener() {
		GUI.getQuestionPanel().getShowAnswerButton().removeActionListener(SHOW_ANSWER_LISTENER);
		GUI.getQuestionPanel().getShowAnswerButton().addActionListener(SHOW_ANSWER_LISTENER);
	}

	private static void setNextQuestionListener() {
		GUI.getDifficultyPanel().getEasyButton().removeActionListener(NEXT_QUESTION_LISTENER_EASY);		
		GUI.getDifficultyPanel().getEasyButton().addActionListener(NEXT_QUESTION_LISTENER_EASY);		
		
		GUI.getDifficultyPanel().getMediumButton().removeActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		GUI.getDifficultyPanel().getMediumButton().addActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		
		GUI.getDifficultyPanel().getHardButton().removeActionListener(NEXT_QUESTION_LISTENER_HARD);		
		GUI.getDifficultyPanel().getHardButton().addActionListener(NEXT_QUESTION_LISTENER_HARD);		
	}	

}






























