package it.fasuro.flashcardApp.model;

import java.io.File;
import java.util.Date;
import java.util.TreeMap;

public class Deck {
	
	private TreeMap<String, Flashcard> fullDeck = new TreeMap<String, Flashcard>();
	private TreeMap<String, String> deckToStudy = new TreeMap<String, String>();
	
	private Date currentDate;
	
	public Deck() {
		currentDate = new Date();
		
		fullDeck = generateFullDeck();
		deckToStudy = generateDeckToStudy(fullDeck);
	}

	public TreeMap<String, String> generateDeckToStudy(TreeMap<String, Flashcard> fullDeck) {
		TreeMap<String, String> buffDeck = new TreeMap<String, String>();
		
		for (String s : fullDeck.keySet()) {
			Flashcard f = fullDeck.get(s);
			
			if (f.getDateToRepeat().compareTo(currentDate) <= 0) {
				buffDeck.put(f.getQuestion(), f.getAnswer());
			}
		}
		return buffDeck;
	}

	public TreeMap<String, Flashcard> generateFullDeck() {
		TreeMap<String, Flashcard> buffDeck = new TreeMap<String, Flashcard>();

		for (String fileName : new File("assets").list()) {			
			Flashcard flashcard = new Flashcard(fileName, IOHandler.getFlashcardDocument(fileName));
			buffDeck.put(flashcard.getQuestion(), flashcard);
		}
		
		return buffDeck;
	}

	public TreeMap<String, Flashcard> getFullDeck() {
		return fullDeck;
	}

	public TreeMap<String, String> getDeckToStudy() {
		return deckToStudy;
	}

}
