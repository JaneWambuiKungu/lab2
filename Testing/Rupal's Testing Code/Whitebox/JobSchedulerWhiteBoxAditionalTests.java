package test.whitebox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class JobSchedulerWhiteBoxAdditionalTests {

    @Test
    void testAddDuplicateJob() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Duplicate Job Test");
        scheduler.addJob(job1);

        Job job2 = new Job("Duplicate Job Test");
        boolean result = scheduler.addJob(job2);

        assertFalse(result, "Duplicate job should not be added.");
    }

    @Test
    void testRemoveNonExistentJob() {
        JobScheduler scheduler = new JobScheduler();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scheduler.removeJob("Nonexistent Job");

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Job 'Nonexistent Job' not found in the schedule."),
                   "Should notify that the job does not exist.");
    }

    @Test
    void testNotifyStartCompletedJob() {
        JobScheduler scheduler = new JobScheduler();

        Job job = new Job("Completed Job Start Test");
        scheduler.addJob(job);
        job.markAsCompleted();

        scheduler.notifyForJobStart();
    }
    @Test
    void testRemoveExistingJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Test Remove Job");

        scheduler.addJob(job);
        scheduler.removeJob("Test Remove Job");

        assertFalse(scheduler.jobExists("Test Remove Job"), "Job should be removed successfully.");
    }

    @Test
    void testRemoveJobThatDoesNotExist() {
        JobScheduler scheduler = new JobScheduler();
        scheduler.removeJob("Non-Existent Job");
        // No assertion needed if no exception is thrown
    }

    @Test
    void testListJobsForWorkerWithNoJobs() {
        JobScheduler scheduler = new JobScheduler();
        scheduler.listJobsForWorker("Unassigned Worker");
        // Verify it runs without error
    }

    @Test
    void testVisualizeScheduleWithMultipleJobs() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Job 1");
        job1.assignTo("Alice");
        scheduler.addJob(job1);

        Job job2 = new Job("Job 2");
        job2.assignTo("Bob");
        scheduler.addJob(job2);

        scheduler.visualizeSchedule();
        // Verify console output if necessary
    }

    @Test
    void testStartNonExistentJob() {
        JobScheduler scheduler = new JobScheduler();
        scheduler.startJob("Non-Existent Job");
        // Validate that the system handles this gracefully
    }
    
    @Test
    void testStartJobByName() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("System Upgrade");
        scheduler.addJob(job);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scheduler.startJob("System Upgrade");

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Notification: Job 'System Upgrade' has started."),
                   "Job should start when invoked by name.");
    }
    
    @Test
    void testStartPausedJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Paused Job");
        scheduler.addJob(job);
        job.pauseJob();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scheduler.startJob("Paused Job");

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Cannot start a paused job"),
                   "Cannot start a paused job without resuming.");
    }
    
    @Test
    void testAddJobWithEmptyName() {
        JobScheduler scheduler = new JobScheduler();
        Job emptyNameJob = new Job("");

        boolean result = scheduler.addJob(emptyNameJob);
        assertFalse(result, "Job with an empty name should not be added.");
    }
    
    @Test
    void testNotifyStartWhenAllJobsCompleted() {
        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("Completed Job 1");
        Job job2 = new Job("Completed Job 2");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        job1.markAsCompleted();
        job2.markAsCompleted();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        scheduler.notifyForJobStart();

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Cannot start a completed job"),
                   "Should notify that completed jobs cannot be started.");
    }
}
