package test.whitebox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory3_AssignJobsToWorkersWhiteBoxTest {

    @Test
    void testValidWorkerAssignment() {
        // Create a new job
        Job job = new Job("Server Maintenance");

        // Assign a valid worker
        job.assignTo("Alice");
        assertEquals("Alice", job.getAssignedTo(), "Worker should be assigned to Alice.");
    }

    @Test
    void testInvalidWorkerAssignment() {
        // Create a new job
        Job job = new Job("Network Setup");

        // Assign an empty worker
        job.assignTo("");
        assertNull(job.getAssignedTo(), "Worker assignment should fail for empty string.");

        // Assign a null worker
        job.assignTo(null);
        assertNull(job.getAssignedTo(), "Worker assignment should fail for null value.");
    }

    @Test
    void testPreventMultipleAssignmentsToSameWorker() {
        JobScheduler scheduler = new JobScheduler();

        // First job assigned to Bob
        Job job1 = new Job("Database Backup");
        job1.assignTo("Bob");
        assertTrue(scheduler.addJob(job1), "First job should be added successfully.");

        // Second job also assigned to Bob (should fail)
        Job job2 = new Job("Security Audit");
        job2.assignTo("Bob");
        assertFalse(scheduler.addJob(job2), "Second job should fail due to worker conflict.");
    }
}
