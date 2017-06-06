package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({TestAssignment.class, TestUser.class, TestAssignmentManager.class,
	TestSettings.class, TestScratchPadManager.class, TestCourse.class})

public class TestSuite {
	
}
