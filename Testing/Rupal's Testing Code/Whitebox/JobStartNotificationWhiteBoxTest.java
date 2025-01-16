package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserStory7_JobStartNotificationWhiteBoxTest {

    @Test
    void testStartActiveJobNotification() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create and start an active job
        Job job = new Job("Database Migration");
        job.startJob();

        // Restore console output
        System.setOut(System.out);

        // Verify notification was printed
        String expectedMessage = "Notification: Job 'Database Migration' has started.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "The start notification should be printed for an active job.");
    }

    @Test
    void testStartPausedJobNotification() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a job and pause it
        Job job = new Job("Security Scan");
        job.pauseJob();

        // Attempt to start the paused job
        job.startJob();

        // Restore console output
        System.setOut(System.out);

        // Verify the job cannot be started while paused
        String expectedMessage = "Cannot start a paused job: 'Security Scan'. Please resume it first.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "A paused job should not start and must show a warning.");
    }

    @Test
    void testStartCompletedJobNotification() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a job and mark it as completed
        Job job = new Job("Log Archiving");
        job.markAsCompleted();

        // Attempt to start the completed job
        job.startJob();

        // Restore console output
        System.setOut(System.out);

        // Verify the job cannot be started after completion
        String expectedMessage = "Cannot start a completed job: 'Log Archiving'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "A completed job should not start and must show an error.");
    }
}
