package logic;

import java.util.TimeZone;

public class User {

	private String name;
	private String password;
	private Settings settings;
	private AssignmentManager aM; 
	private ScratchPadManager sM;
	
	
	public User() {
		this("No", "password");
	}
	
	public User(String name, String password){
		this.name= name;
		this.password=password; 
		settings = new Settings();
		aM = new AssignmentManager(); 
		sM = new ScratchPadManager();
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public String getColorScheme() {
		return settings.getColors();
	}
	
	public String getLanguage() {
		return settings.getLanguage();
	}
	
	public TimeZone getTimeZone() {
		return settings.getTz();
	}
	
	public void setPassword(String pass)
	{
		password = pass;
	}
	
	public AssignmentManager getAM() {
		return aM; 
	}
	
	public ScratchPadManager getSM() {
		return sM;
	}
	
}
