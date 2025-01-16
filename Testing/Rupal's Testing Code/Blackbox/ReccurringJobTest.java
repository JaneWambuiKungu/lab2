package test.blackbox;

// Import your Job class
import jobscheduler.Job; 

// Import JUnit annotations and assertions
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStory2_RecurringJobTest {
	@Test
	void testSetRecurrence() {
	    Job job = new Job("Recurring Job Test");

	    // Valid recurrence values
	    job.setRecurrence("Daily");
	    assertEquals("Daily", job.getRecurrence(), "Recurrence should be Daily");

	    job.setRecurrence("Weekly");
	    assertEquals("Weekly", job.getRecurrence(), "Recurrence should be Weekly");

	    job.setRecurrence("Monthly");
	    assertEquals("Monthly", job.getRecurrence(), "Recurrence should be Monthly");

	    job.setRecurrence("None");
	    assertEquals("None", job.getRecurrence(), "Recurrence should be None");

	    // Invalid recurrence value
	    job.setRecurrence("Yearly");
	    assertNotEquals("Yearly", job.getRecurrence(), "Invalid recurrence should not be accepted");

	    // Ensure the previous value did not change
	    assertEquals("None", job.getRecurrence(), "Recurrence should remain 'None' after invalid input");
	}
}
