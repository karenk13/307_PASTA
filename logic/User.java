package logic;

public class User {

	private String name;
	private String password;
	private AssignmentManager aM; 
	
	public User() {
		this("No", "password");
	}
	
	public User(String name, String password){
		this.name= name;
		this.password=password; 
		aM = new AssignmentManager(); 
	}
	
	protected String getName(){
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass)
	{
		password = pass;
	}
	
	public AssignmentManager getAM() {
		return aM; 
	}
	
}
