package logic;

public class Course {

	private String professor; 
	private String dept; 
	private String section; 
	
	public Course(){
		this("no", "uhoh", "nope");
	}
	

	public Course(String p, String d, String s){
		professor=p;
		dept=d;
		section=s;
	}
	
	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public String toString(){
		return professor + " " + dept + "-" + section;
	}
	
	public String getClassInfo(){
		return toString();
	}
	

	
	
}
