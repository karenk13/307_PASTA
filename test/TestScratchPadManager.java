package test;

// Olivia Hillman

import org.junit.Test;
import static org.junit.Assert.*;
import logic.*;

public class TestScratchPadManager {

	@Test public void testNumNotesZero(){
		ScratchPadManager s = new ScratchPadManager();
		
		int expected = 0;
		int actual = s.numNotes();
		
		assertEquals(expected, actual);
		
	}
	
	@Test public void testNumNotesOne(){
		ScratchPadManager s = new ScratchPadManager();
		
		s.addNote("Remember to work on it later");
		
		int expected = 1;
		int actual = s.numNotes();
		
		assertEquals(expected, actual);
		
	}

	
}

