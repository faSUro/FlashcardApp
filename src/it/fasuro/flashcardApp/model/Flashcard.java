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
	
	public Flashcard(String deckPath, String body) {
		filePath = deckPath + "/" + generateFileName();
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

	public String generateFileName() {
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

	public void modifyDate(Difficulty d) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		switch (d) {
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
