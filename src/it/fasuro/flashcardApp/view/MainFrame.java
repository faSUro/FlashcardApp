package it.fasuro.flashcardApp.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public static int WIDTH = 500;
	public static int HEIGHT = 500;
	
	public MainFrame() {
		setTitle("FlashcardApp");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}

}
