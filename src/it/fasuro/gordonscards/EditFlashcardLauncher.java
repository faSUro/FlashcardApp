package it.fasuro.gordonscards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.fasuro.gordonscards.model.Flashcard;

public class EditFlashcardLauncher {
	
	private MainController mainController;
	private EditFlashcardFrame editFlashcardGui;
	private Flashcard flashcard;
	
	public EditFlashcardLauncher(MainController mainController, Flashcard flashcard) {
		this.mainController = mainController;
		this.flashcard = flashcard;
		
		start();
	}
	
	private void start() {
		editFlashcardGui = new EditFlashcardFrame(flashcard.getQuestion(), flashcard.getAnswer());
		
		editFlashcardGui.getSaveButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flashcard.editFlashcard(editFlashcardGui.getNewQuestion(), editFlashcardGui.getNewAnswer());
				
				end();
			}
			
		});
	}

	private void end() {
		mainController.refreshMainMenu();
		editFlashcardGui.dispose();
	}

}
