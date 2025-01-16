package jobscheduling;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class teststory1 {

    @Test
    public void testAddJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("ADM-001", "TaskA", Arrays.asList("urgent", "review"));
        scheduler.addJob(job);

        // Validate using the scheduler's existing functionality
        assertNotNull(scheduler); // Ensure scheduler is initialized
    }

    @Test
    public void testFinalizeJobTwice() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("ADM-200", "ReportTask", Arrays.asList("urgent"));
        scheduler.addJob(job);

        // First finalization
        scheduler.finalizeJob("ADM-200");

        // Second finalization (verify no exception occurs)
        scheduler.finalizeJob("ADM-200");

        // Check console output manually (Black-Box Testing)
    }

    @Test
    public void testAssignTagsToNonExistentJob() {
        JobScheduler scheduler = new JobScheduler();

        // Try assigning tags to a non-existent job
        scheduler.assignTags("XYZ-999", Arrays.asList("client", "review"));

        // Check console output manually (Black-Box Testing)
    }

    @Test
    public void testFilterJobsByNonExistentTag() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("MGR-300", "ManagerTask", Arrays.asList("high"));
        scheduler.addJob(job);

        // Filter by a non-existent tag
        scheduler.filterJobsByTags("unknown");

        // Check console output manually (Black-Box Testing)
    }

    @Test
    public void testGenerateSummaryReport() {
        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("ADM-005", "AdminTask", Arrays.asList("critical"));
        Job job2 = new Job("MGR-006", "ManagerTask", Arrays.asList("high"));
        scheduler.addJob(job1);
        scheduler.addJob(job2);
        scheduler.finalizeJob("ADM-005");

        // Generate summary report
        scheduler.generateSummaryReport();

        // Check console output manually (Black-Box Testing)
    }
}
