package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.utilities.IOTools;
import it.fasuro.gordonscards.utilities.PathHandler;
import it.fasuro.gordonscards.view.ErrorDisplayer;
import it.fasuro.gordonscards.view.mainmenu.MainMenuFrame;

public class MainController {
	
	private static MainController mainController;
	
	private MainMenuFrame gui;
	private ArrayList<String> deckList;
	
	private String selectedDeckName;
	private String selectedDeckPath;
	
	public MainController(ArrayList<String> deckList) {
		this.deckList = deckList;
		gui = new MainMenuFrame(this.deckList);
		
		mainController = this;
		
		setPermanentListeners();
	}

	private void setPermanentListeners() {
		
		gui.getSelectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedDeckName = gui.getSelectedDeck();
				selectedDeckPath = PathHandler.generateDeckPath(selectedDeckName); //generates deck path starting from its name
				Deck selectedDeck = new Deck(selectedDeckPath); 
				gui.refreshDeckPanel(selectedDeck.getFlashcardList());

				setTemporaryListeners();
			}
			
		});
		
		gui.getCreateNewDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					IOTools.createDeck("testDeck");
					gui.addDeck("testDeck");
					JOptionPane.showOptionDialog(null, "The deck has been created.", "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				} catch (IllegalArgumentException ex) {
					new ErrorDisplayer("      You've inserted an invalid deck name.");
				}
			}
			
		});
		
		gui.getImportDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	public void setTemporaryListeners() {
		
		gui.getStudyDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setVisible(false);
				new StudyDeckLauncher(new Deck(selectedDeckPath), mainController);	
			}
			
		});
		
		gui.getStudyAllDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Deck(selectedDeckPath).resetFlashcardDate(); 
				gui.setVisible(false);
				new StudyDeckLauncher(new Deck(selectedDeckPath), mainController); //per ora così, valutare un metodo refreshDeck che aggiorna
			}
			
		});
		
		gui.getDeleteDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					IOTools.deleteDeck(selectedDeckName);
					gui.removeDeck(selectedDeckName);
					gui.emptyDeckPanel();
					JOptionPane.showOptionDialog(null, "The deck has been deleted.", "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				} catch (IllegalArgumentException ex) {
					new ErrorDisplayer("                        Something unexpected happened.");
				}
				
			}
			
		});
		
		gui.getAddFlashcardsButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setVisible(false);
				new CreateFlashcardsLauncher(mainController, selectedDeckPath);
			}
			
		});
		
		gui.getDeleteFlashcardButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}

	public MainMenuFrame getGui() {
		return gui;
	}

}
