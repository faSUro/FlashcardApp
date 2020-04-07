package it.fasuro.flashcardApp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DifficultyPanel extends JPanel {
	
	private JButton easyButton;
	private JButton mediumButton;
	private JButton hardButton;
	
	public DifficultyPanel() {
		setLayout(new GridLayout(1, 3));
		
		easyButton = new JButton(" Easy ");
		easyButton.setFont(new Font("Dialog", Font.BOLD, 18));
		easyButton.setBackground(Color.GREEN);
		
		mediumButton = new JButton(" Medium ");
		mediumButton.setFont(new Font("Dialog", Font.BOLD, 18));
		mediumButton.setBackground(Color.YELLOW);
		
		hardButton = new JButton(" Hard ");
		hardButton.setFont(new Font("Dialog", Font.BOLD, 18));
		hardButton.setBackground(Color.RED);
		
		add(easyButton);
		add(mediumButton);
		add(hardButton);		
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

}
