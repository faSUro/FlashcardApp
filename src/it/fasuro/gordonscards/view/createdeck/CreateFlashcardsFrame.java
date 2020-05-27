package it.fasuro.gordonscards.view.createdeck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.fasuro.gordonscards.utilities.OSDistinguisher;
import it.fasuro.gordonscards.utilities.PathHandler;

/**
 * Frame that allows to create new flashcards.
 * Contains a text field to insert the question, a text area for
 * the answer and two buttons: one to create the flashcard and one
 * to terminate the creation.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 * 
 */
@SuppressWarnings("serial")
public class CreateFlashcardsFrame extends JFrame {
	
	private JTextField questionTextField;
	private JTextArea answerArea;
	private JButton createFlashcardButton;
	private JButton endButton;

	public CreateFlashcardsFrame() {
		setTitle("Gordon's Card");
		try {
			String iconPath = "res" + PathHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		
		//panel with an "Insert question" label and the text field for the question.
		JPanel questionPanel = new JPanel();
		JLabel insertQuestionLabel = new JLabel("Insert question: ");
		insertQuestionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		questionTextField = new JTextField(50);
		questionTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
		questionPanel.add(insertQuestionLabel); questionPanel.add(questionTextField);
		
		//sets different scales depending on the OS
				int heightScale = 27;
				int widthScale = 18;
				if (OSDistinguisher.isWindows()) {
					heightScale = 30;
					widthScale = 15;
				}
		
		//panel with an "Insert answer" label and a scrollable text area for the answer.
		JPanel answerPanel = new JPanel();
		JLabel insertAnswerLabel = new JLabel("Insert answer: ");
		insertAnswerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		answerArea = new JTextArea(screenSize.height/heightScale, screenSize.width/widthScale); 
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(new Font("Dialog", Font.PLAIN, 18));
		JScrollPane answerScrollPane = new JScrollPane(answerArea);
		answerPanel.add(insertAnswerLabel); answerPanel.add(answerScrollPane);
		
		//panel with a "create flashcard" button and an "end" button
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));
		JPanel createFlashcardPanel = new JPanel();
		createFlashcardButton = new JButton("  Create flashcard  ");
		createFlashcardButton.setFont(new Font("Dialog", Font.BOLD, 18));
		createFlashcardPanel.add(createFlashcardButton);
		JPanel endPanel = new JPanel();
		endButton = new JButton("  End  ");
		endButton.setFont(new Font("Dialog", Font.BOLD, 18));
		endPanel.add(endButton);
		buttonsPanel.add(createFlashcardPanel); buttonsPanel.add(endPanel);
		
		mainPanel.add(questionPanel, BorderLayout.NORTH);
		mainPanel.add(answerPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public String getQuestion() {
		return questionTextField.getText();
	}

	public void initializeQuestionTextField() {
		questionTextField.setText("");;
	}

	public String getAnswer() {
		return answerArea.getText();
	}

	public void initializeAnswerArea() {
		answerArea.setText("");;
	}

	public JButton getCreateFlashcardButton() {
		return createFlashcardButton;
	}

	public JButton getEndButton() {
		return endButton;
	}

}
