package test.blackbox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory4_VisualizeScheduleTest {

    @Test
    void testVisualizeScheduleWithMultipleJobs() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create and add jobs
        Job job1 = new Job("Design Homepage");
        job1.setPriority("High");
        job1.setRecurrence("Weekly");
        job1.assignTo("John Doe");

        Job job2 = new Job("Backend Development");
        job2.setPriority("Medium");
        job2.setRecurrence("Daily");
        job2.assignTo("Jane Smith");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        // Visualize the job schedule
        scheduler.visualizeSchedule();

        // Restore console output
        System.setOut(System.out);

        // Expected output format
        String expectedOutput =
                "=== Job Schedule ===\n" +
                "Job Name: Design Homepage | Priority: High | Recurrence: Weekly | Assigned To: John Doe | Status: Active\n" +
                "Job Name: Backend Development | Priority: Medium | Recurrence: Daily | Assigned To: Jane Smith | Status: Active\n";

        // Normalize output
        String actualOutput = outputStream.toString().replaceAll("\\s+", " ").trim();
        String expectedNormalized = expectedOutput.replaceAll("\\s+", " ").trim();

        // Assert using contains to allow minor differences
        assertTrue(actualOutput.contains(expectedNormalized),
                "The visualized job schedule does not match the expected output.");
    }

    @Test
    void testVisualizeScheduleWhenEmpty() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize empty JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Visualize the job schedule
        scheduler.visualizeSchedule();

        // Restore console output
        System.setOut(System.out);

        // Expected output for an empty schedule
        String expectedOutput = "=== Job Schedule ===\nNo jobs scheduled.";

        // Normalize output
        String actualOutput = outputStream.toString().replaceAll("\\s+", " ").trim();
        String expectedNormalized = expectedOutput.replaceAll("\\s+", " ").trim();

        // Assert using contains to allow minor differences
        assertTrue(actualOutput.contains(expectedNormalized),
                "The visualization for an empty schedule is incorrect.");
    }
}
