package it.fasuro.gordonscards.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

/**
 * Allows to read and write on txt documents (flashcards).
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class IOTools {
	
	public static ArrayList<String> getDeckList() {
		ArrayList<String> deckList = new ArrayList<String>();
		deckList.add("                                                  "); //empty line to set minimum JComboBox width
		
		File file = new File(PathHandler.getDeckFolder());
		String[] deckFolders = file.list(new FilenameFilter() {
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		
		for (String deckName : deckFolders) {
			deckList.add(deckName);
		}
		
		return deckList;
	}
	
	public static void createDeck(String newDeck) throws IllegalArgumentException, IOException {		
		if (PathHandler.isValidPath(newDeck)) {
			File srcFolder = new File(newDeck); 
			File destFolder = new File(PathHandler.generateDeckPath(PathHandler.getDeckNameFromPath(newDeck))); //creates the dest folder maintaining 
																												//the same deck (updates the path)
			FileUtils.copyDirectory(srcFolder, destFolder);		
		} else {
			File deckFolder = new File(PathHandler.generateDeckPath(newDeck));
			if (!deckFolder.mkdir()) {
				throw new IllegalArgumentException();
			}
		}
	}
	
	public static void deleteFile(String filePath) throws IllegalArgumentException {
		File file = new File(filePath);

		if (file.isDirectory()) { //checks whether the file is a directory (i.e. a deck) or not (i.e. a flashcard)
			for (String flashcard : file.list()) { //deletes all flashcards before deleting the deck folder
				File flashcardFile = new File(file.getPath(), flashcard);
				flashcardFile.delete();
			}
		} 
		
	      if (!file.delete()) {
	         throw new IllegalArgumentException();
	      }
	}

	/**
	 * Returns the content of the text file placed at the path
	 * passed as the argument.
	 * @param filePath
	 * @return docContent
	 */
	public static String getFlashcardDocument(String filePath) {
		BufferedReader in;
		String docContent = "";
		
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line;
			
			while ((line = in.readLine()) != null) {
				docContent = docContent + line + "\n";
			}
			
			in.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return docContent;
	}
	
	/**
	 * Modifies the text file placed at the given path with
	 * the body passed as argument.
	 * @param filePath
	 * @param body
	 */
	public static void setFlashcardDocument(String filePath, String body) {
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(filePath));
			out.println(body);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}






