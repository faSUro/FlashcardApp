package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import it.fasuro.flashcardApp.model.Deck;
import it.fasuro.flashcardApp.view.StudyDeckFrame;

public class Controller {
	
	private final static StudyDeckFrame VIEW = new StudyDeckFrame();
	private static TreeMap<String, String> MODEL;
	
	private static int COUNTER = 0;
	private static int TOTAL_QUESTIONS;
	private static Object[] KEY_SET;
	
	private static ActionListener SHOW_ANSWER_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			VIEW.getAnswerPanel().setAnswer(MODEL.get(KEY_SET[COUNTER].toString()));
			
			setNextQuestionListener();	
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_EASY = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				VIEW.getQuestionPanel().setQuestion(KEY_SET[COUNTER].toString());
				VIEW.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				System.out.println("Congratulations! You've studied the entire deck!");
			}
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_MEDIUM = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				VIEW.getQuestionPanel().setQuestion(KEY_SET[COUNTER].toString());
				VIEW.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				System.out.println("Congratulations! You've studied the entire deck!");
			}
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_HARD = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				VIEW.getQuestionPanel().setQuestion(KEY_SET[COUNTER].toString());
				VIEW.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				System.out.println("Congratulations! You've studied the entire deck!");
			}
		}		
	};
	
	public Controller(Deck deck) {
		MODEL = deck.getFcToStudy();
		
		KEY_SET = MODEL.keySet().toArray();
		TOTAL_QUESTIONS = KEY_SET.length;
		
		setFirstQuestionListener();
	}

	private void setFirstQuestionListener() {
		VIEW.getQuestionPanel().getShowAnswerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VIEW.getQuestionPanel().getQuestionLabel().setText(KEY_SET[COUNTER].toString());
				VIEW.getQuestionPanel().getShowAnswerButton().setText("  Show answer  ");
				
				setShowAnswerListener();
			}
		});
	}

	private static void setShowAnswerListener() {
		VIEW.getQuestionPanel().getShowAnswerButton().removeActionListener(SHOW_ANSWER_LISTENER);
		VIEW.getQuestionPanel().getShowAnswerButton().addActionListener(SHOW_ANSWER_LISTENER);
	}

	private static void setNextQuestionListener() {
		VIEW.getDifficultyPanel().getEasyButton().removeActionListener(NEXT_QUESTION_LISTENER_EASY);		
		VIEW.getDifficultyPanel().getEasyButton().addActionListener(NEXT_QUESTION_LISTENER_EASY);		
		
		VIEW.getDifficultyPanel().getMediumButton().removeActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		VIEW.getDifficultyPanel().getMediumButton().addActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		
		VIEW.getDifficultyPanel().getHardButton().removeActionListener(NEXT_QUESTION_LISTENER_HARD);		
		VIEW.getDifficultyPanel().getHardButton().addActionListener(NEXT_QUESTION_LISTENER_HARD);		
	}	

}






























