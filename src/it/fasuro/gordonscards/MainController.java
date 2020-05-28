package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.utilities.PathHandler;
import it.fasuro.gordonscards.view.mainmenu.MainMenuFrame;

public class MainController {
	
	private MainMenuFrame gui;
	private ArrayList<String> deckList;
	
	private String selectedDeckPath;
	
	public MainController(ArrayList<String> s) {
		deckList = s;
		gui = new MainMenuFrame(deckList);
		
		setPermanentListeners();
	}

	private void setPermanentListeners() {
		
		gui.getSelectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedDeckPath = PathHandler.generateDeckPath(gui.getSelectedDeck());
				Deck selectedDeck = new Deck(selectedDeckPath); //generates deck path starting from its name
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
				new StudyDeckLauncher(new Deck(selectedDeckPath));	
				
			}
			
		});
		
		gui.getStudyAllDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Deck(selectedDeckPath).resetFlashcardDate(); //per ora così, valutare un metodo refreshDeck che aggiorna
				new StudyDeckLauncher(new Deck(selectedDeckPath));
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
				new CreateFlashcardsLauncher(gui, selectedDeckPath);
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
