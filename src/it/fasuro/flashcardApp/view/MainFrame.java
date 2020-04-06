package it.fasuro.flashcardApp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	
	private JPanel answerPanel;
	private JScrollPane answerScrollPane;
	
	private JButton showAnswerButton;
	private JLabel questionLabel;
	private JTextArea answerArea;
	private JButton easyButton;
	private JButton mediumButton;
	private JButton hardButton;
	
	public MainFrame() {
		setTitle("FlashcardApp");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel questionPanel = new JPanel();
		questionLabel = new JLabel("Let's start!");
		questionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		questionPanel.add(questionLabel);
		
		answerPanel = new JPanel();
		showAnswerButton = new JButton("  Press this button to start!  ");
		showAnswerButton.setFont(new Font("Dialog", Font.BOLD, 18));
		answerPanel.add(showAnswerButton);
		
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridLayout(1, 3));
		
		easyButton = new JButton(" Easy ");
		easyButton.setFont(new Font("Dialog", Font.BOLD, 18));
		easyButton.setBackground(Color.GREEN);
		
		mediumButton = new JButton(" Medium ");
		mediumButton.setFont(new Font("Dialog", Font.BOLD, 18));
		mediumButton.setBackground(Color.YELLOW);
		
		hardButton = new JButton(" Hard ");
		hardButton.setFont(new Font("Dialog", Font.BOLD, 18));
		hardButton.setBackground(Color.RED);
		
		difficultyPanel.add(easyButton);
		difficultyPanel.add(mediumButton);
		difficultyPanel.add(hardButton);
		
		
		mainPanel.add(questionPanel, BorderLayout.NORTH);
		mainPanel.add(answerPanel, BorderLayout.CENTER);
		mainPanel.add(difficultyPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JButton getShowAnswerButton() {
		return showAnswerButton;
	}

	public JButton getEasyButton() {
		return easyButton;
	}

	public JButton getMediumButton() {
		return mediumButton;
	}

	public JButton getHardButton() {
		return hardButton;
	}
	
	

	/*public void setFirstQuestion(String question, String answer) {
		answerPanel.remove(showAnswerButton);
		answerArea = new JTextArea(answerPanel.getHeight()/15, answerPanel.getWidth()/10);
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		answerArea.setFont(new Font("Dialog", Font.BOLD, 18));
		answerScrollPane = new JScrollPane(answerArea);
		answerPanel.add(answerScrollPane);
		
		questionLabel.setText(question);
		answerArea.setText(answer);
		
		repaint();
		setVisible(true);
	} */
	
	public void setNewQuestion(String question, String answer) {
		questionLabel.setText(question);
		answerArea.setText(answer);
		
		repaint();
	}

	public void setFirstQuestion(String question) {
		questionLabel.setText(question);
		showAnswerButton.setText("  Show answer  ");
		
		answerArea = new JTextArea(answerPanel.getHeight()/15, answerPanel.getWidth()/10);
		answerArea.setFont(new Font("Dialog", Font.BOLD, 18));
		answerScrollPane = new JScrollPane(answerArea);
		
		repaint();
	}

	public void showAnswer(String answer) {
		answerPanel.remove(showAnswerButton);
		
		answerArea.setText(answer);
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		
		answerPanel.add(answerScrollPane);
		
		repaint();
		setVisible(true);
	}

}










