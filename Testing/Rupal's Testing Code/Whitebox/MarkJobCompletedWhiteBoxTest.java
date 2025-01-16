package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserStory8_MarkJobCompletedWhiteBoxTest {

    @Test
    void testMarkJobAsCompleted() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Server Backup");
        job.markAsCompleted();

        // Restore console output
        System.setOut(System.out);

        // Verify the job was marked as completed
        String expectedMessage = "Job 'Server Backup' has been marked as completed.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The job should be marked as completed.");
        assertTrue(job.isCompleted(), "The job status should be marked as completed.");
    }

    @Test
    void testCannotPauseCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("System Audit");
        job.markAsCompleted();

        // Attempt to pause the completed job
        boolean result = job.pauseJob();

        // Restore console output
        System.setOut(System.out);

        // Verify that the job cannot be paused
        String expectedMessage = "Cannot pause a completed job: 'System Audit'.";
        assertFalse(result, "A completed job should not be paused.");
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The correct warning should be printed when pausing a completed job.");
    }

    @Test
    void testCannotReassignCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Data Analysis");
        job.markAsCompleted();

        // Attempt to reassign the completed job
        job.assignTo("John Doe");

        // Restore console output
        System.setOut(System.out);

        // Verify reassignment is blocked
        String expectedMessage = "Cannot reassign a completed job: 'Data Analysis'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The job should not be reassigned once marked as completed.");
        assertNull(job.getAssignedTo(), "The completed job should not have a new assignment.");
    }
}
