package it.fasuro.flashcardApp.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class QuestionPanel extends JPanel {
	
	private JLabel questionLabel;
	private JButton showAnswerButton;
	
	public QuestionPanel() {
		questionLabel = new JLabel("  Let's start  ");
		questionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		showAnswerButton = new JButton("  Go!  ");
		showAnswerButton.setFont(new Font("Dialog", Font.BOLD, 18));
		
		add(questionLabel);
		add(showAnswerButton);
	}

	public JLabel getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestion(String question) {
		questionLabel.setText(question);
		repaint();
	}
	
	public JButton getShowAnswerButton() {
		return showAnswerButton;
	}

}
