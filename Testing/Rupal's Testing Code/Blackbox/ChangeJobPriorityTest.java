package test.blackbox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory10_ChangeJobPriorityTest {

    @Test
    void testChangePriorityOfActiveJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an active job
        Job job = new Job("UI Redesign");

        // Change priority to High
        job.setPriority("High");

        // Restore console output
        System.setOut(System.out);

        // Verify priority change
        assertEquals("High", job.getPriority(), "Priority should be set to High.");
        assertTrue(outputStream.toString().contains("Priority set to High for job: UI Redesign"),
                "Correct success message should be printed.");
    }

    @Test
    void testChangePriorityOfPausedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and pause a job
        Job job = new Job("Backend Refactor");
        job.pauseJob();

        // Change priority to Low
        job.setPriority("Low");

        // Restore console output
        System.setOut(System.out);

        // Verify priority change
        assertEquals("Low", job.getPriority(), "Priority should be set to Low.");
        assertTrue(outputStream.toString().contains("Priority set to Low for job: Backend Refactor"),
                "Correct success message should be printed.");
    }

    @Test
    void testChangePriorityOfCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Database Migration");
        job.markAsCompleted();

        // Attempt to change priority
        job.setPriority("High");

        // Restore console output
        System.setOut(System.out);

        // Verify priority did not change
        assertNotEquals("High", job.getPriority(), "Priority should not change for a completed job.");
        assertTrue(outputStream.toString().contains("Cannot change priority of a completed job: 'Database Migration'."),
                "Correct error message should be printed.");
    }

    @Test
    void testSetInvalidPriority() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a job
        Job job = new Job("System Monitoring");

        // Attempt to set invalid priority
        job.setPriority("Urgent");

        // Restore console output
        System.setOut(System.out);

        // Verify priority did not change
        assertNotEquals("Urgent", job.getPriority(), "Invalid priority should not be accepted.");
        assertTrue(outputStream.toString().contains("Invalid priority! Please choose High, Medium, or Low."),
                "Correct error message should be printed for invalid priority.");
    }
    
    

}
