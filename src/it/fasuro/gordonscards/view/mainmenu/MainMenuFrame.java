package it.fasuro.gordonscards.view.mainmenu;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.fasuro.gordonscards.utilities.PathAndOSHandler;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {
	
	private final static int WIDTH = 700, HEIGHT = 350;
	
	private JPanel mainPanel;
	private JComboBox<String> deckListComboBox;
	private JButton selectButton;
	private JButton createNewDeckButton;
	private JButton importDeckButton;
	private JPanel deckPanel;
	
	public MainMenuFrame(String[] deckList) {
		setTitle("Gordon's Card");
		try {
			String iconPath = "res" + PathAndOSHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(WIDTH, HEIGHT);
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		
		JPanel deckManagerPanel = new JPanel();
		mainPanel.add(deckManagerPanel, BorderLayout.NORTH);
		
		JLabel selectDeckLabel = new JLabel("Select a deck: ");
		deckManagerPanel.add(selectDeckLabel);
		
		deckListComboBox = new JComboBox<String>(deckList); //JComboBox that shows the list of all decks
		deckManagerPanel.add(deckListComboBox);
		
		selectButton = new JButton("Select"); //button to select the list shown in the JComboBox
		deckManagerPanel.add(selectButton);
		
		createNewDeckButton = new JButton("Create new deck"); //button to create a new deck
		deckManagerPanel.add(createNewDeckButton);
		
		importDeckButton = new JButton("Import deck"); //button to import an existent deck
		deckManagerPanel.add(importDeckButton);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Refreshes the panel with the deck options and deck flashcards inside it.
	 * @param newFlashcardList
	 */
	public void refreshDeckPanel(String[] newFlashcardList) {
		if (deckPanel != null) { //avoids NullPointerException at first call
			mainPanel.remove(deckPanel);
		}
		deckPanel = new DeckPanel(newFlashcardList);
		mainPanel.add(deckPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Refreshes the JComboBox after a deck is added/deleted.
	 * @param newDeckList
	 */
	public void refreshComboBox(String[] newDeckList) {
		deckListComboBox = new JComboBox<String>(newDeckList);
	}

}
