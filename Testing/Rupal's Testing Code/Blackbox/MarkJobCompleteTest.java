package test.blackbox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory8_MarkJobCompletedTest {

    @Test
    void testMarkJobAsCompleted() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an active job
        Job job = new Job("Database Cleanup");

        // Mark the job as completed
        job.markAsCompleted();

        // Restore console output
        System.setOut(System.out);

        // Verify the completion message
        String expectedMessage = "Job 'Database Cleanup' has been marked as completed.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The job should be marked as completed.");
    }

    @Test
    void testCannotPauseCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Server Upgrade");
        job.markAsCompleted();

        // Attempt to pause the completed job
        job.pauseJob();

        // Restore console output
        System.setOut(System.out);

        // Verify error message
        String expectedMessage = "Cannot pause a completed job: 'Server Upgrade'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "Paused action should be blocked for completed jobs.");
    }

    @Test
    void testCannotResumeCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Data Backup");
        job.markAsCompleted();

        // Attempt to resume the completed job
        job.resumeJob();

        // Restore console output
        System.setOut(System.out);

        // Verify error message
        String expectedMessage = "Cannot resume a completed job: 'Data Backup'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "Resuming should be blocked for completed jobs.");
    }

    @Test
    void testCannotStartCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Software Deployment");
        job.markAsCompleted();

        // Attempt to start the completed job
        job.startJob();

        // Restore console output
        System.setOut(System.out);

        // Verify error message
        String expectedMessage = "Cannot start a completed job: 'Software Deployment'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "Starting should be blocked for completed jobs.");
    }

    @Test
    void testCannotReassignCompletedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and complete a job
        Job job = new Job("Network Configuration");
        job.markAsCompleted();

        // Attempt to reassign the completed job
        job.assignTo("New Worker");

        // Restore console output
        System.setOut(System.out);

        // Verify error message
        String expectedMessage = "Cannot reassign a completed job: 'Network Configuration'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "Reassigning should be blocked for completed jobs.");
    }
}
