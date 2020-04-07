package it.fasuro.flashcardApp.view;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AnswerPanel extends JPanel {
	
	public AnswerPanel(String answer) {
		JTextArea answerArea = new JTextArea(42, 100);
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(new Font("Dialog", Font.BOLD, 18));

		JScrollPane answerScrollPane = new JScrollPane(answerArea);
		add(answerScrollPane);
		
		answerArea.setText(answer);

		repaint();
	}

}
