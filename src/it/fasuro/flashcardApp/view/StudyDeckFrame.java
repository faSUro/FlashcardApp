package it.fasuro.flashcardApp.view;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StudyDeckFrame extends JFrame {
	
	private JPanel mainPanel;
	private QuestionPanel questionPanel;
	private AnswerPanel answerPanel;
	private DifficultyPanel difficultyPanel;
	
	public StudyDeckFrame() {
		setTitle("FlashcardApp");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		
		questionPanel = new QuestionPanel();
		
		answerPanel = new AnswerPanel("");
		
		difficultyPanel = new DifficultyPanel();
		
		mainPanel.add(questionPanel, BorderLayout.NORTH);
		mainPanel.add(answerPanel, BorderLayout.CENTER);
		mainPanel.add(difficultyPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public QuestionPanel getQuestionPanel() {
		return questionPanel;
	}
	
	public AnswerPanel getAnswerPanel() {
		return answerPanel;
	}
	
	public DifficultyPanel getDifficultyPanel() {
		return difficultyPanel;
	}
	
}
