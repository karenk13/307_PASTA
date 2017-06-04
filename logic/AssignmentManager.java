package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* Cole Grigsby 
*/ 
public class AssignmentManager {

	
	
	private ArrayList<Assignment> assignments; 
	
	public AssignmentManager(){
		assignments = new ArrayList<Assignment>();
	}
	
	protected ArrayList<Assignment> getAssignments() {
		return assignments; 
	}
	
	protected ArrayList<Assignment> addAssignment(String name, String description, String due, 
			double priority){
		//TODO add params and create new assignment and add to assignments 
		assignments.add(new Assignment(name, description, LocalDate.parse(due,
				DateTimeFormatter.ofPattern("MM/dd/yyyy")) , priority));
		return assignments;
	}
	
	protected ArrayList<Assignment> getAssignmentsOnDate(LocalDate date){
		ArrayList<Assignment> a = new ArrayList<Assignment>(); 
		
		//TODO 
		for (Assignment i: assignments){
			if (i.dueDate().equals(date)){
				a.add(i);
			}
		}
		
		return a;
		
	}
	
	public ArrayList<Assignment> getAssignmentsPriority(LocalDate date)
	{
		ArrayList<Assignment> a = new ArrayList<Assignment>();
		
		for(Assignment i: assignments)
		{
			a.add(i);
		}
		Collections.sort(a);
		return a;
	}
	
	
}

