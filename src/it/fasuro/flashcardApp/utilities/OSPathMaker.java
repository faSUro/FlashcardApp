package it.fasuro.flashcardApp.utilities;

/**
 * Allows to obtain the proper separator (for paths) 
 * depending on the operative system.
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class OSPathMaker {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Returns a string containing the separator (for paths),  
	 * different for every OS.
	 * @return separator
	 */
	public static String getSeparator() {
		String separator = "";
		
		if (isWindows()) {
			separator = "\\";
		} else if (isUnix()) {
			separator = "/";
		} else if (isMac()) {
			separator = ":";
		} else {
			separator = null;
		}
		
		return separator;
	}
	
	/**
	 * Returns true if the current OS is Windows.
	 * @return boolean
	 */
	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
 
	/**
	 * Returns true if the current OS is a Mac OS.
	 * @return boolean
	 */
	private static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
 
	/**
	 * Returns true if the current OS is Unix based.
	 * @return boolean
	 */
	private static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

}
