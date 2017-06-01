package logic;

public class User {

	private String name;
	private String password;
	
	public User() {
		this("No", "password");
	}
	
	public User(String name, String password){
		this.name= name;
		this.password=password; 
	}
	
	protected String getName(){
		return name;
	}
	
	private String getPassword() {
		return password;
	}
	
}
