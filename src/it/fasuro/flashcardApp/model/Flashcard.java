package it.fasuro.flashcardApp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.fasuro.flashcardApp.Difficulty;
import it.fasuro.flashcardApp.IOHandler;

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
	 */
	public Flashcard(String deckPath, String fileName, String flashcardDocument) {
		filePath = deckPath + "/" + fileName;
		
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
	 * (question + answer) and using the today date.
	 * @param deckPath
	 * @param body
	 */
	public Flashcard(String deckPath, String body) {
		filePath = deckPath + "/" + generateFileName(); //if working on windows: / must be replaced with \\
		dateToRepeat = new Date();
		
		BufferedReader reader = new BufferedReader(new StringReader(body));
		
		try {			
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
	 * Generates fileName built from a default string plus
	 * a random alphanumeric string of 10 characters.
	 * It does not guarantee that the file name doesn't
	 * exists already (in that case it would be overwritten).
	 * Even though the probability is low, it's highly recommended
	 * to manually change the name after the creation,
	 * @return fileName
	 */
	public String generateFileName() { //to be corrected
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int count = 10;
		
		StringBuilder builder = new StringBuilder();
		while(count-- != 0) {
			int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		
		String fileName = "AUTO_GENERATED" + builder.toString() + ".txt";
		return fileName;
	}

	/**
	 * Returns a yyyy/MM/dd format Date based on a String
	 * with the same format.
	 * @param date
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
		reprintFlashcard();
	}
	
	/**
	 * Invokes the setFlashcardDocument method to
	 * save the date changes.
	 */
	public void reprintFlashcard() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String buffBody = "";
		
		buffBody += sdf.format(dateToRepeat) + "\n";
		buffBody += question + "\n";
		buffBody += answer;
		
		IOHandler.setFlashcardDocument(filePath, buffBody);		
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

}
