package it.fasuro.flashcardApp.view.createDeck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateDeckFrame extends JFrame {
	
	private JTextField questionTextField;
	private JTextArea answerArea;
	private JButton createFlashcardButton;
	private JButton endButton;

	public CreateDeckFrame() {
		setTitle("FlashcardApp");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel questionPanel = new JPanel();
		JLabel insertQuestionLabel = new JLabel("Insert question: ");
		insertQuestionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		questionTextField = new JTextField(50);
		questionTextField.setFont(new Font("Dialog", Font.PLAIN, 18));
		questionPanel.add(insertQuestionLabel); questionPanel.add(questionTextField);
		
		JPanel answerPanel = new JPanel();
		JLabel insertAnswerLabel = new JLabel("Insert answer: ");
		insertAnswerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		answerArea = new JTextArea(29, 100);
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(new Font("Dialog", Font.PLAIN, 18));
		JScrollPane answerScrollPane = new JScrollPane(answerArea);
		answerPanel.add(insertAnswerLabel); answerPanel.add(answerScrollPane);
		
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
