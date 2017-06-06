package logic;

import java.util.TimeZone;

//class to track settings chosen by user 

public class Settings {



	private String colors;
	private String language; 
	private TimeZone tz; 
	
	private static String defaultColors = "Blue";
	private static String defaultLang = "en";
	private static TimeZone defaultTZ = TimeZone.getTimeZone("America/Los_Angeles");


	
	public Settings(){
		this(defaultColors, defaultLang, defaultTZ);
	}
	
	public Settings(String colors, String language, TimeZone tz){
		this.colors=colors;
		this.language=language;
		this.tz = tz; 
	}
	
	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public TimeZone getTz() {
		return tz;
	}

	public void setTz(TimeZone tz) {
		this.tz = tz;
	}

	public static String getDefaultColors() {
		return defaultColors;
	}

	public static String getDefaultLang() {
		return defaultLang;
	}

	@Override 
	public String toString() {
		return "Colors: " + colors + " Lang: " + language; 
	}
	
	
	
}
