package test.blackbox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory5_PreventOverlappingJobsTest {

    @Test
    void testPreventOverlappingJobsForSameWorker() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create the first job for John Doe
        Job job1 = new Job("Design Homepage");
        job1.assignTo("John Doe");

        // Create the second job for the same worker (should fail)
        Job job2 = new Job("Backend Development");
        job2.assignTo("John Doe");

        // Add jobs to the scheduler
        boolean result1 = scheduler.addJob(job1);  // Should succeed
        boolean result2 = scheduler.addJob(job2);  // Should fail

        // Restore console output
        System.setOut(System.out);

        // ✅ Assert that the first job was added
        assertTrue(result1, "The first job should be successfully added.");

        // ❌ Assert that the second job was blocked
        assertFalse(result2, "The second job should be rejected due to worker conflict.");

        // 📝 Assert the correct error message was printed
        String expectedError = "Error: Worker/Resource 'John Doe' is already assigned to another job.";
        assertTrue(outputStream.toString().contains(expectedError),
                "The correct error message should be printed for overlapping jobs.");
    }
}
