package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory1_AssignPriorityWhiteBoxTest {

    @Test
    void testDifferentPrioritiesForDifferentJobs() {
        // Create multiple jobs
        Job job1 = new Job("Frontend Development");
        Job job2 = new Job("Backend Development");
        Job job3 = new Job("Database Optimization");

        // Assign different priorities to each job
        job1.setPriority("High");
        job2.setPriority("Medium");
        job3.setPriority("Low");

        // Verify that each job has the correct priority
        assertEquals("High", job1.getPriority(), "Job 1 should have High priority.");
        assertEquals("Medium", job2.getPriority(), "Job 2 should have Medium priority.");
        assertEquals("Low", job3.getPriority(), "Job 3 should have Low priority.");
    }
}
