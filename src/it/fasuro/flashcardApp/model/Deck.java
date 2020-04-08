package it.fasuro.flashcardApp.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class Deck {
	
	private ArrayList<Flashcard> fullDeck = new ArrayList<Flashcard>();
	private TreeMap<String, String> deckToStudy = new TreeMap<String, String>();
	
	private Date currentDate;
	
	public Deck() {
		currentDate = new Date();
		
		fullDeck = generateFullDeck();
		deckToStudy = generateDeckToStudy(fullDeck);
	}

	public TreeMap<String, String> generateDeckToStudy(ArrayList<Flashcard> fullDeck) {
		TreeMap<String, String> buffDeck = new TreeMap<String, String>();
		
		for (Flashcard f : fullDeck) {
			if (f.getDateToRepeat().compareTo(currentDate) <= 0) {
				buffDeck.put(f.getQuestion(), f.getAnswer());
			}
		}
		return buffDeck;
	}

	public ArrayList<Flashcard> generateFullDeck() {
		ArrayList<Flashcard> buffDeck = new ArrayList<Flashcard>();

		for (String fileName : new File("assets").list()) {			
			Flashcard flashcard = new Flashcard(fileName, IOHandler.getFlashcardDocument(fileName));
			buffDeck.add(flashcard);
		}
		
		return buffDeck;
	}

	public TreeMap<String, String> getDeckToStudy() {
		return deckToStudy;
	}

}
