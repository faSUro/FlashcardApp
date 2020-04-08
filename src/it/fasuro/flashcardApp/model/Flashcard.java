package it.fasuro.flashcardApp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

public class Flashcard {
	
	private String fileName;
	private Date dateToRepeat;
	private String question = "";
	private String answer = "";
	
	public Flashcard(String fileName, String flashcardDocument) {
		this.fileName = fileName;
		
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

	@SuppressWarnings({ "deprecation" })
	public Date generateDate(String date) {
		String[] buff = date.split(" ");
		Integer[] intBuff = {0, 0, 0};
		
		intBuff[0] = Integer.parseInt(buff[0]) - 1900;
		intBuff[1] = Integer.parseInt(buff[1]) - 1;
		intBuff[2] = Integer.parseInt(buff[2]);
		
		return new Date(intBuff[0], intBuff[1], intBuff[2]);
	}

	public String getFileName() {
		return fileName;
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
