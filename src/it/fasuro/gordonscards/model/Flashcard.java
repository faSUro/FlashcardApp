package it.fasuro.gordonscards.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.fasuro.gordonscards.Difficulty;
import it.fasuro.gordonscards.utilities.IOTools;
import it.fasuro.gordonscards.utilities.PathHandler;

/**
 * Class that contains all of the flashcard data.
 * It allows to create flashcards and to modify them.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class Flashcard {
	
	private String filePath;
	private Date dateToRepeat;
	private String question = "";
	private String answer = "";
	
	/**
	 * Builds the flashcard file path starting from the deck path and the file
	 * name (the flashcard file already exists if this constructor is used).
	 * Then, it assigns the class variables relying on the file content.
	 * @param deckPath
	 * @param fileName
	 * @param flashcardDocument
	 * 
	 */
	public Flashcard(String deckPath, String fileName, String flashcardDocument) {
		filePath = deckPath + PathHandler.getSeparator() + fileName;
		
		BufferedReader reader = new BufferedReader(new StringReader(flashcardDocument));
		
		try {
			dateToRepeat = generateDate(reader.readLine());
			
			question = reader.readLine();
			
			String line;
			while ((line = reader.readLine()) != null) {
				answer = answer + line + "\n";
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a completely new flashcard (the file doesn't exists yet)
	 * with a random file name relying on the deck path and the body 
	 * (question + answer) and using the current date.
	 * @param deckPath
	 * @param body
	 * 
	 */
	public Flashcard(String deckPath, String body) {
		dateToRepeat = new Date();
		filePath = deckPath + PathHandler.getSeparator() + generateFileName(); 
		
		BufferedReader reader = new BufferedReader(new StringReader(body));
		
		try {			
			question = reader.readLine();
			
			String line;
			while ((line = reader.readLine()) != null) {
				answer += line + "\n";
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates fileName built from a default string plus
	 * the current date.
	 * @return fileName
	 * 
	 */
	public String generateFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String date = sdf.format(dateToRepeat);
		
		String fileName = "AUTO_GENERATED_" + date + ".txt";
		return fileName; 
	}

	/**
	 * Returns a yyyy/MM/dd format Date based on a String
	 * with the same format.
	 * @param date String
	 * @return date Date
	 * 
	 */
	public Date generateDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		try{
			   c.setTime(sdf.parse(date));
			}catch(ParseException e){
				e.printStackTrace();
			 }	
		
		return c.getTime();
	}

	/**
	 * Modifies the date starting from the difficulty:
	 * +3 days for the EASY case, +1 for the MEDIUM one.
	 * The sum is calculated from today.
	 * Reprints the flashcard.
	 * @param difficulty
	 * 
	 */
	public void modifyDate(Difficulty difficulty) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		switch (difficulty) {
		case EASY:
			c.add(Calendar.DATE, 3);
			break;
		case MEDIUM:
			c.add(Calendar.DATE, 1);
			break;
		}
		
		dateToRepeat = c.getTime();
		printFlashcard();
	}
	
	/**
	 * Invokes the setFlashcardDocument method to
	 * save the date changes or the brand new flashcard.
	 * 
	 */
	public void printFlashcard() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String buffBody = "";
		
		buffBody += sdf.format(dateToRepeat) + "\n";
		buffBody += question + "\n";
		buffBody += answer;
		
		IOTools.setFlashcardDocument(filePath, buffBody);		
	}

	public String getFilePath() {
		return filePath;
	}

	public Date getDateToRepeat() {
		return dateToRepeat;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public void resetDate() {
		dateToRepeat = new Date();
		printFlashcard();		
	}

}
