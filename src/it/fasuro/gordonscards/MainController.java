package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.model.Flashcard;
import it.fasuro.gordonscards.utilities.IOTools;
import it.fasuro.gordonscards.utilities.PathHandler;
import it.fasuro.gordonscards.view.ErrorDisplayer;
import it.fasuro.gordonscards.view.mainmenu.MainMenuFrame;

public class MainController {
	
	private static MainController mainController;
	
	private MainMenuFrame gui;
	private ArrayList<String> deckList;
	
	private String selectedDeckPath;
	private Deck selectedDeck;
	
	public MainController(ArrayList<String> deckList) {
		this.deckList = deckList;
		gui = new MainMenuFrame(this.deckList);
		
		mainController = this;
		
		gui.initializeDeckPanel();
		
		setPermanentListeners();
	}

	private void setPermanentListeners() {
		
		gui.getSelectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					selectedDeckPath = PathHandler.generateDeckPath(gui.getSelectedDeck()); //generates deck path starting from its name
					refreshMainMenu();
				} catch (NullPointerException ex) {
					new ErrorDisplayer("                                            Select a deck first!");
				}
			}
			
		});
		
		gui.getCreateNewDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String newDeck = gui.getNewDeckName();
					IOTools.createDeck(newDeck);
					JOptionPane.showOptionDialog(null, "The deck has been created.", "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					if (PathHandler.isValidPath(newDeck)) {
						selectedDeckPath = newDeck; 
					} else {
						selectedDeckPath = PathHandler.generateDeckPath(newDeck);
					}
					
					gui.addDeck(PathHandler.getDeckNameFromPath(selectedDeckPath));
					refreshMainMenu(); //auto selects the new deck
				} catch (IllegalArgumentException ex) {
					new ErrorDisplayer("                           You've inserted an invalid deck name.");
				} catch (IOException ex) {
					new ErrorDisplayer("                           You've inserted an invalid deck path.");
				}
			}
			
		});
		
		gui.getBrowseButton().addActionListener(new ActionListener() {

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
				new StudyDeckLauncher(selectedDeck, mainController);	
			}
			
		});
		
		gui.getStudyAllDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedDeck.resetFlashcardDate(); 
				refreshSelectedDeck();
				
				gui.setVisible(false);
				new StudyDeckLauncher(selectedDeck, mainController); 
			}
			
		});
		
		gui.getDeleteDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					IOTools.deleteFile(selectedDeckPath);
					gui.removeDeck(selectedDeck.getDeckName());
					gui.initializeDeckPanel();
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
				Flashcard flashcard = selectedDeck.getFlashcard(gui.getSelectedFlashcard());
				selectedDeck.deleteFlashcard(flashcard);
				refreshMainMenu();
			}
			
		});
		
		gui.getFlashcardList().addMouseListener(new MouseListener() { 

			@Override
			public void mouseClicked(MouseEvent e) { 
				@SuppressWarnings("unchecked")
				JList<Object> list = (JList<Object>) e.getSource();
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					Flashcard flashcard = selectedDeck.getFlashcard((String) list.getModel().getElementAt(index)); 
					gui.setVisible(false);
					new EditFlashcardLauncher(mainController, flashcard);
				}
				
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
		
	}
	
	private void refreshSelectedDeck() {
		selectedDeck = new Deck(selectedDeckPath);
	}

	public void refreshMainMenu() {
		refreshSelectedDeck();
		gui.refreshDeckPanel(selectedDeck.getDeckName(), selectedDeck.getFlashcardList());
		gui.setVisible(true);
		
		setTemporaryListeners();
	}

}
