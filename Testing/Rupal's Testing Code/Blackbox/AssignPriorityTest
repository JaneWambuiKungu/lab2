package test.blackbox;

import jobscheduler.Job; // Import your Job class
import org.junit.jupiter.api.Test; // Import JUnit annotations
import static org.junit.jupiter.api.Assertions.*; // Import assertions

class UserStory1_AssignPriorityTest {

	@Test
	void testSetPriority() {
	    Job job = new Job("Assign Priority Test");

	    job.setPriority("High");
	    assertEquals("High", job.getPriority(), "Job priority should be set to High");

	    job.setPriority("Medium");
	    assertEquals("Medium", job.getPriority(), "Job priority should be set to Medium");

	    job.setPriority("Low");
	    assertEquals("Low", job.getPriority(), "Job priority should be set to Low");

	    // Invalid priority
	    job.setPriority("Urgent");
	    assertNotEquals("Urgent", job.getPriority(), "Invalid priority should not be accepted");
	}
}
