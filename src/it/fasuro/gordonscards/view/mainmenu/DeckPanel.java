package it.fasuro.gordonscards.view.mainmenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DeckPanel extends JPanel {
	
	private JPanel flashcardsPanel;
	private JList<Object> list;
	private JButton studyDeckButton;
	private JButton studyAllDeckButton;
	private JButton deleteDeckButton;
	private JButton addFlashcardsButton;
	private JButton deleteFlashcardButton;
	
	public DeckPanel(ArrayList<String> flashcardsList) {
		setLayout(new GridLayout(1, 2));
		
		JPanel deckOptionsPanel = new JPanel();
		add(deckOptionsPanel);
		deckOptionsPanel.setLayout(new GridLayout(3, 1));
		
		JPanel studyDeckPanel = new JPanel();
		deckOptionsPanel.add(studyDeckPanel);
		
		studyDeckButton = new JButton("Study");
		studyDeckPanel.add(studyDeckButton);
		
		JPanel studyAllDeckPanel = new JPanel();
		deckOptionsPanel.add(studyAllDeckPanel);
		
		studyAllDeckButton = new JButton("Study all");
		studyAllDeckPanel.add(studyAllDeckButton);
		
		JPanel deleteDeckPanel = new JPanel();
		deckOptionsPanel.add(deleteDeckPanel);
		
		deleteDeckButton = new JButton("Delete deck");
		deleteDeckPanel.add(deleteDeckButton);
		
		flashcardsPanel = new JPanel();
		add(flashcardsPanel);
		flashcardsPanel.setLayout(new BorderLayout());
		
		JPanel flashcardOptionsPanel = new JPanel();
		flashcardsPanel.add(flashcardOptionsPanel, BorderLayout.SOUTH);
		
		addFlashcardsButton = new JButton("Add flashcard");
		flashcardOptionsPanel.add(addFlashcardsButton);
		
		deleteFlashcardButton = new JButton("Delete flashcard");
		flashcardOptionsPanel.add(deleteFlashcardButton);
		
		refreshFlashcardsPanel(flashcardsList);
	}

	public void refreshFlashcardsPanel(ArrayList<String> newFlashcardList) {
		if (list != null) {
			flashcardsPanel.remove(list);
		}

		list = new JList<Object>(newFlashcardList.toArray());
		flashcardsPanel.add(list, BorderLayout.CENTER);

		revalidate();
	}
	
	public String getSelectedFlashcard() {
		return (String) list.getSelectedValue();
	}

	public JButton getStudyDeckButton() {
		return studyDeckButton;
	}

	public JButton getStudyAllDeckButton() {
		return studyAllDeckButton;
	}

	public JButton getDeleteDeckButton() {
		return deleteDeckButton;
	}

	public JButton getAddFlashcardsButton() {
		return addFlashcardsButton;
	}

	public JButton getDeleteFlashcardButton() {
		return deleteFlashcardButton;
	}

}
