package test.blackbox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;






public class BlackBoxJobSchedulerTest1 {
    private final JobScheduler scheduler = new JobScheduler();

    @Test
    public void testAddValidJob() {
        scheduler.addJob("job123");
        assertTrue(scheduler.getLogs().containsKey("job123"), "Log should contain 'job123' after adding.");
    }

    @Test
    public void testAddEmptyJobId() {
        scheduler.addJob("");
        assertFalse(scheduler.getLogs().containsKey(""), "Empty job ID should not be added.");
    }

    @Test
    public void testSetDependenciesWithInvalidJob() {
        scheduler.addJob("jobA");
        scheduler.getActiveJobs().get(0).addDependency("nonexistentJob");
        scheduler.validateInputs();
        // Expected console output check (mocking needed for exact validation).
    }

    @Test
    public void testCancelAllJobs() {
        scheduler.addJob("job1");
        scheduler.addJob("job2");
        scheduler.cancelAllJobs();
        assertEquals(0, scheduler.getActiveJobs().size(), "All jobs should be cancelled.");
    }

    @Test
    public void testExportSchedule() {
        scheduler.addJob("job1");
        scheduler.addJob("job2");
        scheduler.exportSchedule();
        // Validate file content manually or mock file writer.
    }
}
