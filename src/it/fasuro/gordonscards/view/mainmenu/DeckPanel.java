package it.fasuro.gordonscards.view.mainmenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DeckPanel extends JPanel {
	
	public DeckPanel(String[] flashcardList) {
		setLayout(new GridLayout(1, 2));
		
		JPanel deckOptionsPanel = new JPanel();
		add(deckOptionsPanel);
		deckOptionsPanel.setLayout(new GridLayout(3, 1));
		
		JPanel studyDeckPanel = new JPanel();
		deckOptionsPanel.add(studyDeckPanel);
		
		JButton studyDeckButton = new JButton("Study");
		studyDeckPanel.add(studyDeckButton);
		
		JPanel studyAllDeckPanel = new JPanel();
		deckOptionsPanel.add(studyAllDeckPanel);
		
		JButton studyAllDeckButton = new JButton("Study all");
		studyAllDeckPanel.add(studyAllDeckButton);
		
		JPanel deleteDeckPanel = new JPanel();
		deckOptionsPanel.add(deleteDeckPanel);
		
		JButton deleteDeckButton = new JButton("Delete deck");
		deleteDeckPanel.add(deleteDeckButton);
		
		JPanel flashcardsPanel = new JPanel();
		add(flashcardsPanel);
		flashcardsPanel.setLayout(new BorderLayout());
		
		JPanel flashcardOptionsPanel = new JPanel();
		flashcardsPanel.add(flashcardOptionsPanel, BorderLayout.SOUTH);
		
		JButton addFlashcardsButton = new JButton("Add flashcard");
		flashcardOptionsPanel.add(addFlashcardsButton);
		
		JButton deleteFlashcardButton = new JButton("Delete flashcard");
		flashcardOptionsPanel.add(deleteFlashcardButton);
		
		JList<String> list = new JList<String>(flashcardList);
		flashcardsPanel.add(list, BorderLayout.CENTER);
	}

}
