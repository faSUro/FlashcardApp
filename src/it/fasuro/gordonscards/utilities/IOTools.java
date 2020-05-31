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

/**
 * Allows to read and write on txt documents (flashcards).
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class IOTools {
	
	public static ArrayList<String> getDeckList() {
		ArrayList<String> deckList = new ArrayList<String>();
		
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
	
	public static void createDeck(String deckName) throws IllegalArgumentException {
		File deckFolder = new File(PathHandler.generateDeckPath(deckName));

	      if (!deckFolder.mkdir()) {
	         throw new IllegalArgumentException();
	      }
	}
	
	public static void deleteDeck(String deckPath) throws IllegalArgumentException {
		File deckFolder = new File(deckPath);

		for (String flashcard : deckFolder.list()) { //deletes all flashcards before deleting the deck folder
			File flashcardFile = new File(deckFolder.getPath(), flashcard);
			flashcardFile.delete();
		}
		
	      if (!deckFolder.delete()) {
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






