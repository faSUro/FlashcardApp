package it.fasuro.gordonscards.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.fasuro.gordonscards.utilities.PathHandler;

@SuppressWarnings("serial")
public class ErrorDisplayer extends JFrame {
	
	private static int WIDTH = 500;
	private static int HEIGHT = 150;
	
	public ErrorDisplayer(String error) {
		setTitle("Something went wrong...");
		try {
			String iconPath = "res" + PathHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		
		mainPanel.setLayout(new BorderLayout());
		
		JLabel errorLabel = new JLabel(error);
		errorLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		mainPanel.add(errorLabel, BorderLayout.CENTER);
		
		contentPane.add(mainPanel);
		
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
