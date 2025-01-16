package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JobWhiteBoxAdditionalTests {

    @Test
    void testInvalidPriorityAssignment() {
        Job job = new Job("Invalid Priority Test");
        job.setPriority("Urgent");  // Invalid priority
        assertEquals("Medium", job.getPriority(), "Invalid priority should not be set.");
    }

    @Test
    void testNullWorkerAssignment() {
        Job job = new Job("Null Worker Test");
        job.assignTo(null);
        assertNull(job.getAssignedTo(), "Worker assignment should fail for null value.");
    }

    @Test
    void testPauseCompletedJob() {
        Job job = new Job("Completed Job Test");
        job.markAsCompleted();
        boolean result = job.pauseJob();
        assertFalse(result, "Cannot pause a completed job.");
    }
}
