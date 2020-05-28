package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.utilities.PathHandler;
import it.fasuro.gordonscards.view.mainmenu.MainMenuFrame;

public class ControllerV2 {
	
	private MainMenuFrame gui;
	private ArrayList<String> deckList;
	
	private Deck selectedDeck;
	
	public ControllerV2(ArrayList<String> s) {
		deckList = s;
		gui = new MainMenuFrame(deckList);
		
		setPermanentListeners();
	}

	private void setPermanentListeners() {
		
		gui.getSelectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedDeck = new Deck(PathHandler.generateDeckPath(gui.getSelectedDeck())); //generates deck path starting from its name
				gui.refreshDeckPanel(selectedDeck.getFlashcardList());

				setTemporaryListeners();
			}
			
		});
		
		gui.getCreateNewDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.getImportDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	private void setTemporaryListeners() {
		
		gui.getStudyDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.getStudyAllDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.getDeleteDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.getAddFlashcardsButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		gui.getDeleteFlashcardButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}

}
