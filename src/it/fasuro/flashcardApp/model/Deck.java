package it.fasuro.flashcardApp.model;

import java.io.File;
import java.util.Date;
import java.util.TreeMap;

import it.fasuro.flashcardApp.IOHandler;

public class Deck {
	
	private TreeMap<String, Flashcard> fullDeck = new TreeMap<String, Flashcard>();
	private TreeMap<String, String> deckToStudy = new TreeMap<String, String>();
	
	private Date currentDate;
	
	public String deckPath;
	
	/**
	 * Sets the path of the folder in which are contained the
	 * flashcards of the selected deck, then it generates two treeMaps
	 * (starting from the flashcards text documents): one with 
	 * the entire deck and one with the flashcards to study today only.
	 * @param deckPath
	 */
	public Deck(String deckPath) {
		this.deckPath = deckPath;
		currentDate = new Date();
		
		fullDeck = generateFullDeck();
		deckToStudy = generateDeckToStudy(fullDeck);
	}
	
	/**
	 * Generates and returns the TreeMap containing all the flashcard
	 * inside the designed path.
	 */
	public TreeMap<String, Flashcard> generateFullDeck() {
		TreeMap<String, Flashcard> buffDeck = new TreeMap<String, Flashcard>();

		for (String fileName : new File(deckPath).list()) {			
			Flashcard flashcard = new Flashcard(deckPath, fileName, IOHandler.getFlashcardDocument(deckPath + "/" + fileName));
			buffDeck.put(flashcard.getQuestion(), flashcard);
		}
		
		return buffDeck;
	}

	/**
	 * Generate and returns the TreeMap containing the questions and
	 * answers to study today, starting from the full deck previously
	 * generated.
	 * @param fullDeck
	 */
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

	public TreeMap<String, Flashcard> getFullDeck() {
		return fullDeck;
	}

	public TreeMap<String, String> getDeckToStudy() {
		return deckToStudy;
	}

	public void setDeckPath(String deckPath) {
		this.deckPath = deckPath;
	}

}
