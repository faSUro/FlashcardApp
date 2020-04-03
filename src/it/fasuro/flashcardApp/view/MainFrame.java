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

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	
	private JLabel questionLabel;
	private JButton showAnswerButton;
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
		
		JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new BorderLayout());
		showAnswerButton = new JButton("  Start  ");
		showAnswerButton.setFont(new Font("Dialog", Font.BOLD, 18));
		showAnswerButton.setBackground(Color.WHITE);
		answerPanel.add(showAnswerButton, BorderLayout.CENTER);
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridLayout(1, 3));
		easyButton = new JButton(" Easy ");
		easyButton.setFont(new Font("Dialog", Font.BOLD, 18));
		mediumButton = new JButton(" Medium ");
		mediumButton.setFont(new Font("Dialog", Font.BOLD, 18));
		hardButton = new JButton(" Hard ");
		hardButton.setFont(new Font("Dialog", Font.BOLD, 18));
		difficultyPanel.add(easyButton);
		difficultyPanel.add(mediumButton);
		difficultyPanel.add(hardButton);
		
		mainPanel.add(questionPanel, BorderLayout.NORTH);
		mainPanel.add(answerPanel, BorderLayout.CENTER);
		mainPanel.add(difficultyPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

}
