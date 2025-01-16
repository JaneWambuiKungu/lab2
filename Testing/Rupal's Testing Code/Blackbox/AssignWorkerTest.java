package test.blackbox;

// Import necessary classes
import jobscheduler.Job; // Import your Job class
import org.junit.jupiter.api.Test; // Import JUnit annotations
import static org.junit.jupiter.api.Assertions.*; // Import assertions

public class UserStory3_AssignWorkerTest {

    @Test
    void testAssignTo() {
        Job job1 = new Job("Worker Assignment Test 1");
        Job job2 = new Job("Worker Assignment Test 2");

        // Assign job1 to a worker
        job1.assignTo("John Doe");
        assertEquals("John Doe", job1.getAssignedTo(), "Job 1 should be assigned to John Doe");

        // Assign job2 to a different worker
        job2.assignTo("Jane Smith");
        assertEquals("Jane Smith", job2.getAssignedTo(), "Job 2 should be assigned to Jane Smith");

        // Attempt to assign the same worker to job2 (should fail due to existing assignment)
        // Assuming JobScheduler or assignTo method in Job class handles this logic
       // job2.assignTo("John Doe");
      //  assertNotEquals("John Doe", job2.getAssignedTo(),
       //         "Job 2 should not allow reassignment of a worker already assigned to another job");
    }

    @Test
    void testAssignInvalidWorker() {
        Job job = new Job("Worker Assignment Invalid Test");

        // Assign job to an invalid worker (e.g., empty or null value)
        job.assignTo("");
        assertNull(job.getAssignedTo(), "Job should not be assigned to an invalid worker (empty string)");

        job.assignTo(null);
        assertNull(job.getAssignedTo(), "Job should not be assigned to an invalid worker (null value)");
    }
}
