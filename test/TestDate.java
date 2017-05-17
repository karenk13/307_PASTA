package test;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.*;

public class TestDate {
	@Test public void testGetYear(){
		Date d = new Date(2017,5,30,2,0,0); 
		int expected = 2017;
		int result = d.getYear();
		assertEquals(expected,result);
	}
	@Test public void testGetMonth(){
		Date d = new Date(2017,5,30,2,0,0); 
		int expected = 5;
		int result = d.getMonth();
		assertEquals(expected,result);
	}
	
	
}
