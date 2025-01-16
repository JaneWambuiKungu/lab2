package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserStory10_ChangeJobPriorityWhiteBoxTest {

    @Test
    void testChangePriorityOfActiveJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an active job
        Job job = new Job("Backend Development");

        // Change priority
        job.setPriority("High");

        // Restore console output
        System.setOut(System.out);

        // Verify priority change
        assertEquals("High", job.getPriority(), "Priority should be set to High.");
        assertTrue(outputStream.toString().contains("Priority set to High for job: Backend Development"),
                "Correct success message should be printed.");
    }

    @Test
    void testChangePriorityOfPausedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and pause a job
        Job job = new Job("UI Design");
        job.pauseJob();

        // Attempt to change priority
        job.setPriority("Medium");

        // Restore console output
        System.setOut(System.out);

        // Verify priority change is allowed
        assertEquals("Medium", job.getPriority(), "Priority should be set to Medium for a paused job.");
        assertTrue(outputStream.toString().contains("Priority set to Medium for job: UI Design"),
                "Correct success message should be printed.");
    }

    @Test
    void testChangePriorityOfCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Security Audit");
        job.markAsCompleted();

        // Attempt to change priority
        job.setPriority("Low");

        // Restore console output
        System.setOut(System.out);

        // Verify priority change is blocked
        assertNotEquals("Low", job.getPriority(), "Completed job's priority should not change.");
        assertTrue(outputStream.toString().contains("Cannot change priority of a completed job"),
                "Error message should indicate priority change is not allowed.");
    }
}
