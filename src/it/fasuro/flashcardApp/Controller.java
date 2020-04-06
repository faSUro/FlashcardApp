package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import it.fasuro.flashcardApp.model.Deck;
import it.fasuro.flashcardApp.view.MainFrame;

public class Controller {
	
	private final MainFrame view = new MainFrame();
	private final TreeMap<String, String> model;
	
	private static int COUNTER = 0;
	private Object[] keySet;
	
	public Controller(Deck deck) {
		model = deck.getFcToStudy();
		
		keySet = model.keySet().toArray();
		
		setFirstQuestionListener();
	}
	
	public void setFirstQuestionListener() {
		view.getShowAnswerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setFirstQuestion(keySet[COUNTER].toString());
				
				setListeners();
			}
		});
	}

	private void setListeners() {
		view.getShowAnswerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.showAnswer(model.get(keySet[COUNTER].toString()));
			}
		});
		
	}

}
