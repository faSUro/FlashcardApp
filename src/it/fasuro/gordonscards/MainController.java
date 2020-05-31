package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

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
		
		setPermanentListeners();
	}

	private void setPermanentListeners() {
		
		gui.getSelectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedDeckPath = PathHandler.generateDeckPath(gui.getSelectedDeck()); //generates deck path starting from its name
				refreshMainMenu();
			}
			
		});
		
		gui.getCreateNewDeckButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String newDeckName = gui.getNewDeckName();
					IOTools.createDeck(newDeckName);
					gui.addDeck(newDeckName);
					JOptionPane.showOptionDialog(null, "The deck has been created.", "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					selectedDeckPath = PathHandler.generateDeckPath(newDeckName); //auto selects the new deck
					refreshMainMenu(); //meglio mettere in refresh un metodo che setti la combobox sul deck selezionato (oppure una label per indicarlo)
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
				Flashcard flashcard = selectedDeck.getFlashcard(gui.getSelectedFlashcard());
				selectedDeck.deleteFlashcard(flashcard);
				refreshMainMenu();
			}
			
		});
		
		gui.getFlashcardList().addMouseListener(new MouseListener() { 

			@Override
			public void mouseClicked(MouseEvent e) { //da implementare
				JList<Object> list = (JList<Object>)e.getSource();
				if (e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					System.out.println(list.getModel().getElementAt(index));
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
		gui.refreshDeckPanel(selectedDeck.getFlashcardList());
		gui.setVisible(true);
		
		setTemporaryListeners();
	}

}
