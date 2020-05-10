package it.fasuro.gordonscards.view.studydeck;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.fasuro.gordonscards.utilities.PathHandler;

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
		
		//sets different scales depending on the OS
		int heightScale = 26;
		int widthScale = 20;
		if (PathHandler.isWindows()) {
			heightScale = 29;
			widthScale = 16;
		}
		
		answerArea = new JTextArea(screenSize.height/heightScale, screenSize.width/widthScale); 
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
