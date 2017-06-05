package test;

// Devin Nicholson

import org.junit.Test;
import static org.junit.Assert.*;
import logic.*;

public class TestCourse {

	@Test public void testToString(){
		Course c = new Course("Falessi", "CPE307", "01");
		
		String expected = "Falessi CPE307-01";
		String result = c.toString();
		
		assertEquals(expected, result);
		
	}
	
	@Test public void testSetSection(){
		Course c = new Course("Falessi", "CPE307", "01");
		c.setSection("02");
		
		String expected = "Falessi CPE307-02";
		String result = c.toString();
		
		assertEquals(expected, result);
	}
	
}
