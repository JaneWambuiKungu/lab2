package test.whitebox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStory4_VisualizeScheduleWhiteBoxTest {

    @Test
    void testVisualizeEmptySchedule() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an empty job scheduler
        JobScheduler scheduler = new JobScheduler();

        // Visualize the empty schedule
        scheduler.visualizeSchedule();

        // Restore console output
        System.setOut(System.out);

        // Normalize actual output (remove extra whitespaces and line breaks)
        String actualOutput = outputStream.toString().replaceAll("\\s+", " ").trim();
        System.out.println("Actual Output (Normalized):\n" + actualOutput);

        // Expected output for an empty schedule
        String expectedOutput = "=== Job Schedule === No jobs scheduled.";
        expectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();

        // Compare normalized outputs
        assertEquals(expectedOutput, actualOutput, "The visualization for an empty schedule is incorrect.");
    }

    @Test
    void testVisualizeScheduleWithJobs() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create the job scheduler
        JobScheduler scheduler = new JobScheduler();

        // Create and add jobs
        Job job1 = new Job("Design Homepage");
        job1.setPriority("High");
        job1.assignTo("John Doe");
        scheduler.addJob(job1);

        Job job2 = new Job("Backend Development");
        job2.setPriority("Medium");
        job2.assignTo("Jane Smith");
        scheduler.addJob(job2);

        // Visualize the job schedule
        scheduler.visualizeSchedule();

        // Restore console output
        System.setOut(System.out);

        // Normalize actual output
        String actualOutput = outputStream.toString().replaceAll("\\s+", " ").trim();
        System.out.println("Actual Output (Normalized):\n" + actualOutput);

        // Expected output format
        String expectedOutputJob1 = "Job Name: Design Homepage | Priority: High | Recurrence: None | Assigned To: John Doe | Status: Active";
        String expectedOutputJob2 = "Job Name: Backend Development | Priority: Medium | Recurrence: None | Assigned To: Jane Smith | Status: Active";

        // Normalize expected outputs
        expectedOutputJob1 = expectedOutputJob1.replaceAll("\\s+", " ").trim();
        expectedOutputJob2 = expectedOutputJob2.replaceAll("\\s+", " ").trim();

        // Verify that both jobs are displayed correctly
        assertEquals(true, actualOutput.contains(expectedOutputJob1), "Job 1 is not displayed correctly.");
        assertEquals(true, actualOutput.contains(expectedOutputJob2), "Job 2 is not displayed correctly.");
    }
}
