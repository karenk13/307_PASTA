package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
* Cole Grigsby 
*/ 
public class AssignmentManager {

	
	
	private ObservableList<Assignment> assignments; 
	
	public AssignmentManager(){
		assignments = FXCollections.observableArrayList();
	}
	
	protected ObservableList<Assignment> getAssignments() {
		return assignments; 
	}
	
	protected ObservableList<Assignment> addAssignment(String name, String description, String due, 
			double priority){
		//TODO add params and create new assignment and add to assignments 
		assignments.add(new Assignment(name, description, LocalDate.parse(due,
				DateTimeFormatter.ofPattern("MM/dd/yyyy")) , priority));
		return assignments;
	}
	
	protected static ObservableList<Assignment> getAssignmentsOnDate(ObservableList<Assignment> ass, LocalDate date){
		ObservableList<Assignment> a = FXCollections.observableArrayList(); 
		
		for (Assignment i: ass){
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

