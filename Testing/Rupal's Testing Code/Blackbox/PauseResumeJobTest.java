package test.blackbox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory6_PauseResumeJobTest {

    @Test
    void testPauseAndResumeActiveJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an active job
        Job job = new Job("Database Backup");

        // Pause the job
        boolean pauseResult = job.pauseJob();

        // Resume the job
        boolean resumeResult = job.resumeJob();

        // Restore console output
        System.setOut(System.out);

        // Assert that pausing was successful
        assertTrue(pauseResult, "The job should be paused successfully.");
        assertTrue(resumeResult, "The job should be resumed successfully.");

        // Check console output
        String output = outputStream.toString();
        assertTrue(output.contains("Job 'Database Backup' is now paused."),
                "Pause message should be printed.");
        assertTrue(output.contains("Job 'Database Backup' has been resumed."),
                "Resume message should be printed.");
    }

    @Test
    void testPauseCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Server Maintenance");
        job.markAsCompleted();

        // Attempt to pause the completed job
        boolean pauseResult = job.pauseJob();

        // Restore console output
        System.setOut(System.out);

        // Assert that pausing a completed job fails
        assertFalse(pauseResult, "Pausing a completed job should fail.");

        // Check console output
        assertTrue(outputStream.toString().contains("Cannot pause a completed job: 'Server Maintenance'."),
                "Error message should be printed for pausing a completed job.");
    }

    @Test
    void testResumeCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("System Update");
        job.markAsCompleted();

        // Attempt to resume the completed job
        boolean resumeResult = job.resumeJob();

        // Restore console output
        System.setOut(System.out);

        // Assert that resuming a completed job fails
        assertFalse(resumeResult, "Resuming a completed job should fail.");

        // Check console output
        assertTrue(outputStream.toString().contains("Cannot resume a completed job: 'System Update'."),
                "Error message should be printed for resuming a completed job.");
    }
}
