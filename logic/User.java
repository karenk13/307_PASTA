package logic;

public class User {

	private String name;
	private String password;
	private AssignmentManager AM; 
	
	public User() {
		this("No", "password");
	}
	
	public User(String name, String password){
		this.name= name;
		this.password=password; 
		AM = new AssignmentManager(); 
	}
	
	protected String getName(){
		return name;
	}
	
	private String getPassword() {
		return password;
	}
	
	public AssignmentManager getAM() {
		return AM; 
	}
	
}
