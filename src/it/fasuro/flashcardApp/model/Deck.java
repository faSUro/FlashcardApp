package it.fasuro.flashcardApp.model;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

public class Deck {
	
	private ArrayList<Flashcard> fullDeck = new ArrayList<Flashcard>();
	private TreeMap<String, String> deckToStudy = new TreeMap<String, String>();
	
	public Deck() {
		fullDeck = generateFullDeck();
		
		deckToStudy = generateDeckToStudy(fullDeck);		
	}

	public TreeMap<String, String> generateDeckToStudy(ArrayList<Flashcard> fullDeck) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Flashcard> generateFullDeck() {
		ArrayList<Flashcard> buffDeck = new ArrayList<Flashcard>();

		for (String fileName : new File("assets").list()) {			
			Flashcard flashcard = new Flashcard(fileName, IOHandler.getFlashcardDocument(fileName));
			buffDeck.add(flashcard);
			System.out.println(flashcard.getDateToRepeat());
		}
		
		return buffDeck;
	}

	public TreeMap<String, String> getDeckToStudy() {
		return deckToStudy;
	}

}
