package it.fasuro.flashcardApp.view.studyDeck;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Contains the current question and the button 
 * that allows to show the answer.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
@SuppressWarnings("serial")
public class QuestionPanel extends JPanel {
	
	private JLabel questionLabel;
	private JButton showAnswerButton;
	
	public QuestionPanel() {
		//the label text and the button text will change after the first click.
		questionLabel = new JLabel("  Let's start  ");
		questionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		showAnswerButton = new JButton("  Go!  ");
		showAnswerButton.setFont(new Font("Dialog", Font.BOLD, 18));
		
		add(questionLabel);
		add(showAnswerButton);
	}

	public void setQuestion(String question) {
		questionLabel.setText(question);
		repaint();
	}
	
	public JButton getShowAnswerButton() {
		return showAnswerButton;
	}
	
	public void setShowAnswerButtonText() {
		showAnswerButton.setText("  Show answer  ");
	}

}
