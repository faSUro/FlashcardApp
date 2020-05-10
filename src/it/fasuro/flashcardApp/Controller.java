package it.fasuro.flashcardApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFileChooser;

import it.fasuro.flashcardApp.model.Deck;
import it.fasuro.flashcardApp.model.Flashcard;
import it.fasuro.flashcardApp.view.BrowseFolderFrame;
import it.fasuro.flashcardApp.view.CloseAppFrame;
import it.fasuro.flashcardApp.view.ErrorDisplayer;
import it.fasuro.flashcardApp.view.createDeck.CreateFlashcardsFrame;
import it.fasuro.flashcardApp.view.studyDeck.StudyDeckFrame;

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
	 * Initializes the DECK static variable and makes
	 * an array that contains the key set. It also 
	 * initializes the TOTAL_QUESTIONS variable (with 
	 * the length of the key set).
	 * @param option
	 */
	public Controller(StartMenuOptions option) {
		CHOSEN_OPTION = option;
		BrowseFolderFrame browseFrame = new BrowseFolderFrame();
		
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
		
		browseFrame.getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DECK_PATH = browseFrame.getPath();
				
				try {
					File deck = new File(DECK_PATH);
					if (!isValidPath(deck)) {
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException ex) {
					new ErrorDisplayer("               You've entered an invalid path!");
					return;
				}
				
				browseFrame.dispose();
				
				switch (option) {
				case CREATE_DECK:
					createDeck();
					break;
				case STUDY_DECK:
					studyDeck();
				}
			}
		});			
	}
	
	public void createDeck() {
		CREATE_DECK_GUI = new CreateFlashcardsFrame();
		
		CREATE_DECK_GUI.getCreateFlashcardButton().addActionListener(CREATE_FLASHCARD_LISTENER);
		CREATE_DECK_GUI.getEndButton().addActionListener(END_APP_LISTENER);
	}

	public void studyDeck() {
		DECK_MODEL = new Deck(DECK_PATH);		
		STUDY_DECK_GUI = new StudyDeckFrame();
		
		if (DECK_MODEL.getFullDeck() == null) {
			return;
		}
		
		FULL_DECK = DECK_MODEL.getFullDeck();
		DECK_TO_STUDY = DECK_MODEL.getDeckToStudy();
		
		KEY_SET = new ArrayList<String>(DECK_TO_STUDY.keySet());
		TOTAL_QUESTIONS = KEY_SET.size();
		
		STUDY_DECK_GUI.getQuestionPanel().getShowAnswerButton().addActionListener(FIRST_QUESTION_LISTENER);
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

	private static void setShowAnswerListener() {
		STUDY_DECK_GUI.getQuestionPanel().getShowAnswerButton().removeActionListener(FIRST_QUESTION_LISTENER);
		
		STUDY_DECK_GUI.getQuestionPanel().getShowAnswerButton().removeActionListener(SHOW_ANSWER_LISTENER);
		STUDY_DECK_GUI.getQuestionPanel().getShowAnswerButton().addActionListener(SHOW_ANSWER_LISTENER);
	}

	private static void setNextQuestionListener() {
		STUDY_DECK_GUI.getDifficultyPanel().getEasyButton().removeActionListener(NEXT_QUESTION_LISTENER_EASY);		
		STUDY_DECK_GUI.getDifficultyPanel().getEasyButton().addActionListener(NEXT_QUESTION_LISTENER_EASY);		
		
		STUDY_DECK_GUI.getDifficultyPanel().getMediumButton().removeActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		STUDY_DECK_GUI.getDifficultyPanel().getMediumButton().addActionListener(NEXT_QUESTION_LISTENER_MEDIUM);		
		
		STUDY_DECK_GUI.getDifficultyPanel().getHardButton().removeActionListener(NEXT_QUESTION_LISTENER_HARD);		
		STUDY_DECK_GUI.getDifficultyPanel().getHardButton().addActionListener(NEXT_QUESTION_LISTENER_HARD);		
	}	
	
	private static ActionListener FIRST_QUESTION_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (TOTAL_QUESTIONS == 0) {
				new CloseAppFrame(CHOSEN_OPTION);
			} else {
				STUDY_DECK_GUI.getQuestionPanel().getQuestionLabel().setText(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.getQuestionPanel().getShowAnswerButton().setText("  Show answer  ");
			
				setShowAnswerListener();
			}
		}	
	};
	
	private static ActionListener SHOW_ANSWER_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			STUDY_DECK_GUI.getAnswerPanel().setAnswer(DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			
			setNextQuestionListener();	
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_EASY = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.EASY);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.getQuestionPanel().setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				new CloseAppFrame(CHOSEN_OPTION);
			}
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_MEDIUM = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FULL_DECK.get(KEY_SET.get(COUNTER)).modifyDate(Difficulty.MEDIUM);
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.getQuestionPanel().setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				new CloseAppFrame(CHOSEN_OPTION);
			}
		}		
	};
	
	private static ActionListener NEXT_QUESTION_LISTENER_HARD = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			KEY_SET.add(KEY_SET.get(COUNTER));
			DECK_TO_STUDY.put(KEY_SET.get(COUNTER), DECK_TO_STUDY.get(KEY_SET.get(COUNTER)));
			TOTAL_QUESTIONS++;
			
			COUNTER++;
			if (COUNTER < TOTAL_QUESTIONS) {
				STUDY_DECK_GUI.getQuestionPanel().setQuestion(KEY_SET.get(COUNTER));
				STUDY_DECK_GUI.getAnswerPanel().setAnswer("");
			
				setShowAnswerListener();	
			} else {
				new CloseAppFrame(CHOSEN_OPTION);
			}
		}		
	};
	
	private static ActionListener CREATE_FLASHCARD_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String body = CREATE_DECK_GUI.getQuestion() + "\n" + CREATE_DECK_GUI.getAnswer();
			
			Flashcard flashcard = new Flashcard(DECK_PATH, body);
			flashcard.reprintFlashcard();
			
			CREATE_DECK_GUI.initializeQuestionTextField();
			CREATE_DECK_GUI.initializeAnswerArea();
		}
	};
	
	private static ActionListener END_APP_LISTENER = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

}






























