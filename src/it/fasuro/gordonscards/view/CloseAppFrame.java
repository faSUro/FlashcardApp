package it.fasuro.gordonscards.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.fasuro.gordonscards.StartMenuOptions;
import it.fasuro.gordonscards.utilities.PathHandler;

@SuppressWarnings("serial")
public class CloseAppFrame extends JFrame {
	
	private final static int WIDTH = 700;
	private final static int HEIGHT = 200;
	
	public CloseAppFrame(JFrame studyDeckFrame, StartMenuOptions option) {
		setTitle("Gordon's Card");
		try {
			String iconPath = "res" + PathHandler.getSeparator() + "icon.png"; //sets frame icon
			setIconImage(ImageIO.read(new File(iconPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(WIDTH, HEIGHT);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		
		JLabel closeLabel = new JLabel();
		closeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		switch (option) {
		case CREATE_FLASHCARDS:
			closeLabel.setText("     Your deck has been created, relaunch the app to study it now!");
			break;
		case STUDY_DECK:
			closeLabel.setText("                Congratulations! You've studied the entire deck!");
			break;
		}
		
		mainPanel.add(closeLabel, BorderLayout.CENTER);
	
		setLocationRelativeTo(null);
		setVisible(true);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}
			public void windowClosing(WindowEvent e) {
				studyDeckFrame.dispose();
				dispose();
			}
			public void windowClosed(WindowEvent e) {
			}
			public void windowIconified(WindowEvent e) {
			}
			public void windowDeiconified(WindowEvent e) {
			}
			public void windowActivated(WindowEvent e) {
			}
			public void windowDeactivated(WindowEvent e) {
			}
		});
	}

}
