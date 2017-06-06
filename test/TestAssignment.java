package test;



import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import logic.Assignment;

public class TestAssignment {

	@Test public void testEquals(){
		Assignment a = new Assignment("Cole", "Doesn't matter", LocalDate.now(), 1);
		Assignment b = new Assignment("Cole", "uh oh", LocalDate.now(), 1);
		boolean expected = true; 
		boolean result = a.equals(b); 
		assertEquals(expected, result);
		
	}
	@Test public void testEqualsFalse(){
		Assignment a = new Assignment("Not cole", "nope", LocalDate.now(), 1);
		Assignment b = new Assignment("Cole", "woah", LocalDate.now(), 1);
		boolean expected = false; 
		boolean result = a.equals(b); 
		assertEquals(expected, result);
		
	}
}
