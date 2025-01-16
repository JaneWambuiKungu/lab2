package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStory6_PauseResumeJobsWhiteBoxTest {

    @Test
    void testPauseAndResumeActiveJob() {
        // Create an active job
        Job job = new Job("Data Backup");

        // Pause the job
        boolean pauseResult = job.pauseJob();

        // Resume the job
        boolean resumeResult = job.resumeJob();

        // Verify pausing and resuming was successful
        assertTrue(pauseResult, "The job should be paused successfully.");
        assertTrue(resumeResult, "The job should be resumed successfully.");
    }

    @Test
    void testCannotPauseCompletedJob() {
        // Create and complete a job
        Job job = new Job("Security Audit");
        job.markAsCompleted();

        // Attempt to pause the completed job
        boolean pauseResult = job.pauseJob();

        // Verify that the pause was unsuccessful
        assertFalse(pauseResult, "A completed job should not be paused.");
    }

    @Test
    void testCannotResumeCompletedJob() {
        // Create and complete a job
        Job job = new Job("Server Maintenance");
        job.markAsCompleted();

        // Attempt to resume the completed job
        boolean resumeResult = job.resumeJob();

        // Verify that the resume was unsuccessful
        assertFalse(resumeResult, "A completed job should not be resumed.");
    }
}
