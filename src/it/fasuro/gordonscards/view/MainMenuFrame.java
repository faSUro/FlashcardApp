package it.fasuro.gordonscards.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import it.fasuro.gordonscards.utilities.PathAndOSHandler;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {
	
	private final static int WIDTH = 600;
	private final static int HEIGHT = 300;
	
	public MainMenuFrame(String[] list1, String[] list2) {
		setTitle("Gordon's Card");
		try {
			String iconPath = "res" + PathAndOSHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(WIDTH, HEIGHT);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblSelectADeck = new JLabel("Select a deck: ");
		panel_1.add(lblSelectADeck);
		
		JComboBox<String> comboBox = new JComboBox<String>(list1);
		panel_1.add(comboBox);
		
		JButton btnOk = new JButton("Ok");
		panel_1.add(btnOk);
		
		JButton btnCreateNewDeck = new JButton("Create new deck");
		panel_1.add(btnCreateNewDeck);
		
		JButton btnImportDeck = new JButton("Import deck");
		panel_1.add(btnImportDeck);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 2));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 1));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		JButton btnStudy = new JButton("Study");
		panel_6.add(btnStudy);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		JButton btnStudyAll = new JButton("Study all");
		panel_7.add(btnStudyAll);
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		JButton btnDeleteDeck = new JButton("Delete deck");
		panel_8.add(btnDeleteDeck);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnAddFlashcard = new JButton("Add flashcard");
		panel_5.add(btnAddFlashcard);
		
		JButton btnDeleteFlashcard = new JButton("Delete flashcard");
		panel_5.add(btnDeleteFlashcard);
		
		JList<String> list = new JList<String>(list2);
		panel_4.add(list, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
