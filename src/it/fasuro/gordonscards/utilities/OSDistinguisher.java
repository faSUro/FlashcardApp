package it.fasuro.gordonscards.utilities;

public class OSDistinguisher {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Returns true if the current OS is Windows.
	 * @return boolean
	 * 
	 */
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
 
	/**
	 * Returns true if the current OS is a Mac OS.
	 * @return boolean
	 * 
	 */
	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
 
	/**
	 * Returns true if the current OS is Unix based.
	 * @return boolean
	 * 
	 */
	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

}
