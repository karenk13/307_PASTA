package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.Assignment;
import logic.AssignmentManager;

public class TestAssignmentManagerLoops {
	@Test public void testGetAssignmentsOnDateLoopNone(){
		ObservableList<Assignment> a = FXCollections.observableArrayList(); 
		LocalDate n = LocalDate.now();
		
		ObservableList<Assignment> results = AssignmentManager.getAssignmentsOnDate(a, n);
				
		boolean expected = true;
		boolean result = results.isEmpty(); 
		
		assertEquals(expected, result);
		
	}
	
	@Test public void testGetAssignmentsOnDateLoopOne(){
		ObservableList<Assignment> a = FXCollections.observableArrayList(); 
		LocalDate n = LocalDate.now();
		a.add(new Assignment("Cole", "Desc", n, 1));
		
		ObservableList<Assignment> results = AssignmentManager.getAssignmentsOnDate(a, n);
				
		int expected = 1;
		int result = results.size(); 
		
		assertEquals(expected, result);
		
	}
	
	@Test public void testGetAssignmentsOnDateLoopTwo(){
		ObservableList<Assignment> a = FXCollections.observableArrayList(); 
		LocalDate n = LocalDate.now();
		a.add(new Assignment("Jon", "d", n, 2));
		a.add(new Assignment("Karen", "more", n, 4));

		ObservableList<Assignment> results = AssignmentManager.getAssignmentsOnDate(a, n);
				
		int expected = 2;
		int result = results.size(); 
		
		assertEquals(expected, result);
		
	}
	
	//Average Case
	@Test public void testGetAssignmentsOnDateLoopFive(){
		ObservableList<Assignment> a = FXCollections.observableArrayList(); 
		LocalDate n = LocalDate.now();
		a.add(new Assignment("J", "Z", n, 2));
		a.add(new Assignment("K", "Y", n, 4));		
		a.add(new Assignment("C", "X", n, 10));
		a.add(new Assignment("D", "W", n, 5));
		a.add(new Assignment("N", "V", n, 6));
		
		ObservableList<Assignment> results = AssignmentManager.getAssignmentsOnDate(a, n);
				
		int expected = 5;
		int result = results.size(); 
		
		assertEquals(expected, result);
		
	}
	
	// No max case for the loop 
	
	
	
	@Test public void testSearchAssignmentsLoopNone(){
		AssignmentManager a = new AssignmentManager();
		
		ObservableList<Assignment> results = a.searchAssignments("Name");
		int expected = 0;
		int result = results.size();
		
		assertEquals(expected, result);
		
	}
	
	
	@Test public void testSearchAssignmentsLoopOne(){
		AssignmentManager a = new AssignmentManager();
		a.addAssignment("TestName", "tDesc", "06/12/2017", 1);
		
		ObservableList<Assignment> results = a.searchAssignments("TestName");
		int expected = 1;
		int result = results.size();
		
		assertEquals(expected, result);
		
	}
	
	@Test public void testSearchAssignmentsLoopTwo(){
		AssignmentManager a = new AssignmentManager();
		String name = "YAY";
		a.addAssignment(name, "tdesc againg ", "06/15/2017", 5);
		a.addAssignment(name, "asdfasdfasd", "06/19/2017", 4);

		
		ObservableList<Assignment> results = a.searchAssignments(name);
		int expected = 2;
		int result = results.size();
		
		assertEquals(expected, result);
		
	}
	
	//Average use case 
	
	@Test public void testSearchAssignmentsLoopFive(){
		AssignmentManager a = new AssignmentManager();
		String name = "Fiver";
		a.addAssignment(name, "tdesc againg ", "06/20/2017", 5);
		a.addAssignment(name, "asdfasdfasd", "06/25/2017", 4);
		a.addAssignment(name, "testing 123", "06/22/2017", 2);
		a.addAssignment(name, "qwert", "06/30/2017", 10);
		a.addAssignment(name, "poiut", "07/21/2017", 9);

		
		ObservableList<Assignment> results = a.searchAssignments(name);
		int expected = 5;
		int result = results.size();
		
		assertEquals(expected, result);
		
	}
	
	// NO max value for test 
}
