package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import it.fasuro.flashcardApp.model.Deck;
import it.fasuro.flashcardApp.view.AnswerPanel;
import it.fasuro.flashcardApp.view.StudyDeckFrame;

public class Controller {
	
	private final StudyDeckFrame view = new StudyDeckFrame();
	private final TreeMap<String, String> model;
	
	private static int COUNTER = 0;
	private static int TOTAL_QUESTIONS;
	private Object[] keySet;
	
	public Controller(Deck deck) {
		model = deck.getFcToStudy();
		
		keySet = model.keySet().toArray();
		TOTAL_QUESTIONS = keySet.length;
		
		setFirstQuestionListener();
	}

	private void setFirstQuestionListener() {
		view.getAnswerPanel().getShowAnswerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getQuestionPanel().getQuestionLabel().setText(keySet[COUNTER].toString());
				view.getAnswerPanel().setShowAnswer();
				
				setShowAnswerListener();
			}
		});
	}

	private void setShowAnswerListener() {
		view.getAnswerPanel().getShowAnswerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getAnswerPanel().removeAll();
				view.getAnswerPanel().add(new AnswerPanel(model.get(keySet[COUNTER].toString())));
				view.getAnswerPanel().revalidate();
				view.getAnswerPanel().repaint();
			}
		});
	}	

}






























