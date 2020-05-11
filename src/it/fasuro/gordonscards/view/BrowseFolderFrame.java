package it.fasuro.gordonscards.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.fasuro.gordonscards.utilities.PathAndOSHandler;

@SuppressWarnings("serial")
public class BrowseFolderFrame extends JFrame {
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 250;
	
	private JTextField pathTextField;
	private JButton browseButton; 
	private JButton okButton; 
	
	public BrowseFolderFrame() {
		setTitle("Gordon's Card");
		try {
			String iconPath = "res" + PathAndOSHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setSize(WIDTH, HEIGHT);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new GridLayout(5, 1));
		
		JPanel emptyPanel = new JPanel();
		
		JPanel requestPanel = new JPanel();
		JLabel requestLabel = new JLabel("Insert the path of the deck folder: ");
		requestLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		requestPanel.add(requestLabel);
		
		JPanel pathPanel = new JPanel();
		pathTextField = new JTextField(25);
		browseButton = new JButton(" Browse ");
		browseButton.setFont(new Font("Dialog", Font.BOLD, 15));
		pathPanel.add(pathTextField); pathPanel.add(browseButton);
		
		JPanel okPanel = new JPanel();
		okButton = new JButton("  Ok  ");
		okButton.setFont(new Font("Dialog", Font.BOLD, 15));
		okPanel.add(okButton);
		
		mainPanel.add(emptyPanel); mainPanel.add(requestPanel); 
		mainPanel.add(pathPanel); mainPanel.add(okPanel);
		
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
