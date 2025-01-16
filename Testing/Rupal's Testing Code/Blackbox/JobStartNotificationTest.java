package test.blackbox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory7_JobStartNotificationTest {

    @Test
    void testStartActiveJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an active job
        Job job = new Job("Database Migration");

        // Start the active job
        job.startJob();

        // Restore console output
        System.setOut(System.out);

        // Verify the notification message
        String expectedMessage = "Notification: Job 'Database Migration' has started.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The start notification should be printed for an active job.");
    }

    @Test
    void testStartPausedJob() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and pause a job
        Job job = new Job("Server Backup");
        job.pauseJob();

        // Attempt to start the paused job
        job.startJob();

        // Restore console output
        System.setOut(System.out);

        // Verify the paused job error message
        String expectedMessage = "Cannot start a paused job: 'Server Backup'. Please resume it first.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The correct error message should be shown for starting a paused job.");
    }

    @Test
    void testStartCompletedJob() {
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

        // Verify the completed job error message
        String expectedMessage = "Cannot start a completed job: 'Software Deployment'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The correct error message should be shown for starting a completed job.");
    }
}
