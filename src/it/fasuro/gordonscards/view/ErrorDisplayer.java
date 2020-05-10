package it.fasuro.gordonscards.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ErrorDisplayer extends JFrame {
	
	private static int WIDTH = 500;
	private static int HEIGHT = 150;
	
	public ErrorDisplayer(String error) {
		setTitle("Something went wrong...");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		
		mainPanel.setLayout(new BorderLayout());
		
		JLabel errorLabel = new JLabel(error);
		errorLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		mainPanel.add(errorLabel, BorderLayout.CENTER);
		
		contentPane.add(mainPanel);
		
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
