package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFileChooser;

import it.fasuro.gordonscards.model.Deck;
import it.fasuro.gordonscards.model.Flashcard;
import it.fasuro.gordonscards.utilities.PathHandler;
import it.fasuro.gordonscards.view.BrowseFolderFrame;
import it.fasuro.gordonscards.view.CloseAppFrame;
import it.fasuro.gordonscards.view.ErrorDisplayer;
import it.fasuro.gordonscards.view.createdeck.CreateFlashcardsFrame;
import it.fasuro.gordonscards.view.studydeck.StudyDeckFrame;

/**
 * This class connects the different layers of the program: the model
 * (that contains the flashcards) and the GUI.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class Controller {
	
	private static CreateFlashcardsFrame CREATE_DECK_GUI;
	private static StudyDeckFrame STUDY_DECK_GUI;
	private static Deck DECK_MODEL;
	
	private static StartMenuOptions CHOSEN_OPTION;
	private static String DECK_PATH;
	
	private static TreeMap<String, Flashcard> FULL_DECK;
	private static TreeMap<String, String> DECK_TO_STUDY;	
	private static int COUNTER = 0;
	private static int TOTAL_QUESTIONS;
	private static ArrayList<String> KEY_SET;
	

	/**
	 * Creates a new frame to select the folder containing the flashcard
	 * (with a JFileChooser); then it calls the method that allows to
	 * create new flashcards/study an existent deck, depending on the
	 * option passed as argument.
	 * @param option
	 * 
	 */
	public Controller(StartMenuOptions option) {
		CHOSEN_OPTION = option;
		BrowseFolderFrame browseFrame = new BrowseFolderFrame(); //creates the frame to select the folder
		
		/*
		 * This block adds an action listener to the browse button to create
		 * a file chooser.
		 * 
		 */
		browseFrame.getBrowseButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.setDialogTitle("Browse folder...");
				int result = jfc.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					browseFrame.setPath(file.getAbsolutePath());
				}				
			}			
		});
		
		/*
		 * This block adds an action listener to the ok button to 
		 * confirm the chosen path and start creating flashcards/studying.
		 * 
		 */
		browseFrame.getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DECK_PATH = browseFrame.getPath();
				
				try {
					File deck = new File(DECK_PATH);
					if (!PathHandler.isValidPath(deck)) {	//checks if the chosen path is valid
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException ex) {
					new ErrorDisplayer("                   You've entered an invalid path!");
					return;
				}
				
				browseFrame.dispose();
				
				switch (option) {
				case CREATE_FLASHCARDS:
					createFlashcards();
					break;
				case STUDY_DECK:
					studyDeck();
				}
			}
		});			
	}
	
	/**
	 * Creates a frame to create new flashcards and adds its listeners.
	 * 
	 */
	public void createFlashcards() {
		CREATE_DECK_GUI = new CreateFlashcardsFrame();
		
		CREATE_DECK_GUI.getCreateFlashcardButton().addActionListener(CREATE_FLASHCARD_LISTENER);
		CREATE_DECK_GUI.getEndButton().addActionListener(END_APP_LISTENER);
	}

	/**
	 * Initializes the DECK_MODEL variable and the GUI to study it.
	 * 
	 */
	public void studyDeck() {
		DECK_MODEL = new Deck(DECK_PATH);		
		STUDY_DECK_GUI = new StudyDeckFrame();
		
		if (DECK_MODEL.getFullDeck() == null) { //in case the deck folder is empty, the method "stops" here
			return;								//an exception has been thrown already
		}
		
		FULL_DECK = DECK_MODEL.getFullDeck();
		DECK_TO_STUDY = DECK_MODEL.getDeckToStudy();
		
		KEY_SET = new ArrayList<String>(DECK_TO_STUDY.keySet());
		TOTAL_QUESTIONS = KEY_SET.size();
		
		STUDY_DECK_GUI.getShowAnswerButton().addActionListener(FIRST_QUESTION_LISTENER); //adds the listener for the first click to start studying
	}

	/**
	 * Removes the previous listeners and resets the listener that shows the answer to the show answer button.
	 * 
	 */
	private static void setShowAnswerListener() {
		STUDY_DECK_GUI.getShowAnswerButton().removeActionListener(FIRST_QUESTION_LISTENER);
		
		STUDY_DECK_GUI.getShowAnswerButton().removeActionListener(SHOW_ANSWER_LISTENER);
		STUDY_DECK_GUI.getShowAnswerButton().addActionListener(SHOW_ANSWER_LISTENER);
	}

	/**
	 * Removes and resets the listeners of the three buttons that lead to the next question.
	 * 
	 */
	private static void setNextQuestionListener() {
		STUDY_DECK_GUI.getEasyButton().removeActionListener(NEXT_QUESTION_LISTENER_EASY);		
		STUDY_DECK_GUI.getEasyButton().addActionListener(NEXT_QUESTION_LISTENER_EASY);		
		
		STUDY_DECK_GUI.getMediumButton().removeActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		STUDY_DECK_GUI.getMediumButton().addActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		
		STUDY_DECK_GUI.getHardButton().removeActionListener(NEXT_QUESTION_LISTENER_HARD);		
		STUDY_DECK_GUI.getHardButton().addActionListener(NEXT_QUESTION_LISTENER_HARD);		
	}	
	
	/**
	 * Listener that set the first question and changes the button text to "Show answer".
	 * Then it set the following listener.
	 * 
	 */
	private static ActionListener FIRST_QUESTION_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (TOTAL_QUESTIONS == 0) {
				new CloseAppFrame(CHOSEN_OPTION);
			} else {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.resetShowAnswerButtonText();
			
				setShowAnswerListener();
			}
		}	
	};
	
	/**
	 * Listener that reveals the answer to the current question.
	 * Then it set the following listener.
	 * 
	 */
	private static ActionListener SHOW_ANSWER_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			STUDY_DECK_GUI.setAnswer(DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			
			setNextQuestionListener();	
		}		
	};
	
	/**
	 * Listener that sets the new question and that modifies the date in the current flashcard (easy case).
	 * Then it set the following listener.
	 * 
	 */
	private static ActionListener NEXT_QUESTION_LISTENER_EASY = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.EASY);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.setAnswer("");
			
				setShowAnswerListener();	
			} else {
				new CloseAppFrame(CHOSEN_OPTION);
			}
		}		
	};
	
	/**
	 * Listener that sets the new question and that modifies the date in the current flashcard (medium case).
	 * Then it set the following listener.
	 * 
	 */
	private static ActionListener NEXT_QUESTION_LISTENER_MEDIUM = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.MEDIUM);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.setAnswer("");
			
				setShowAnswerListener();	
			} else {
				new CloseAppFrame(CHOSEN_OPTION);
			}
		}		
	};
	
	/**
	 * Listener that sets the new question and re-adds the current question to the key set (hard case).
	 * Then it set the following listener.
	 * 
	 */
	private static ActionListener NEXT_QUESTION_LISTENER_HARD = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			KEY_SET.add(KEY_SET.get(COUNTER));
			DECK_TO_STUDY.put(KEY_SET.get(COUNTER), DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			TOTAL_QUESTIONS++;
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.setAnswer("");
			
				setShowAnswerListener();	
			} else {
				new CloseAppFrame(CHOSEN_OPTION);
			}
		}		
	};
	
	/**
	 * Listener that creates a new flashcard.
	 * 
	 */
	private static ActionListener CREATE_FLASHCARD_LISTENER = new ActionListener() {
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
	private static ActionListener END_APP_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

}








