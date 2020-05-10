package it.fasuro.flashcardApp.view.studyDeck;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Contains the answer to the current question, only 
 * visible after clicking the "show answer" button.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
@SuppressWarnings("serial")
public class AnswerPanel extends JPanel {
	
	private JTextArea answerArea;
	
	public AnswerPanel(String answer) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		answerArea = new JTextArea(screenSize.height/26, screenSize.width/20); //if in Windows: change the 26-20 parameters
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(new Font("Dialog", Font.BOLD, 18));

		JScrollPane answerScrollPane = new JScrollPane(answerArea);
		add(answerScrollPane);
		
		answerArea.setText(answer);
	}

	public void setAnswer(String answer) {
		answerArea.setText(answer);
	}

}
