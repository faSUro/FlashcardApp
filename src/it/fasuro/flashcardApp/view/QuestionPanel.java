package it.fasuro.flashcardApp.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class QuestionPanel extends JPanel {
	
	private JLabel questionLabel;
	
	public QuestionPanel() {
		questionLabel = new JLabel("  Let's start  ");
		questionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		add(questionLabel);
	}

	public JLabel getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestion(String question) {
		questionLabel.setText(question);
		repaint();
	}

}
