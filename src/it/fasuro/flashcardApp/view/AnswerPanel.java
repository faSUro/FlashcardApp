package it.fasuro.flashcardApp.view;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AnswerPanel extends JPanel {
	
	private JTextArea answerArea;
	
	public AnswerPanel(String answer) {
		answerArea = new JTextArea(42, 100);
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(new Font("Dialog", Font.BOLD, 18));

		JScrollPane answerScrollPane = new JScrollPane(answerArea);
		add(answerScrollPane);
		
		answerArea.setText(answer);
	}

	public JTextArea getAnswerArea() {
		return answerArea;
	}

	public void setAnswer(String answer) {
		answerArea.setText(answer);
	}

}
