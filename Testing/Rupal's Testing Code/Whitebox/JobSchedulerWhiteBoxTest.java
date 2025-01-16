package test.whitebox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class JobSchedulerWhiteBoxTest {

    // === User Story 1: Assigning Priorities to Jobs ===
    @Test
    void testSetJobPriority() {
        Job job = new Job("Priority Test");
        job.setPriority("High");
        assertEquals("High", job.getPriority(), "Job priority should be set to High.");
    }

    @Test
    void testInvalidPriorityAssignment() {
        Job job = new Job("Invalid Priority Test");
        job.setPriority("Urgent");
        assertEquals("Medium", job.getPriority(), "Invalid priority should not change the priority.");
    }

    // === User Story 2: Creating Recurring Jobs ===
    @Test
    void testSetJobRecurrence() {
        Job job = new Job("Recurring Job Test");
        job.setRecurrence("Weekly");
        assertEquals("Weekly", job.getRecurrence(), "Recurrence should be set to Weekly.");
    }

    @Test
    void testInvalidRecurrenceAssignment() {
        Job job = new Job("Invalid Recurrence Test");
        job.setRecurrence("Yearly");
        assertEquals("None", job.getRecurrence(), "Invalid recurrence should not change the value.");
    }

    // === User Story 3: Assigning Jobs to Specific Workers/Resources ===
    @Test
    void testAssignWorkerToJob() {
        Job job = new Job("Worker Assignment Test");
        job.assignTo("John Doe");
        assertEquals("John Doe", job.getAssignedTo(), "Job should be assigned to John Doe.");
    }

    @Test
    void testAssignNullWorkerToJob() {
        Job job = new Job("Null Worker Assignment Test");
        job.assignTo(null);
        assertNull(job.getAssignedTo(), "Job should not accept null worker.");
    }

    // === User Story 4: Visualizing the Job Schedule ===
    @Test
    void testVisualizeEmptySchedule() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.visualizeSchedule();

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("No jobs scheduled."));
    }

    // === User Story 5: Preventing Overlapping Jobs ===
    @Test
    void testPreventWorkerOverlap() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Task 1");
        job1.assignTo("Alice");
        assertTrue(scheduler.addJob(job1));

        Job job2 = new Job("Task 2");
        job2.assignTo("Alice");
        assertFalse(scheduler.addJob(job2), "Should prevent assigning the same worker.");
    }

    // === User Story 6: Pausing and Resuming Jobs ===
    @Test
    void testPauseAndResumeJob() {
        Job job = new Job("Pause and Resume Test");

        assertTrue(job.pauseJob(), "Job should be paused.");
        assertTrue(job.resumeJob(), "Job should be resumed.");
    }

    @Test
    void testPauseCompletedJob() {
        Job job = new Job("Completed Job Pause Test");
        job.markAsCompleted();
        assertFalse(job.pauseJob(), "Completed job cannot be paused.");
    }

    // === User Story 7: Notifications for Job Start ===
    @Test
    void testJobStartNotification() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Start Notification Test");
        job.startJob();

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Notification: Job 'Start Notification Test' has started."));
    }

    // === User Story 8: Marking Jobs as Completed ===
    @Test
    void testMarkJobAsCompleted() {
        Job job = new Job("Completion Test");
        job.markAsCompleted();
        assertTrue(job.isCompleted(), "Job should be marked as completed.");
    }

    @Test
    void testMarkPausedJobAsCompleted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Paused Completion Test");
        job.pauseJob();
        job.markAsCompleted();

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Cannot complete a paused job."));
    }

    // === User Story 9: Searching for Jobs by Keyword ===
    @Test
    void testSearchJobByKeyword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.addJob(new Job("Database Backup"));
        scheduler.searchJobsByKeyword("Database");

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Database Backup"));
    }

    @Test
    void testSearchJobWithNoMatch() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.searchJobsByKeyword("NonExistentJob");

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("No jobs found"));
    }

    // === User Story 10: Changing Job Priority After Creation ===
    @Test
    void testChangePriorityOfActiveJob() {
        Job job = new Job("Change Priority Test");
        job.setPriority("Low");
        assertEquals("Low", job.getPriority(), "Priority should be changed to Low.");
    }

    @Test
    void testChangePriorityOfCompletedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Completed Priority Test");
        job.markAsCompleted();
        job.setPriority("High");

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Cannot change priority of a completed job"));
    }
    @Test
    void testDisplayJobDetails() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Details Job");
        job.setPriority("High");
        job.setRecurrence("Daily");
        job.assignTo("Alice");
        job.displayJobDetails();

        System.setOut(System.out);
        String output = outputStream.toString();
        assertTrue(output.contains("Job Name: Details Job"));
        assertTrue(output.contains("Priority: High"));
        assertTrue(output.contains("Recurrence: Daily"));
        assertTrue(output.contains("Assigned To: Alice"));
        assertTrue(output.contains("Status: Active"));
    }

    
    @Test
    void testAssignEmptyWorker() {
        Job job = new Job("Invalid Worker Assignment");
        job.assignTo("");  // Invalid assignment

        assertNull(job.getAssignedTo(), "Empty worker name should not be assigned.");
    }
    @Test
    void testJobExists() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Existence Check");
        scheduler.addJob(job);

        assertTrue(scheduler.jobExists("Existence Check"), "Job should exist in the scheduler.");
        assertFalse(scheduler.jobExists("Nonexistent Job"), "Job should not exist.");
    }
    @Test
    void testRemoveJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Removable Job");
        scheduler.addJob(job);

        scheduler.removeJob("Removable Job");
        assertFalse(scheduler.jobExists("Removable Job"), "Job should be removed from the schedule.");
    }
    @Test
    void testNotifyForJobStartWithJobs() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("Job 1");
        Job job2 = new Job("Job 2");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        scheduler.notifyForJobStart();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Notification: Job 'Job 1' has started."));
        assertTrue(outputStream.toString().contains("Notification: Job 'Job 2' has started."));
    }
    @Test
    void testRemoveNonExistentJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Arrange: Create a scheduler with no jobs
            JobScheduler scheduler = new JobScheduler();

            // Act: Attempt to remove a job that doesn't exist
            scheduler.removeJob("GhostJob");

            // Assert: Verify error message is printed
            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Job 'GhostJob' not found in the schedule."),
                    "Removing a non-existent job should print an error.");

            // Assert: Verify the scheduler's job list is still empty
            assertFalse(scheduler.jobExists("GhostJob"), "Scheduler should not contain 'GhostJob'.");

        } finally {
            // Ensure console output is reset
            System.setOut(originalOut);
        }
    }

    @Test
    void testJobExistsCaseSensitivity() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("CaseTestJob");
        scheduler.addJob(job);

        assertTrue(scheduler.jobExists("CaseTestJob"), "Exact case should be found.");
        assertFalse(scheduler.jobExists("casetestjob"), "Case-insensitive check should fail.");
    }
    @Test
    void testAddJobWithEmptyName() {
        JobScheduler scheduler = new JobScheduler();
        Job emptyNameJob = new Job("");

        boolean result = scheduler.addJob(emptyNameJob);

        assertFalse(result, "Job with an empty name should not be added.");
    }
    @Test
    void testStartCompletedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Completed Job");
        job.markAsCompleted();
        job.startJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot start a completed job"),
                "Completed jobs should not be started.");
    }
    @Test
    void testStartNonExistentJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.startJob("NonExistentJob");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'NonExistentJob' not found in the schedule."),
                "Starting a non-existent job should produce an error.");
    }

    @Test
    void testSameWorkerAssignedToMultipleJobs() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Job 1");
        job1.assignTo("WorkerA");
        assertTrue(scheduler.addJob(job1), "First job should be added.");

        Job job2 = new Job("Job 2");
        job2.assignTo("WorkerA");  // Same worker

        assertFalse(scheduler.addJob(job2), "Should prevent assigning the same worker to multiple jobs.");
    }
    @Test
    void testListJobsForUnassignedWorker() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.listJobsForWorker("WorkerZ");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("No jobs assigned to worker: 'WorkerZ'."),
                "Should notify when a worker has no jobs.");
    }
    @Test
    void testMarkNonExistentJobAsCompleted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.markJobAsCompleted("UnknownJob");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'UnknownJob' not found in the schedule."),
                "Marking a non-existent job as completed should print an error.");
    }
    @Test
    void testAssignWorkerToCompletedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Arrange: Create and complete a job
            Job job = new Job("Completed Assignment Test");
            job.markAsCompleted();

            // Act: Attempt to assign a worker
            job.assignTo("WorkerB");

            // Assert: Verify error message
            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Cannot reassign a completed job"),
                    "Should print error when assigning worker to a completed job.");

            // Assert: Verify that worker was not assigned
            assertNull(job.getAssignedTo(), "Worker should not be assigned to a completed job.");

        } finally {
            // Ensure console output is reset
            System.setOut(originalOut);
        }
    }

    @Test
    void testStartPausedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Arrange: Create JobScheduler and add a job
            JobScheduler scheduler = new JobScheduler();
            Job job = new Job("Paused Job Start Test");
            scheduler.addJob(job);

            // Act: Pause the job and attempt to start it
            job.pauseJob();
            scheduler.startJob("Paused Job Start Test");  // Correct way to start

            // Assert: Check if the correct message is printed
            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Cannot start a paused job"),
                    "Starting a paused job should not be allowed.");
        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    void testAddNullJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        boolean result = scheduler.addJob(null);

        System.setOut(System.out);

        assertFalse(result, "Adding a null job should fail.");
        assertTrue(outputStream.toString().contains("Error: Cannot add a null job."),
                "Should print an error when adding a null job.");
    }
    
    @Test
    void testStartJobsWithCompletedJobsPresent() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("Active Job");
        Job job2 = new Job("Completed Job");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        job2.markAsCompleted();

        scheduler.notifyForJobStart();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Notification: Job 'Active Job' has started."),
                "Active jobs should start.");
        assertTrue(outputStream.toString().contains("Cannot start a completed job: 'Completed Job'."),
                "Completed jobs should not start.");
    }
    @Test
    void testRemoveJobTwice() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Arrange: Create scheduler and add a job
            JobScheduler scheduler = new JobScheduler();
            Job job = new Job("Removable Job");

            scheduler.addJob(job);

            // Act: First removal (should succeed)
            scheduler.removeJob("Removable Job");

            // Act: Second removal (should fail)
            scheduler.removeJob("Removable Job");

            // Assert: First removal success message
            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Job 'Removable Job' removed from the schedule."),
                    "First removal should succeed.");

            // Assert: Second removal failure message
            assertTrue(consoleOutput.contains("Job 'Removable Job' not found in the schedule."),
                    "Second removal should fail.");

        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    void testAssignSameWorkerWithDifferentPriorities() {
        JobScheduler scheduler = new JobScheduler();

        Job highPriorityJob = new Job("High Priority Task");
        highPriorityJob.setPriority("High");
        highPriorityJob.assignTo("Bob");

        Job lowPriorityJob = new Job("Low Priority Task");
        lowPriorityJob.setPriority("Low");
        lowPriorityJob.assignTo("Bob");

        assertTrue(scheduler.addJob(highPriorityJob), "High priority job should be added.");
        assertFalse(scheduler.addJob(lowPriorityJob), "Worker Bob cannot be assigned to multiple jobs.");
    }
    @Test
    void testVisualizationAfterJobRemoval() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Visual Job");
        scheduler.addJob(job);

        scheduler.visualizeSchedule();

        scheduler.removeJob("Visual Job");
        scheduler.visualizeSchedule();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job Name: Visual Job"),
                "Job should appear in the first visualization.");
        assertTrue(outputStream.toString().contains("No jobs scheduled."),
                "Job should not appear after removal.");
    }
    @Test
    void testStartActiveJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Active Job Test");
        job.startJob();  // First start
        job.startJob();  // Attempt to start again

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Job 'Active Job Test' is already active."),
                   "Starting an already active job should print a warning.");
    }
    
    @Test
    void testAssignWorkerToMultipleJobs() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Task 1");
        job1.assignTo("Bob");
        scheduler.addJob(job1);

        Job job2 = new Job("Task 2");
        job2.assignTo("Bob");
        boolean result = scheduler.addJob(job2);

        assertFalse(result, "Worker 'Bob' should not be assigned to multiple jobs.");
    }
    
    @Test
    void testAddJobWithWhitespaceName() {
        JobScheduler scheduler = new JobScheduler();
        Job jobWithSpaces = new Job("   ");  // Job name with only spaces

        boolean result = scheduler.addJob(jobWithSpaces);
        assertFalse(result, "Job with only spaces in the name should not be added.");
    }
    
    @Test
    void testSetRecurrenceForCompletedJob() {
        Job job = new Job("Completed Job");
        job.markAsCompleted();
        job.setRecurrence("Weekly");
        assertNotEquals("Weekly", job.getRecurrence(), "Recurrence should not be updated for a completed job.");
    }
    
    @Test
    void testPauseAlreadyPausedJob() {
        Job job = new Job("Paused Job");
        job.pauseJob();  // First pause
        assertFalse(job.pauseJob(), "Pausing an already paused job should fail.");
    }
    
    @Test
    void testResumeActiveJob() {
        Job job = new Job("Active Job");
        assertFalse(job.resumeJob(), "Resuming an already active job should fail.");
    }
    
    @Test
    void testMarkAlreadyCompletedJob() {
        Job job = new Job("Already Completed Job");
        job.markAsCompleted();
        job.markAsCompleted();  // Attempt to mark it again
        assertTrue(job.isCompleted(), "Job should remain completed.");
    }

 // Test: Adding a job with a null name
    @Test
    void testAddJobWithNullName() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job(null);
        
        assertFalse(scheduler.addJob(job), "Job with a null name should not be added.");
    }

    // Test: Starting an already active job
    @Test
    void testStartAlreadyActiveJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Active Job");
        
        scheduler.addJob(job);
        job.startJob();  // First start

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        job.startJob();  // Attempt to start again

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Active Job' is already active."),
                "Starting an already active job should print a warning.");
    } 
    
 // Test: Resuming an already active job
    @Test
    void testResumeAlreadyActiveJob() {
        Job job = new Job("Already Active Job");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        job.resumeJob();  // Attempt to resume without pausing

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Already Active Job' is already active."),
                "Resuming an already active job should print a warning.");
    }
    
    @Test
    void testReassignJobToSameWorker() {
        Job job = new Job("Server Maintenance");
        job.assignTo("Alice");

        // Attempt to reassign to the same worker
        job.assignTo("Alice");

        assertEquals("Alice", job.getAssignedTo(), "Job should remain assigned to Alice.");
    }
    

    @Test
    void testPauseUnstartedJob() {
        Job job = new Job("Unstarted Job");

        boolean result = job.pauseJob();

        assertTrue(result, "Pausing an unstarted job should succeed as it's considered active.");
    }
    @Test
    void testAddDuplicateJobNames() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Duplicate Job");
        Job job2 = new Job("Duplicate Job");

        assertTrue(scheduler.addJob(job1), "First job should be added.");
        assertFalse(scheduler.addJob(job2), "Second job with the same name should not be added.");
    }

    @Test
    void testPauseJobMultipleTimes() {
        Job job = new Job("Multi-Pause Job");

        assertTrue(job.pauseJob(), "First pause should succeed.");
        assertFalse(job.pauseJob(), "Pausing an already paused job should fail.");
    }
    
    @Test
    void testSearchJobsWithNullKeyword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.searchJobsByKeyword(null);

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("No jobs found"),
                "Searching with a null keyword should return no results.");
    }

    @Test
    void testResumeCompletedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Completed Resume Test");
        job.markAsCompleted();
        job.resumeJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot resume a completed job"),
                "Completed jobs should not be resumed.");
    }
    
    @Test
    void testSearchJobsWithSpecialCharacters() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.addJob(new Job("Backup_2023#"));

        scheduler.searchJobsByKeyword("#");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Backup_2023#"),
                "Search should return jobs with special characters.");
    }
    
    @Test
    void testAddDuplicateJobCaseInsensitive() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Data Backup");
        Job job2 = new Job("data backup");  // Same name, different case

        assertTrue(scheduler.addJob(job1), "First job should be added.");
        assertFalse(scheduler.addJob(job2), "Duplicate job name (case-insensitive) should not be added.");
    }

    @Test
    void testStartJobWithoutAssignedWorker() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Unassigned Job");
        job.startJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Notification: Job 'Unassigned Job' has started."),
                "Unassigned job should still be allowed to start.");
    }

    @Test
    void testRemoveNullJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.removeJob(null);

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'null' not found in the schedule."),
                "Attempting to remove a null job should print an error.");
    }

    @Test
    void testPauseUnassignedJob() {
        Job job = new Job("Unassigned Pause Job");

        boolean result = job.pauseJob();

        assertTrue(result, "Unassigned job should be paused successfully.");
    }

    @Test
    void testSearchWithEmptyString() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.searchJobsByKeyword("");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("No jobs found"),
                "Searching with an empty string should return no results.");
    }

    @Test
    void testRemoveCompletedJob() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Completed Job");

        scheduler.addJob(job);
        job.markAsCompleted();
        scheduler.removeJob("Completed Job");

        assertFalse(scheduler.jobExists("Completed Job"), "Completed job should be removable.");
    }

    
    @Test
    void testSequentialWorkerAssignment() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Task 1");
        job1.assignTo("Alice");
        scheduler.addJob(job1);
        job1.markAsCompleted();

        Job job2 = new Job("Task 2");
        job2.assignTo("Alice");
        
        assertTrue(scheduler.addJob(job2), "Worker Alice should be available after completing previous job.");
    }

    @Test
    void testStartRemovedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Temporary Job");

        scheduler.addJob(job);
        scheduler.removeJob("Temporary Job");
        scheduler.startJob("Temporary Job");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Temporary Job' not found in the schedule."),
                "Starting a removed job should print an error.");
    }

    @Test
    void testAssignWorkerToMultipleActiveJobs() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Active Job 1");
        job1.assignTo("Bob");
        scheduler.addJob(job1);

        Job job2 = new Job("Active Job 2");
        job2.assignTo("Bob");

        assertFalse(scheduler.addJob(job2), "Worker Bob should not be assigned to multiple active jobs.");
    }

    @Test
    void testResumeUnpausedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Resume Unpaused Job");
        job.resumeJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Resume Unpaused Job' is already active."),
                "Resuming an unpaused job should print a warning.");
    }

    @Test
    void testStartJobNotInScheduler() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.startJob("Nonexistent Job");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Nonexistent Job' not found in the schedule."),
                "Starting a job that wasn't added should print an error.");
    }

    @Test
    void testAssignWorkerWithWhitespaceName() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Whitespace Worker Test");
        job.assignTo("    ");  // Worker name with spaces

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Error: Worker/Resource cannot be null or empty."),
                "Assigning a worker with only spaces should print an error.");
    }

    @Test
    void testRemoveAllJobs() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Job 1");
        Job job2 = new Job("Job 2");
        Job job3 = new Job("Job 3");

        scheduler.addJob(job1);
        scheduler.addJob(job2);
        scheduler.addJob(job3);

        scheduler.removeJob("Job 1");
        scheduler.removeJob("Job 2");
        scheduler.removeJob("Job 3");

        assertFalse(scheduler.jobExists("Job 1"), "Job 1 should be removed.");
        assertFalse(scheduler.jobExists("Job 2"), "Job 2 should be removed.");
        assertFalse(scheduler.jobExists("Job 3"), "Job 3 should be removed.");
    }

    @Test
    void testAddJobWithLongName() {
        JobScheduler scheduler = new JobScheduler();
        
        String longJobName = "A".repeat(500);  // Very long job name
        Job longNameJob = new Job(longJobName);

        assertTrue(scheduler.addJob(longNameJob), "Job with a long name should be added successfully.");
    }

    @Test
    void testPauseRemovedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Temporary Job");

        scheduler.addJob(job);
        scheduler.removeJob("Temporary Job");
        job.pauseJob();  // Attempt to pause after removal

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot pause a completed job"),
                "Pausing a removed job should not be allowed.");
    }

    @Test
    void testAddJobWithSpecialCharacters() {
        JobScheduler scheduler = new JobScheduler();
        Job specialCharJob = new Job("Deploy@2023!");

        boolean result = scheduler.addJob(specialCharJob);

        assertTrue(result, "Job with special characters in the name should be added.");
    }

    @Test
    void testSearchJobByNumericKeyword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.addJob(new Job("Database Backup 2023"));
        scheduler.searchJobsByKeyword("2023");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Database Backup 2023"),
                "Search with a numeric keyword should return matching jobs.");
    }

    @Test
    void testNotifyJobStartWhenEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.notifyForJobStart();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("No jobs available to start."),
                "Notification should indicate no jobs are available to start.");
    }


    @Test
    void testMarkNullJobAsCompleted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.markJobAsCompleted(null);

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'null' not found in the schedule."),
                "Attempting to mark a null job as completed should print an error.");
    }

    @Test
    void testVisualizeCompletedJobs() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Task 1");
        Job job2 = new Job("Task 2");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        job1.markAsCompleted();
        job2.markAsCompleted();

        scheduler.visualizeSchedule();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Completed"), 
                   "Visualization should show completed status for all jobs.");
    }

    @Test
    void testAssignWorkerToPausedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Paused Job Test");
        job.pauseJob();
        job.assignTo("WorkerC");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot reassign a completed job"),
                "Paused job should not allow worker reassignment.");
    }

    @Test
    void testSearchJobWithPartialName() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.addJob(new Job("Database Backup"));

        scheduler.searchJobsByKeyword("Backup");

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Database Backup"),
                "Partial keyword search should return matching jobs.");
    }

    @Test
    void testPauseNullJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.startJob(null);

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'null' not found in the schedule."),
                "Attempting to start a null job should print an error.");
    }


    @Test
    void testAddJobsWithDifferentCaseNames() {
        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Data Migration");
        Job job2 = new Job("data migration");  // Same name, different case

        assertTrue(scheduler.addJob(job1), "First job should be added.");
        assertFalse(scheduler.addJob(job2), "Duplicate job with different case should not be added.");
    }

    @Test
    void testVisualizeAfterRemovingAllJobs() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Task A");
        Job job2 = new Job("Task B");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        scheduler.removeJob("Task A");
        scheduler.removeJob("Task B");

        scheduler.visualizeSchedule();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("No jobs scheduled."),
                "Visualization should show no jobs after all are removed.");
    }


    @Test
    void testResumeRemovedJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Temp Job");

        scheduler.addJob(job);
        scheduler.removeJob("Temp Job");

        job.resumeJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot resume a completed job"),
                "Resuming a removed job should not be allowed.");
    }
    

    @Test
    void testImmediateJobCompletion() {
        JobScheduler scheduler = new JobScheduler();
        Job job = new Job("Instant Complete");

        scheduler.addJob(job);
        job.markAsCompleted();

        assertTrue(job.isCompleted(), "Job should be marked as completed immediately after being added.");
    }
    
    @Test
    void testPauseJobNotInScheduler() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Orphan Job");
        job.pauseJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Orphan Job' is now paused."),
                "Pausing a job not in the scheduler should still work.");
    }

    @Test
    void testAddSameJobObjectTwice() {
        JobScheduler scheduler = new JobScheduler();

        Job job = new Job("Duplicate Object Job");

        assertTrue(scheduler.addJob(job), "First addition of the job should succeed.");
        assertFalse(scheduler.addJob(job), "Second addition of the same job object should fail.");
    }

    @Test
    void testSearchWithNullKeyword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();
        scheduler.searchJobsByKeyword(null);

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("No jobs found"),
                "Searching with a null keyword should return no results.");
    }

    @Test
    void testNotifyWhenAllJobsPaused() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Paused Job 1");
        Job job2 = new Job("Paused Job 2");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        job1.pauseJob();
        job2.pauseJob();

        scheduler.notifyForJobStart();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot start a paused job"),
                "Notification should indicate that paused jobs can't be started.");
    }
    
    @Test
    void testResumeJobWithoutPausing() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Job job = new Job("Unpaused Resume");
        job.resumeJob();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Job 'Unpaused Resume' is already active."),
                "Resuming a job that wasn't paused should print a warning.");
    }

    @Test
    void testAddJobWithSpecialCharactersOnly() {
        JobScheduler scheduler = new JobScheduler();
        Job specialCharJob = new Job("!@#$%^&*()");

        boolean result = scheduler.addJob(specialCharJob);

        assertTrue(result, "Job with only special characters should still be added.");
    }

    @Test
    void testNotifyWhenAllJobsCompleted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        JobScheduler scheduler = new JobScheduler();

        Job job1 = new Job("Completed Job 1");
        Job job2 = new Job("Completed Job 2");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        job1.markAsCompleted();
        job2.markAsCompleted();

        scheduler.notifyForJobStart();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Cannot start a completed job"),
                "Completed jobs should not trigger start notifications.");
    }

    @Test
    void testStartNullJob() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            JobScheduler scheduler = new JobScheduler();
            scheduler.startJob(null);

            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Job 'null' not found in the schedule."),
                    "Starting a null job should print an error.");
        } finally {
            System.setOut(originalOut);
        }
    }
   
        @Test
        void testAddJobWithExtremelyLongName() {
            JobScheduler scheduler = new JobScheduler();
            String longName = "A".repeat(1000);  // Very long name
            Job job = new Job(longName);

            assertTrue(scheduler.addJob(job), "Job with a very long name should be added successfully.");
        }
        @Test
        void testSearchWithSpecialCharacters() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            try {
                JobScheduler scheduler = new JobScheduler();
                scheduler.addJob(new Job("Backup_2023#"));

                scheduler.searchJobsByKeyword("#");

                String consoleOutput = outputStream.toString();
                assertTrue(consoleOutput.contains("Backup_2023#"),
                        "Search should return jobs with special characters.");
            } finally {
                System.setOut(originalOut);
            }
        }

        @Test
        void testAddAndImmediatelyRemoveJob() {
            JobScheduler scheduler = new JobScheduler();
            Job job = new Job("Quick Remove Job");

            assertTrue(scheduler.addJob(job), "Job should be added successfully.");
            scheduler.removeJob("Quick Remove Job");

            assertFalse(scheduler.jobExists("Quick Remove Job"), "Job should be removed successfully.");
        }


    



}
    
