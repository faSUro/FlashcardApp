package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.model.Flashcard;
import it.fasuro.gordonscards.view.createdeck.CreateFlashcardsFrame;
import it.fasuro.gordonscards.view.mainmenu.MainMenuFrame;

/**
 * This class connects the different layers of the program: the model
 * (that contains the flashcards) and the GUI.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class CreateFlashcardsLauncher {
	
	private MainMenuFrame mainMenuGUI;
	private CreateFlashcardsFrame CREATE_DECK_GUI;
	private String DECK_PATH;
	

	/**
	 * Creates a new frame to select the folder containing the flashcard
	 * (with a JFileChooser); then it calls the method that allows to
	 * create new flashcards/study an existent deck, depending on the
	 * option passed as argument.
	 * @param gui 
	 * @param option
	 * 
	 */
	public CreateFlashcardsLauncher(MainMenuFrame mainMenuGUI, String deckPath) {
		this.mainMenuGUI = mainMenuGUI;
		DECK_PATH = deckPath;
		start();
	}
	
	/**
	 * Creates a frame to create new flashcards and adds its listeners.
	 * 
	 */
	private void start() {
		CREATE_DECK_GUI = new CreateFlashcardsFrame();
		
		CREATE_DECK_GUI.getCreateFlashcardButton().addActionListener(CREATE_FLASHCARD_LISTENER);
		CREATE_DECK_GUI.getEndButton().addActionListener(END_APP_LISTENER);
	}
	
	/**
	 * Listener that creates a new flashcard.
	 * 
	 */
	private ActionListener CREATE_FLASHCARD_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String body = CREATE_DECK_GUI.getQuestion() + "\n" + CREATE_DECK_GUI.getAnswer();
			
			Flashcard flashcard = new Flashcard(DECK_PATH, body);
			flashcard.printFlashcard();
			
			CREATE_DECK_GUI.initializeQuestionTextField();
			CREATE_DECK_GUI.initializeAnswerArea();
		}
	};
	
	/**
	 * Listener that exits from the application.
	 * 
	 */
	private ActionListener END_APP_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mainMenuGUI.refreshDeckPanel(new Deck(DECK_PATH).getFlashcardList()); //da sostituire come una chiamata diretta a mainmenugui (singleton)
			CREATE_DECK_GUI.dispose();;
		}
	};

}








