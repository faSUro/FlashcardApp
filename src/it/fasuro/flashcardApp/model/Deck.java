package it.fasuro.flashcardApp.model;

import java.io.File;
import java.util.Date;
import java.util.TreeMap;

import it.fasuro.flashcardApp.IOHandler;
import it.fasuro.flashcardApp.view.ErrorDisplayer;

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

		try {
			File deck = new File(deckPath);
			if (!isValidPath(deck)) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			new ErrorDisplayer("               You've entered an invalid path!");
			return null;
		}
		
		for (String fileName : new File(deckPath).list()) {	
			if (fileName.endsWith(".txt")) {
				Flashcard flashcard = new Flashcard(deckPath, fileName, IOHandler.getFlashcardDocument(deckPath + "/" + fileName));
				buffDeck.put(flashcard.getQuestion(), flashcard);
			}
		}
		
		try {
			if (buffDeck.isEmpty()) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			new ErrorDisplayer("     The selected folder doesn't contain any flashcard!");
			return null;
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
		
		if (fullDeck == null) {
			return null;
		}
		
		for (String s : fullDeck.keySet()) {
			Flashcard f = fullDeck.get(s);
			
			if (f.getDateToRepeat().compareTo(currentDate) <= 0) {
				buffDeck.put(f.getQuestion(), f.getAnswer());
			}
		}
		
		return buffDeck;
	}

	/**
	 * Check if a File is a valid folder path: returns true
	 * if it is valid, false otherwise.
	 * @param path
	 */
	private boolean isValidPath(File path) {
		if (!path.isDirectory()) {
			return false;
		}
		
		if(path.exists()) {
			return true;
		} else {
			return false;
		}		
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
