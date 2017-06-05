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
		boolean result = "BetterPW123".equals(t.getPassword());
		
		assertEquals(expected, result);
		
	}
	
	@Test public void testGetName(){
		User t = new User("Cole", "Password");
		boolean expected = true; 
		boolean result = "Cole".equals(t.getName());
		
		assertEquals(expected, result);
		
	}
	
}
