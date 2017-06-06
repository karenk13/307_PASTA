package test;

// Cole Grigsby 


import static org.junit.Assert.assertEquals;

import java.util.TimeZone;

import org.junit.Test;
import logic.Settings; 

public class TestSettings {

	
	@Test public void testDefaultConstructorValues(){
		Settings s = new Settings();
		String cExpected = "Blue";
		String cActual = s.getColors();
		
		assertEquals(cExpected, cActual);
		
		String lExpected = "en";
		String lActual = s.getLanguage();
		
		assertEquals(lExpected, lActual);
		
		TimeZone tzExpected = TimeZone.getTimeZone("America/Los_Angeles");
		TimeZone tzActual = s.getTz(); 
		
		assertEquals(tzExpected, tzActual);
		
	}
	
	@Test public void testConstructor() {
		Settings s = new Settings("Green", "fr", TimeZone.getTimeZone("America/New_York"));
		String cExpected = "Green";
		String cActual = s.getColors();
		
		assertEquals(cExpected, cActual);
		
		String lExpected = "fr";
		String lActual = s.getLanguage();
		
		assertEquals(lExpected, lActual);
		
		TimeZone tzExpected = TimeZone.getTimeZone("America/New_York");
		TimeZone tzActual = s.getTz(); 
		
		assertEquals(tzExpected, tzActual);
	}
	
	
}
