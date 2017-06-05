package test;

// Jon Scott

import org.junit.Test;
import static org.junit.Assert.*;
import logic.*;

public class TestUser {

	@Test public void testSetPassword(){
		User t = new User("Cole", "Password");
		t.setPassword("BetterPW123");
		boolean expected = true; 
		boolean result = t.getPassword().equals("BetterPW123");
		
		assertEquals(expected, result);
		
	}
	
	@Test public void testGetName(){
		User t = new User("Cole", "Password");
		boolean expected = true; 
		boolean result = t.getName().equals("Cole");
		
		assertEquals(expected, result);
		
	}
	
}
