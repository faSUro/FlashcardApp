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
	
	private static ActionListener NEXT_QUESTION_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			COUNTER++;
			VIEW.getQuestionPanel().setQuestion(KEY_SET[COUNTER].toString());
			VIEW.getAnswerPanel().setAnswer("");
			
			setShowAnswerListener();	
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
				
				setShowAnswerListener();
			}
		});
	}

	private static void setShowAnswerListener() {
		VIEW.getQuestionPanel().getShowAnswerButton().removeActionListener(SHOW_ANSWER_LISTENER);
		VIEW.getQuestionPanel().getShowAnswerButton().addActionListener(SHOW_ANSWER_LISTENER);
	}

	private static void setNextQuestionListener() {
		VIEW.getDifficultyPanel().getEasyButton().removeActionListener(NEXT_QUESTION_LISTENER);		
		VIEW.getDifficultyPanel().getEasyButton().addActionListener(NEXT_QUESTION_LISTENER);		
	}	

}






























