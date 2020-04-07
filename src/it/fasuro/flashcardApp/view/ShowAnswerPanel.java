package it.fasuro.flashcardApp.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShowAnswerPanel extends JPanel {
	
	private JButton showAnswerButton;
	
	public ShowAnswerPanel() {
		showAnswerButton = new JButton("  Start  ");
		showAnswerButton.setFont(new Font("Dialog", Font.BOLD, 18));
		add(showAnswerButton);
	}

	public JButton getShowAnswerButton() {
		return showAnswerButton;
	}

	public void setShowAnswer() {
		showAnswerButton.setText("  Show answer  ");
		repaint();
	}

}
