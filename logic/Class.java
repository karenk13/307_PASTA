package logic;

public class Class {

	private String professor; 
	private String dept; 
	private String section; 
	
	public Class(){
		this("no", "uhoh", "nope");
	}
	
	public Class(String p, String d, String s){
		professor=p;
		dept=d;
		section=s;
	}
	
	public String getClassInfo(){
		return professor + " " + dept + "-" + section;
	}
	
	
}
