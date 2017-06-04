package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
* Cole Grigsby 
*/ 
public class AssignmentManager {
	
	private ObservableList<Assignment> assignments; 
	private ObservableList<Assignment> completeAssignments;
	
	public AssignmentManager(){
		assignments = FXCollections.observableArrayList();
		completeAssignments = FXCollections.observableArrayList();
	}
	
	protected ObservableList<Assignment> getAssignments() {
		return assignments; 
	}
	
	protected ObservableList<Assignment> getComplete() {
		return completeAssignments; 
	}
	
	public void deleteAssignment(Assignment a)
	{
		assignments.remove(a);
	}
	
	public int numAssignments()
	{
		return assignments.size();
	}
	public int completedAssignments()
	{
		return completeAssignments.size();
	}
	
	public Assignment getAssignment(int index)
	{
		return assignments.get(index);
	}
	
	public void markComplete(Assignment a)
	{
	   completeAssignments.add(a);
	   assignments.remove(a);
	}
	
	protected ObservableList<Assignment> addAssignment(String name, String description, String due, 
			double priority){
		assignments.add(new Assignment(name, description, LocalDate.parse(due,
				DateTimeFormatter.ofPattern("MM/dd/yyyy")) , priority));
		return assignments;
	}
	
	protected static ObservableList<Assignment> getAssignmentsOnDate(ObservableList<Assignment> l, 
			LocalDate date){
		ObservableList<Assignment> a = FXCollections.observableArrayList(); 
		
		//TODO 
		for (Assignment i: l){
			if (i.dueDate().equals(date)){
				a.add(i);
			}
		}
		
		return a;
		
	}
	
	public void getAssignmentsPriority()
	{
		Collections.sort(assignments);	
	}
	
	
}

