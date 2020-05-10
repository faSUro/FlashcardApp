package it.fasuro.flashcardApp.utilities;

public class OSPathMaker {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
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
	
	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
 
	private static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
 
	private static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

}
