package it.fasuro.flashcardApp.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Allows to read and write on txt documents (flashcards).
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class IOHandler {

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
