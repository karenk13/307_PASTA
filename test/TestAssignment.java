package test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

import logic.*;

public class TestAssignment {
	/*@Test public void testIsCompleteFalse() {
		Assignment a = new Assignment(new Date(2017,5,30,2,0,0));
		boolean expected = false; 
		boolean result = a.isComplete();
		assertEquals(expected, result); 
	}
	@Test public void testMarkComplete() {
		Assignment a = new Assignment(new Date(2017,5,30,2,0,0));
		boolean expected = true; 
		a.markComplete();
		boolean result = a.isComplete();
		assertEquals(expected, result); 
	}*/
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
