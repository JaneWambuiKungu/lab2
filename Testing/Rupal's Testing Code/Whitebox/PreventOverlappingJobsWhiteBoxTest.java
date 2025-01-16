package test.whitebox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory5_PreventOverlappingJobsWhiteBoxTest {

    @Test
    void testPreventOverlappingJobsForSameWorker() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize the JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create the first job and assign it to John Doe
        Job job1 = new Job("Website Design");
        job1.assignTo("John Doe");

        // Create the second job and attempt to assign it to John Doe
        Job job2 = new Job("Backend Development");
        job2.assignTo("John Doe");  // This should be blocked

        // Add both jobs to the scheduler
        boolean result1 = scheduler.addJob(job1);  // Should succeed
        boolean result2 = scheduler.addJob(job2);  // Should fail

        // Restore console output
        System.setOut(System.out);

        // Capture and check the error message
        String expectedError = "Error: Worker/Resource 'John Doe' is already assigned to another job.";
        String actualOutput = outputStream.toString();

        // Assertions
        assertTrue(result1, "The first job should be added successfully.");
        assertFalse(result2, "The second job should be rejected due to worker conflict.");
        assertTrue(actualOutput.contains(expectedError), "The correct error message should be displayed.");
    }

    @Test
    void testAllowDifferentWorkersForJobs() {
        // Initialize the JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create jobs assigned to different workers
        Job job1 = new Job("Database Optimization");
        job1.assignTo("Alice");

        Job job2 = new Job("UI Redesign");
        job2.assignTo("Bob");

        // Add both jobs to the scheduler
        boolean result1 = scheduler.addJob(job1);  // Should succeed
        boolean result2 = scheduler.addJob(job2);  // Should also succeed

        // Assertions
        assertTrue(result1, "Job 1 should be successfully assigned to Alice.");
        assertTrue(result2, "Job 2 should be successfully assigned to Bob.");
    }
}
