package test;

// Karen Kauffman 

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

import logic.*;

public class TestAssignmentManager {
	
	@Test public void testNumAssignments(){
		AssignmentManager aM = new AssignmentManager();
		aM.addAssignment("Test1", "Desc", "6/7/2017", 10);
		aM.addAssignment("Test2", "Desc", "6/7/2017", 10);
		aM.addAssignment("Test3", "Desc", "6/7/2017", 10);

		int expected = 3;
		int result = aM.numAssignments();
		
		assertEquals(expected, result);
		
		
	}
	
	@Test public void testDeleteAssignment(){
		AssignmentManager aM = new AssignmentManager();
		Assignment a = new Assignment("Test1", "Desc", LocalDate.now(), 10);
		aM.addAssignment(a);
		aM.addAssignment("Test2", "Desc", "6/7/2017", 10);
		aM.addAssignment("Test3", "Desc", "6/7/2017", 10);
		
		int expected = 2; 
		aM.deleteAssignment(a);
		int result = aM.numAssignments();
		
		assertEquals(expected, result);

	}

}
