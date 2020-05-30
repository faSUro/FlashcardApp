package it.fasuro.gordonscards.utilities;

import java.io.File;

/**
 * Allows to obtain the proper separator (for paths) 
 * depending on the operative system.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class PathHandler {
	
	public static String getDeckFolder() {
		return "assets" + getSeparator() + "decks";
	}
	
	/**
	 * Returns a string containing the separator (for paths),  
	 * different for every OS.
	 * @return separator
	 * 
	 */
	public static String getSeparator() {
		String separator = "";
		
		if (OSDistinguisher.isWindows()) {
			separator = "\\";
		} else if (OSDistinguisher.isUnix()) {
			separator = "/";
		} else if (OSDistinguisher.isMac()) {
			separator = ":";
		} 
		
		return separator;
	}
	
	/**
	 * Check if a File is a valid folder path: returns true
	 * if it is valid, false otherwise.
	 * @param path
	 * 
	 */
	public static boolean isValidPath(File path) {
		if (!path.isDirectory()) {
			return false;
		}
		
		if (path.exists()) {
			return true;
		} else {
			return false;
		}		
	}

	public static String generateDeckPath(String deckName) {
		return getDeckFolder() + getSeparator() + deckName;
	}

}
