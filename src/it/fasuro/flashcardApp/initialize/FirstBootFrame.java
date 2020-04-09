package it.fasuro.flashcardApp.initialize;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FirstBootFrame extends JFrame {
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 250;
	
	private JTextField pathTextField;
	private JButton browseButton; 
	private JButton okButton; 
	
	public FirstBootFrame() {
		setTitle("FlashcardApp");
		this.setSize(WIDTH, HEIGHT);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new GridLayout(5, 1));
		mainPanel.setBackground(Color.WHITE);
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.WHITE);
		
		JPanel requestPanel = new JPanel();
		requestPanel.setBackground(Color.WHITE);
		JLabel requestLabel = new JLabel("Insert the path of the folder containing the flashcards: ");
		requestLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		requestPanel.add(requestLabel);
		
		JPanel pathPanel = new JPanel();
		pathPanel.setBackground(Color.WHITE);
		pathTextField = new JTextField(25);
		browseButton = new JButton(" Browse ");
		browseButton.setFont(new Font("Dialog", Font.BOLD, 15));
		pathPanel.add(pathTextField); pathPanel.add(browseButton);
		
		JPanel okPanel = new JPanel();
		okPanel.setBackground(Color.WHITE);
		okButton = new JButton("  Ok  ");
		okButton.setFont(new Font("Dialog", Font.BOLD, 15));
		okPanel.add(okButton);
		
		mainPanel.add(emptyPanel); mainPanel.add(requestPanel); mainPanel.add(pathPanel); mainPanel.add(okPanel);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public String getPath() {
		return pathTextField.getText();
	}
	
	public void setPath(String path) {
		pathTextField.setText(path);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getBrowseButton() {
		return browseButton;
	}
	
}
