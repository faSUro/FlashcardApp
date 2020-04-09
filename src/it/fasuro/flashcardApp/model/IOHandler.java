package it.fasuro.flashcardApp.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOHandler {
	
	public static boolean firstBoot() {
		BufferedReader in;
		String firstBoot = "";
		
		try {
			in = new BufferedReader(new FileReader("assets/first_boot.txt"));
			
			firstBoot += in.readLine();
			
			in.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Boolean.parseBoolean(firstBoot);
	}
	
	public static String getDeckPath() {
		BufferedReader in;
		String deckPath = "";
		
		try {
			in = new BufferedReader(new FileReader("assets/deck_path.txt"));
			
			deckPath += in.readLine();
			
			in.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return deckPath;
	}
	
	public static void setDeckPath(String deckPath) {
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter("assets/deck_path.txt"));
			
			out.println(deckPath);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static String getFlashcardDocument(String filePath) {
		BufferedReader in;
		String input = "";
		
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line;
			
			while ((line = in.readLine()) != null) {
				input = input + line + "\n";
			}
			
			in.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	
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

	public static void firstBootDone() {
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter("assets/first_boot.txt"));
			
			out.println("false");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
