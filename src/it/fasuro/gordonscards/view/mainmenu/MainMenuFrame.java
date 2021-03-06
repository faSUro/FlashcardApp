package it.fasuro.gordonscards.view.mainmenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.fasuro.gordonscards.utilities.PathHandler;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {
	
	private final static int WIDTH = 1100, HEIGHT = 500;
	
	private JPanel mainPanel;
	private JComboBox<Object> deckListComboBox;
	private JButton selectButton;
	private JTextField deckNameTextField;
	private JButton createNewDeckButton;
	private JButton browseButton;
	private DeckPanel deckPanel;
	
	public MainMenuFrame(ArrayList<String> deckArrayList) {
		setTitle("Gordon's Card");
		try {
			String iconPath = "res" + PathHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(WIDTH, HEIGHT);
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		
		JPanel deckManagerPanel = new JPanel();
		deckManagerPanel.setLayout(new GridLayout(2, 1));
		
		JPanel deckSelectionPanel = new JPanel();
		
		JLabel selectDeckLabel = new JLabel("Select a deck: ");
		deckSelectionPanel.add(selectDeckLabel);
		
		deckListComboBox = new JComboBox<Object>(deckArrayList.toArray()); //JComboBox that shows the list of all decks
		deckSelectionPanel.add(deckListComboBox);
		
		selectButton = new JButton("Select"); //button to select the list shown in the JComboBox
		deckSelectionPanel.add(selectButton);
	
		JPanel newDeckPanel = new JPanel();
		
		JLabel createDeckLabel = new JLabel("Insert a name to create a new deck or a path to import an existing one: ");
		newDeckPanel.add(createDeckLabel);
		
		deckNameTextField = new JTextField(15);
		newDeckPanel.add(deckNameTextField);
		
		browseButton = new JButton("Browse"); //button to import an existent deck
		newDeckPanel.add(browseButton);
		
		createNewDeckButton = new JButton("Create deck"); //button to create a new deck
		newDeckPanel.add(createNewDeckButton);
			
		deckManagerPanel.add(deckSelectionPanel); deckManagerPanel.add(newDeckPanel);
		
		mainPanel.add(deckManagerPanel, BorderLayout.NORTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Refreshes the panel with the deck options and deck flashcards inside it.
	 * @param arrayList
	 */
	public void refreshDeckPanel(String deckName, ArrayList<String> arrayList) {
		if (deckPanel != null) { //avoids NullPointerException at first call
			mainPanel.remove(deckPanel);
		}
		deckPanel = new DeckPanel(deckName, arrayList);
		mainPanel.add(deckPanel, BorderLayout.CENTER);
		
		revalidate();
	}
	
	public void initializeDeckPanel() {
		if (deckPanel != null) { //avoids NullPointerException at first call
			mainPanel.remove(deckPanel);
		}
		deckPanel = new DeckPanel();
		mainPanel.add(deckPanel, BorderLayout.CENTER);
		
		revalidate();
	}
	
	public void addDeck(String newDeckName) {
		deckListComboBox.addItem(newDeckName);
		deckNameTextField.setText("");
		
		revalidate();
	}
	
	public void removeDeck(String deckName) {
		deckListComboBox.removeItem(deckName);

		revalidate();
	}
	
	public String getSelectedDeck() {
		return (String) deckListComboBox.getSelectedItem();
	}
	
	public String getSelectedFlashcard() {
		return deckPanel.getSelectedFlashcard();
	}
	
	public String getNewDeckName() {
		return deckNameTextField.getText();
	}

	public JButton getSelectButton() {
		return selectButton;
	}

	public JButton getCreateNewDeckButton() {
		return createNewDeckButton;
	}

	public JButton getBrowseButton() {
		return browseButton;
	}
	
	public JButton getStudyDeckButton() {
		return deckPanel.getStudyDeckButton();
	}

	public JButton getStudyAllDeckButton() {
		return deckPanel.getStudyAllDeckButton();
	}

	public JButton getDeleteDeckButton() {
		return deckPanel.getDeleteDeckButton();
	}

	public JButton getAddFlashcardsButton() {
		return deckPanel.getAddFlashcardsButton();
	}

	public JButton getDeleteFlashcardButton() {
		return deckPanel.getDeleteFlashcardButton();
	}
	
	public JList<Object> getFlashcardList() {
		return deckPanel.getFlashcardList();
	}

	public void setDeckPath(String deckPath) {
		deckNameTextField.setText(deckPath);		
	}

}
