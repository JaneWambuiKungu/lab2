package test.blackbox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class JobSchedulerBlackBoxTest {

	@Test
	void testAddJobWithNullName() {
	    JobScheduler scheduler = new JobScheduler();
	    Job nullNameJob = new Job(null);

	    boolean result = scheduler.addJob(nullNameJob);

	    assertFalse(result, "Job with null name should not be added.");
	}
	@Test
	void testAddNullJob() {
	    JobScheduler scheduler = new JobScheduler();
	    boolean result = scheduler.addJob(null);
	    assertFalse(result, "Adding a null job should fail.");
	}

	@Test
	void testRemoveNonExistentJob() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.removeJob("NonExistentJob");

	    // You can check console output if necessary
	}

	@Test
	void testAssignSameWorkerToMultipleJobs() {
	    JobScheduler scheduler = new JobScheduler();

	    Job job1 = new Job("Task 1");
	    job1.assignTo("WorkerA");

	    Job job2 = new Job("Task 2");
	    job2.assignTo("WorkerA");

	    assertTrue(scheduler.addJob(job1), "First job should be added.");
	    assertFalse(scheduler.addJob(job2), "Second job should fail due to worker conflict.");
	}

	@Test
	void testAddDuplicateJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Duplicate Job");
	    Job job2 = new Job("Duplicate Job");

	    assertTrue(scheduler.addJob(job1), "First job should be added.");
	    assertFalse(scheduler.addJob(job2), "Duplicate job should not be added.");
	}

	@Test
	void testPauseNonExistentJob() {
	    // Capture console output
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    Job job = new Job("Non-existent Job");
	    boolean result = job.pauseJob();  // This will be false or not set, since the job is not in the scheduler

	    // Restore console output
	    System.setOut(System.out);

	    // Check if the expected error message is printed to the console
	    assertTrue(outputStream.toString().contains("Cannot pause a job that doesn't exist."),
	            "Pausing a non-existent job should print an error.");
	}
	
	@Test
	void testAssignNullWorkerToJob() {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    Job job = new Job("Null Worker Assignment");
	    job.assignTo(null);

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Error: Worker/Resource cannot be null or empty."),
	            "Assigning a null worker should trigger an error.");
	}


	@Test
	void testInvalidPriorityAssignment() {
	    Job job = new Job("Invalid Priority Job");
	    job.setPriority("Urgent");
	    assertEquals("Medium", job.getPriority(), "Invalid priority should revert to Medium.");
	}

	@Test
	void testJobStartAfterCompletion() {
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
	void testJobRemoveNonExistent() {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    JobScheduler scheduler = new JobScheduler();
	    scheduler.removeJob("Ghost Job");

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Job 'Ghost Job' not found in the schedule."),
	            "Trying to remove a non-existent job should print an error.");
	}

	@Test
	void testVisualizeEmptySchedule() {
	    JobScheduler scheduler = new JobScheduler();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    scheduler.visualizeSchedule();

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs scheduled."),
	            "Visualizing an empty schedule should print 'No jobs scheduled.'");
	}
	
	@Test
	void testAssignNullWorker() {
	    Job job = new Job("Task");
	    job.assignTo(null); // Assigning null to worker should fail.
	    assertNull(job.getAssignedTo(), "Worker should not be assigned.");
	}

	@Test
	void testAssignWorkerToMultipleJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Task 1");
	    job1.assignTo("Worker1");
	    scheduler.addJob(job1);

	    Job job2 = new Job("Task 2");
	    job2.assignTo("Worker1");
	    assertFalse(scheduler.addJob(job2), "Worker1 should not be assigned to multiple jobs.");
	}

	@Test
	void testResumeCompletedJob() {
	    Job job = new Job("Completed Task");
	    job.markAsCompleted();
	    boolean result = job.resumeJob(); // Should fail since the job is completed.
	    assertFalse(result, "Completed job cannot be resumed.");
	}

	
	@Test
	void testVisualizeJobSchedule() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Job 1");
	    Job job2 = new Job("Job 2");
	    scheduler.addJob(job1);
	    scheduler.addJob(job2);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    scheduler.visualizeSchedule();

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Job Name: Job 1"), "Job 1 should be displayed.");
	}
	

	@Test
	void testAddJobWithEmptyName() {
	    JobScheduler scheduler = new JobScheduler();
	    Job emptyNameJob = new Job(""); // Create job with empty name
	    assertFalse(scheduler.addJob(emptyNameJob), "Job with empty name should not be added.");
	}


	@Test
	void testAddValidJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("New Job");
	    job.setPriority("High");
	    job.setRecurrence("Daily");
	    job.assignTo("Worker 1");
	    
	    assertTrue(scheduler.addJob(job), "Valid job should be added successfully.");
	}

	@Test
	void testPauseCompletedJob() {
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    
	    assertFalse(job.pauseJob(), "Completed job cannot be paused.");
	}

	
	@Test
	void testChangePriorityForCompletedJob() {
	    // Create an instance of ByteArrayOutputStream to capture console output
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    // Create and complete the job
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();

	    // Attempt to change priority for the completed job
	    job.setPriority("Low");

	    // Restore console output
	    System.setOut(System.out);

	    // Verify that the error message is printed when trying to reassign a completed job
	    assertTrue(outputStream.toString().contains("Cannot change priority of a completed job"));
	}



	@Test
	void testJobStartNotification() {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    Job job = new Job("Job 1");
	    job.startJob();

	    assertTrue(outputStream.toString().contains("Notification: Job 'Job 1' has started."));
	}


	@Test
	void testJobAssignmentWithDuplicateWorkers() {
	    JobScheduler scheduler = new JobScheduler();

	    Job job1 = new Job("Task1");
	    job1.assignTo("Worker1");
	    assertTrue(scheduler.addJob(job1));

	    Job job2 = new Job("Task2");
	    job2.assignTo("Worker1");
	    assertFalse(scheduler.addJob(job2), "Worker1 should not be assigned to multiple jobs.");
	}


	@Test
	void testAssignJobWithEmptyName() {
	    Job job = new Job("");  // Create a job with an empty name
	    JobScheduler scheduler = new JobScheduler();
	    assertFalse(scheduler.addJob(job), "Job with an empty name should not be added.");
	}

	@Test
	void testAssignJobWithNullName() {
	    Job job = new Job(null);  // Create a job with a null name
	    JobScheduler scheduler = new JobScheduler();
	    assertFalse(scheduler.addJob(job), "Job with a null name should not be added.");
	}

	@Test
	void testJobLifecycle() {
	    Job job = new Job("Test Job");
	    JobScheduler scheduler = new JobScheduler();

	    // Test that job can be added and status is active
	    scheduler.addJob(job);
	    assertFalse(job.isCompleted(), "Job should not be completed initially.");

	    // Test completing the job
	    job.markAsCompleted();
	    assertTrue(job.isCompleted(), "Job should be marked as completed.");
	}

	

	@Test
	void testStartNonExistentJob() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.startJob("Non-existent Job");
	    assertFalse(scheduler.jobExists("Non-existent Job"), "Non-existent job cannot be started.");
	}


	@Test
	void testAssignWorkerToCompletedJob() {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    job.assignTo("Worker");

	    System.setOut(System.out);  // Reset the output stream

	    assertTrue(outputStream.toString().contains("Cannot reassign a completed job"),
	            "Cannot assign a worker to a completed job.");
	}
	
	@Test
	void testNotifyForJobStartWithNoJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.notifyForJobStart();
	    // Verify that the message "No jobs available to start" is printed.
	}

	@Test
	void testNotifyForJobStartWithCompletedJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Design Homepage");
	    job1.setPriority("High");
	    job1.setRecurrence("Weekly");
	    job1.assignTo("John Doe");
	    job1.markAsCompleted();
	    scheduler.addJob(job1);
	    
	    scheduler.notifyForJobStart();
	    // Verify that completed jobs do not get started.
	}

	@Test
	void testNotifyForJobStartWithActiveJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Design Homepage");
	    job1.setPriority("High");
	    job1.setRecurrence("Weekly");
	    job1.assignTo("John Doe");
	    scheduler.addJob(job1);

	    scheduler.notifyForJobStart();
	    // Verify that active jobs are started and the notification is printed.
	}

	@Test
	void testStartJobCompletedJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Completed Job");
	    job1.markAsCompleted();
	    scheduler.addJob(job1);
	    
	    scheduler.startJob("Completed Job");
	    // Verify that the message "Cannot start a completed job" is printed.
	}

	@Test
	void testStartActiveJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Active Job");
	    scheduler.addJob(job1);
	    
	    scheduler.startJob("Active Job");
	    // Verify that the job is started successfully.
	}


	@Test
	void testListJobsForWorker() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Design Homepage");
	    job1.assignTo("John Doe");
	    scheduler.addJob(job1);
	    
	    scheduler.listJobsForWorker("John Doe");
	    // Verify that the job details are printed for "John Doe".
	}

	@Test
	void testListJobsForWorkerWithNoJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.listJobsForWorker("No Jobs Worker");
	    // Verify that the message "No jobs assigned to worker" is printed.
	}

	

	@Test
	void testRemoveJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Database Migration");
	    scheduler.addJob(job1);
	    
	    scheduler.removeJob("Database Migration");
	    assertFalse(scheduler.jobExists("Database Migration"), "Job should be removed from the schedule.");
	}

	@Test
	void testGetJobPriority() {
	    Job job = new Job("Test Job");
	    job.setPriority("High");
	    assertEquals("High", job.getPriority(), "Priority should be 'High'.");
	}


	@Test
	void testGetRecurrence() {
	    Job job = new Job("Test Job");
	    job.setRecurrence("Weekly");
	    assertEquals("Weekly", job.getRecurrence(), "Recurrence should be 'Weekly'.");
	}
	@Test
	void testGetAssignedTo() {
	    Job job = new Job("Test Job");
	    job.assignTo("Worker1");
	    assertEquals("Worker1", job.getAssignedTo(), "Job should be assigned to Worker1.");
	}

	@Test
	void testRemoveJobWithExistingJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Job to be Removed");
	    scheduler.addJob(job);

	    scheduler.removeJob("Job to be Removed");
	    assertFalse(scheduler.jobExists("Job to be Removed"), "Job should be removed from the schedule.");
	}

	@Test
	void testSetPriorityWhenCompleted() {
	    Job job = new Job("Job1");
	    job.markAsCompleted();
	    job.setPriority("High");
	    assertEquals("Medium", job.getPriority(), "Priority should not be changed after job is completed.");
	}

	@Test
	void testSetPriorityAfterJobCompletion() {
	    Job job = new Job("Test Job");
	    job.markAsCompleted();
	    job.setPriority("Low");
	    assertEquals("Medium", job.getPriority(), "Priority should not change after job is completed.");
	}

	@Test
	void testRemoveCompletedJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Completed Task");
	    job.markAsCompleted();
	    scheduler.addJob(job);

	    scheduler.removeJob("Completed Task");
	    assertFalse(scheduler.jobExists("Completed Task"), "Completed job should be removable.");
	}

	@Test
	void testRemoveJobWhenSchedulerIsEmpty() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.removeJob("NonExistentJob");
	    // Check the appropriate error message is printed for non-existent job removal.
	}

	@Test
	void testPauseJobTwice() {
	    Job job = new Job("Job1");
	    job.pauseJob();  // First time should succeed
	    assertFalse(job.pauseJob(), "Pausing an already paused job should fail.");
	}
	
	@Test
	void testRemoveJobAfterCompletion() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    scheduler.addJob(job);
	    
	    scheduler.removeJob("Completed Job");
	    assertFalse(scheduler.jobExists("Completed Job"), "Completed job should be removed from the scheduler.");
	}
	
	@Test
	void testVisualizeScheduleWhenEmpty() {
	    JobScheduler scheduler = new JobScheduler();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.visualizeSchedule();
	    
	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs scheduled."), "Empty schedule should display correct message.");
	}


	@Test
	void testAddJobWithLongName() {
	    JobScheduler scheduler = new JobScheduler();
	    Job longNameJob = new Job("A very long job name that exceeds typical limits");
	    assertTrue(scheduler.addJob(longNameJob), "Job with a long name should be added.");
	}
	
	@Test
	void testAssignJobWithSpecialCharacterWorker() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Special Character Job");
	    job.assignTo("John$Doe");
	    assertEquals("John$Doe", job.getAssignedTo(), "Worker with special characters should be assigned.");
	}

	@Test
	void testChangeRecurrenceOnCompletedJob() {
	    // Create an output stream to capture the console output
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));  // Redirect System.out to the outputStream

	    // Create and complete the job
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();

	    // Attempt to change recurrence for the completed job
	    job.setRecurrence("Weekly");

	    // Restore System.out
	    System.setOut(System.out);

	    // Verify that the error message is printed when trying to change recurrence of a completed job
	    assertTrue(outputStream.toString().contains("Cannot change recurrence of a completed job"),
	            "Recurrence change on a completed job should fail.");
	}

	@Test
	void testAssignWorkerToJob() {
	    Job job = new Job("Task");
	    job.assignTo("Worker1"); // Valid assignment
	    assertEquals("Worker1", job.getAssignedTo(), "Job should be assigned to Worker1.");
	}

	@Test
	void testSetRecurrenceForCompletedJob() {
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    job.setRecurrence("Weekly"); // Should fail for completed job

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Cannot change recurrence of a completed job"),
	                "Completed jobs should not allow recurrence changes.");
	}

	@Test
	void testJobStartNotificationForActiveJob() {
	    Job job = new Job("Active Job");
	    job.startJob(); // This should be allowed
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    job.startJob(); // Should print notification that job has started

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Notification: Job 'Active Job' has started."),
	                "Active jobs should notify when started.");
	}

	@Test
	void testRemoveJobWithInvalidName() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.addJob(new Job("Valid Job"));

	    scheduler.removeJob("Invalid Job"); // Trying to remove a job that doesn't exist
	    assertFalse(scheduler.jobExists("Invalid Job"), "Job with invalid name should not be removed.");
	}
	
	@Test
	void testAssignWorkerToNullJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = null;
	    boolean result = scheduler.addJob(job);
	    assertFalse(result, "A null job should not be added to the scheduler.");
	}

	@Test
	void testAssignMultipleWorkersToJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Task 1");
	    job.assignTo("Worker1");
	    scheduler.addJob(job);

	    job.assignTo("Worker2"); // Attempt to assign a second worker
	    assertFalse(scheduler.addJob(job), "Job should not be assigned to multiple workers.");
	}

	@Test
	void testInvalidJobStatusChange() {
	    Job job = new Job("Test Job");
	    job.markAsCompleted();
	    
	    // Attempt to pause a completed job
	    assertFalse(job.pauseJob(), "Completed job should not be paused.");
	}
	
	@Test
	void testAddJobAfterCompletion() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Task1");
	    scheduler.addJob(job);
	    job.markAsCompleted();

	    assertFalse(scheduler.addJob(job), "Job should not be added again once it is completed.");
	}

	@Test
	void testChangeRecurrenceForActiveJob() {
	    Job job = new Job("Active Job");
	    job.setRecurrence("Weekly"); // This should succeed
	    assertEquals("Weekly", job.getRecurrence(), "Recurrence should be changed for active jobs.");
	}

	@Test
	void testJobOrderAfterAddition() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Task 1");
	    Job job2 = new Job("Task 2");

	    scheduler.addJob(job1);
	    scheduler.addJob(job2);

	}


	@Test
	void testNotifyJobStartForNewJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("New Job");
	    scheduler.addJob(job);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.notifyForJobStart();

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Notification: Job 'New Job' has started."),
	                "New jobs should trigger a start notification.");
	}

	@Test
	void testVisualizeMultipleJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Job 1");
	    Job job2 = new Job("Job 2");
	    
	    scheduler.addJob(job1);
	    scheduler.addJob(job2);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.visualizeSchedule();

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Job 1"), "Job 1 should be visualized.");
	    assertTrue(outputStream.toString().contains("Job 2"), "Job 2 should be visualized.");
	}

	@Test
	void testInvalidRecurrenceAssignment() {
	    Job job = new Job("Invalid Recurrence Job");
	    job.setRecurrence("Yearly"); // Invalid recurrence value
	    assertEquals("None", job.getRecurrence(), "Invalid recurrence should default to 'None'.");
	}


	@Test
	void testStartPausedJob() {
	    Job job = new Job("Paused Job");
	    job.pauseJob();
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    job.startJob(); // This should fail as the job is paused

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Cannot start a paused job"),
	                "Paused jobs should not be started.");
	}
	@Test
	void testAssignEmptyWorker() {
	    Job job = new Job("Task with Empty Worker");
	    job.assignTo(""); // Empty worker name
	    assertNull(job.getAssignedTo(), "Worker should not be assigned when name is empty.");
	}

	@Test
	void testAddJobWithInvalidName() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("T"); // Invalid name with length less than 2
	    boolean result = scheduler.addJob(job);
	    assertFalse(result, "Job with an invalid name (less than 2 characters) should not be added.");
	}

	@Test
	void testAssignWorkerAfterCompletion() {
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    job.assignTo("New Worker"); // Should fail as the job is completed
	    assertNull(job.getAssignedTo(), "Cannot assign a worker to a completed job.");
	}

	@Test
	void testNotifyForJobStartWhenNoJobsExist() {
	    JobScheduler scheduler = new JobScheduler();
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.notifyForJobStart(); // No jobs should be present

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs available to start"), "No jobs available message should be printed.");
	}

	@Test
	void testJobCompletionNotification() {
	    Job job = new Job("Job Completion Task");
	    job.markAsCompleted();
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    job.startJob(); // This should not start, as the job is completed

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Cannot start a completed job"), "Completed jobs should not trigger start notifications.");
	}

	@Test
	void testWorkerResourceConflict() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Task 1");
	    job1.assignTo("Worker1");
	    scheduler.addJob(job1);

	    Job job2 = new Job("Task 2");
	    job2.assignTo("Worker1"); // Same worker assigned
	    assertFalse(scheduler.addJob(job2), "Worker1 should not be assigned to multiple jobs.");
	}

	@Test
	void testPauseAndResumeJob() {
	    Job job = new Job("Task");
	    
	    assertTrue(job.pauseJob(), "Job should be paused successfully.");
	    assertTrue(job.resumeJob(), "Job should be resumed successfully.");
	}

	@Test
	void testJobStartNotificationForCompletedJob() {
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    job.startJob(); // This should not trigger a notification for a completed job.

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Cannot start a completed job"),
	               "Completed jobs should not trigger job start notifications.");
	}

	@Test
	void testAddJobWithInvalidPriority() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Task with Invalid Priority");
	    job.setPriority("Urgent");  // Invalid priority
	    scheduler.addJob(job);
	    assertEquals("Medium", job.getPriority(), "Invalid priority should default to Medium.");
	}

	@Test
	void testPauseJobThatIsAlreadyPaused() {
	    Job job = new Job("Test Paused Job");
	    job.pauseJob();  // First pause should succeed
	    assertFalse(job.pauseJob(), "Pausing an already paused job should fail.");
	}

	@Test
	void testResumeJobThatIsAlreadyActive() {
	    Job job = new Job("Test Active Job");
	    job.resumeJob();  // Attempting to resume an active job should fail
	    assertFalse(job.resumeJob(), "Resuming an already active job should fail.");
	}

	@Test
	void testReassignWorkerToCompletedJob() {
	    Job job = new Job("Completed Task");
	    job.markAsCompleted();
	    job.assignTo("WorkerB"); // Should fail as the job is completed
	    assertNull(job.getAssignedTo(), "Worker should not be reassigned to a completed job.");
	}

	@Test
	void testCompleteActiveJob() {
	    Job job = new Job("Active Job");
	    assertFalse(job.isCompleted(), "Job should not be completed initially.");
	    job.markAsCompleted();
	    assertTrue(job.isCompleted(), "Job should be marked as completed.");
	}

	@Test
	void testSearchWithEmptyKeyword() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Searchable Job");
	    scheduler.addJob(job);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    scheduler.searchJobsByKeyword("");  // Empty keyword search
	    
	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Search Results for Keyword: ''"), "Empty keyword should be handled.");
	}

	
	@Test
	void testAssignJobWithNullWorker() {
	    Job job = new Job("Test Job");
	    job.assignTo(null);  // Assigning null worker
	    assertNull(job.getAssignedTo(), "Worker should not be assigned to null worker.");
	}

	@Test
	void testAssignWorkerWithSpecialCharacters() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Special Character Job");
	    job.assignTo("John$Doe");  // Assign worker with special characters
	    assertEquals("John$Doe", job.getAssignedTo(), "Worker with special characters should be assigned.");
	}

	@Test
	void testSearchJobByKeyword() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Searchable Job");
	    scheduler.addJob(job);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    scheduler.searchJobsByKeyword("Searchable");
	    
	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Searchable Job"), "Search result should include the job.");
	}

	@Test
	void testStartJobWithNoJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    scheduler.notifyForJobStart();  // No jobs available
	}

	@Test
	void testStartJobWithCompletedStatus() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Completed Task");
	    job.markAsCompleted();
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    scheduler.startJob("Completed Task");
	    System.setOut(System.out);
	    
	    assertTrue(outputStream.toString().contains("Cannot start a completed job"), "Completed jobs cannot be started.");
	}

	@Test
	void testAddJobWithNullWorker() {
	    Job job = new Job("Job with Null Worker");
	    job.assignTo(null);
	    assertNull(job.getAssignedTo(), "Job should not be assigned to a null worker.");
	}

	@Test
	void testJobAssignmentToWorkerWhenAlreadyAssigned() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Job1");
	    Job job2 = new Job("Job2");

	    job1.assignTo("WorkerA");
	    scheduler.addJob(job1);

	    job2.assignTo("WorkerA");
	    boolean result = scheduler.addJob(job2); // Check if this fails when WorkerA is already assigned.
	    
	    assertFalse(result, "WorkerA should not be assigned to multiple jobs.");
	}

	@Test
	void testVisualizeNoJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    scheduler.visualizeSchedule();
	    
	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs scheduled."), "Scheduler should display no jobs if none exist.");
	}


	@Test
	void testRemoveJobWhenJobExists() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Job to be Removed");
	    
	    scheduler.addJob(job);
	    scheduler.removeJob("Job to be Removed");
	    
	    assertFalse(scheduler.jobExists("Job to be Removed"), "Job should be removed.");
	}

	@Test
	void testJobPauseState() {
	    Job job = new Job("Job to Pause");
	    assertTrue(job.pauseJob(), "Job should be paused successfully.");
	    assertFalse(job.pauseJob(), "Job that is already paused should not be paused again.");
	}

	@Test
	void testAssignWorkerAfterJobCompletion() {
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    job.assignTo("Worker1"); // This should fail, as the job is completed
	    assertNull(job.getAssignedTo(), "Worker should not be reassigned to a completed job.");
	}


	@Test
	void testAddJobWithInvalidRecurrence() {
	    Job job = new Job("Invalid Recurrence Job");
	    job.setRecurrence("Yearly");  // Invalid recurrence value
	    assertEquals("None", job.getRecurrence(), "Invalid recurrence should default to 'None'.");
	}


	@Test
	void testSearchJobsWithNoMatches() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Design Homepage");
	    scheduler.addJob(job1);

	    scheduler.searchJobsByKeyword("Backend");  // No matching jobs
	    // Verify the system handles it gracefully
	    String expectedMessage = "No jobs found matching the keyword: 'Backend'.";
	 
	}

	@Test
	void testAddMultipleJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Task 1");
	    Job job2 = new Job("Task 2");
	    job1.assignTo("WorkerA");
	    job2.assignTo("WorkerB");

	    scheduler.addJob(job1);
	    scheduler.addJob(job2);
	    assertTrue(scheduler.jobExists("Task 1"));
	    assertTrue(scheduler.jobExists("Task 2"));
	}

	@Test
	void testChangeRecurrenceOnPausedJob() {
	    Job job = new Job("Paused Job");
	    job.pauseJob();
	    job.setRecurrence("Weekly");  // Should succeed
	    assertEquals("Weekly", job.getRecurrence(), "Recurrence should be set to Weekly.");
	}

	@Test
	void testResumeJobAfterMultiplePauses() {
	    Job job = new Job("Task");
	    job.pauseJob();  // First pause
	    job.resumeJob();  // Resume
	    job.pauseJob();  // Pause again
	    assertTrue(job.resumeJob(), "Job should resume after multiple pauses.");
	}

	@Test
	void testDuplicateJobName() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Job Name");
	    Job job2 = new Job("Job Name");  // Same job name as job1

	    assertTrue(scheduler.addJob(job1));
	    assertFalse(scheduler.addJob(job2), "Job with the same name should not be added.");
	}

	@Test
	void testAddJobWithWorkerConflict() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job1 = new Job("Task 1");
	    job1.assignTo("Worker1");
	    Job job2 = new Job("Task 2");
	    job2.assignTo("Worker1");  // Worker conflict with job1

	    assertTrue(scheduler.addJob(job1));
	    assertFalse(scheduler.addJob(job2), "Worker should not be assigned to two jobs simultaneously.");
	}

	@Test
	void testJobStartInInvalidState() {
	    Job job = new Job("Task 1");
	    job.markAsCompleted();  // Job should be marked as completed
	    job.startJob();  // Should fail, as it's completed
	   
	}

	@Test
	void testRemovePausedJob() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Paused Job");
	    job.pauseJob();  // Pause the job
	    scheduler.addJob(job);

	    scheduler.removeJob("Paused Job");  // Remove paused job
	    assertFalse(scheduler.jobExists("Paused Job"), "Paused job should be removed.");
	}

	@Test
	void testJobStateTransition() {
	    Job job = new Job("Test Job");
	    job.markAsCompleted();
	    job.resumeJob();  // Should fail because job is completed
	    assertFalse(job.resumeJob(), "Cannot resume a completed job.");
	}

	@Test
	void testAssignNullOrEmptyWorker() {
	    Job job = new Job("Test Job");
	    job.assignTo("");  // Should fail, worker cannot be empty
	    assertNull(job.getAssignedTo(), "Job should not be assigned to an empty worker.");
	    
	    job.assignTo(null);  // Should fail, worker cannot be null
	    assertNull(job.getAssignedTo(), "Job should not be assigned to a null worker.");
	}

	
	@Test
	void testStartCompletedJob() {
	    Job job = new Job("Completed Job");
	    job.markAsCompleted();
	    job.startJob();  // Should print error for completed job
	}
	
	
	@Test
	void testInvalidJobStatusTransition() {
	    Job job = new Job("Test Job");
	    job.markAsCompleted();
	    assertFalse(job.pauseJob(), "Completed job should not be paused.");
	}

	@Test
	void testSearchCaseInsensitive() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Case Insensitive Job");
	    scheduler.addJob(job);

	    // Search with different case inputs
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.searchJobsByKeyword("case");  // Lowercase search
	    System.setOut(System.out);
	    
	    assertTrue(outputStream.toString().contains("Case Insensitive Job"), "Search should return correct job regardless of case.");
	}

	@Test
	void testRemoveJobWithNoJobsForWorker() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Job for Non-existent Worker");
	    scheduler.addJob(job);

	    // Remove the job for a worker who has no jobs
	    scheduler.removeJob("NonExistentJob");  // Job doesn't exist
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    scheduler.removeJob("Job for Non-existent Worker");

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Job 'Job for Non-existent Worker' not found in the schedule."), 
	                "Should print error when job is removed from worker without any jobs.");
	}

	@Test
	void testSetRecurrenceWithInvalidValue() {
	    Job job = new Job("Job with Invalid Recurrence");
	    
	    job.setRecurrence("Yearly");  // Invalid recurrence value
	    assertEquals("None", job.getRecurrence(), "Invalid recurrence should default to 'None'.");

	    job.setRecurrence("Weekly");  // Valid recurrence
	    assertEquals("Weekly", job.getRecurrence(), "Recurrence should be Weekly.");
	}

	@Test
	void testWorkerConflictWhenAssigningJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    Job job1 = new Job("First Job");
	    job1.assignTo("Worker A");
	    scheduler.addJob(job1);

	    Job job2 = new Job("Second Job");
	    job2.assignTo("Worker A");  // Conflict, same worker
	    boolean result = scheduler.addJob(job2);

	    assertFalse(result, "Worker 'Worker A' should not be assigned to multiple jobs.");
	}

	@Test
	void testSearchJobsByWorkerAssignment() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    Job job1 = new Job("Job1");
	    job1.assignTo("Worker1");
	    scheduler.addJob(job1);
	    
	    Job job2 = new Job("Job2");
	    job2.assignTo("Worker2");
	    scheduler.addJob(job2);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.searchJobsByKeyword("Worker1");  // Search for jobs assigned to Worker1
	    
	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("Job1"), "Jobs assigned to Worker1 should appear in the search results.");
	    assertFalse(outputStream.toString().contains("Job2"), "Jobs assigned to Worker2 should not appear.");
	}

	@Test
	void testPauseResumeBehavior() {
	    Job job = new Job("Test Job");

	    // Pause and resume job
	    assertTrue(job.pauseJob(), "Job should be paused successfully.");
	    assertFalse(job.pauseJob(), "Job that is already paused should not be paused again.");

	    assertTrue(job.resumeJob(), "Job should be resumed successfully.");
	    assertFalse(job.resumeJob(), "Job that is already active should not be resumed again.");
	}

	@Test
	void testAssignToWorker() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Test Job");
	    job.assignTo("Worker1");
	    assertEquals("Worker1", job.getAssignedTo(), "Job should be assigned to Worker1.");
	}

	@Test
	void testVisualizeEmptyJobSchedule() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.visualizeSchedule();  // Visualize when no jobs are added

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs scheduled."), "Empty job schedule should display correct message.");
	}

	
	@Test
	void testReassignWorkerToInactiveJob() {
	    Job job = new Job("Test Inactive Job");
	    
	    job.pauseJob();  // Pause the job
	    job.assignTo("Worker1");

	    // Try to reassign worker to a paused job
	    assertNull(job.getAssignedTo(), "Worker should not be reassigned to a paused job.");
	    
	    job.markAsCompleted();  // Complete the job
	    job.assignTo("Worker2");

	    assertNull(job.getAssignedTo(), "Worker should not be reassigned to a completed job.");
	}

	@Test
	void testJobLifecycleTransition() {
	    Job job = new Job("Test Job Lifecycle");

	    // Job should be active initially
	    assertFalse(job.isCompleted(), "Job should be active initially.");
	    
	    // Mark the job as completed
	    job.markAsCompleted();
	    assertTrue(job.isCompleted(), "Job should be marked as completed.");

	    // Attempting to change priority should fail now
	    job.setPriority("High");
	    assertEquals("Medium", job.getPriority(), "Priority should remain 'Medium' after completion.");
	}

	@Test
	void testAddJobWithInvalidWorker() {
	    JobScheduler scheduler = new JobScheduler();
	    Job job = new Job("Job with Invalid Worker");

	    job.assignTo("");  // Invalid empty worker
	    assertNull(job.getAssignedTo(), "Job should not be assigned to an empty worker.");

	    job.assignTo(null);  // Invalid null worker
	    assertNull(job.getAssignedTo(), "Job should not be assigned to a null worker.");
	}

	@Test
	void testSearchNoJobFound() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    Job job1 = new Job("Design Homepage");
	    scheduler.addJob(job1);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));
	    
	    scheduler.searchJobsByKeyword("NonExistentJob");  // Search for a job that doesn't exist
	    
	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs found matching the keyword: 'NonExistentJob'"), 
	                "Search should return 'No jobs found' for a keyword with no matches.");
	}

	@Test
	void testWorkerConflictAfterCompletion() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    Job job1 = new Job("Design Homepage");
	    job1.assignTo("Worker A");
	    scheduler.addJob(job1);
	    
	    // Mark job1 as completed
	    job1.markAsCompleted();

	    Job job2 = new Job("Backend Development");
	    job2.assignTo("Worker A");  // Same worker
	    boolean result = scheduler.addJob(job2);
	    
	    assertTrue(result, "Worker should be able to be reassigned after job completion.");
	}

	@Test
	void testWorkerConflictWithPausedJobs() {
	    JobScheduler scheduler = new JobScheduler();
	    
	    Job job1 = new Job("Backend Development");
	    job1.assignTo("Worker A");
	    scheduler.addJob(job1);
	    
	    // Pause job1
	    job1.pauseJob();
	    
	    Job job2 = new Job("UI Design");
	    job2.assignTo("Worker A");  // Same worker
	    boolean result = scheduler.addJob(job2);
	    
	    assertFalse(result, "Worker 'Worker A' should not be reassigned to multiple jobs when one is paused.");
	}

	@Test
	void testNoJobsStartNotification() {
	    JobScheduler scheduler = new JobScheduler();

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStream));

	    scheduler.notifyForJobStart();  // No jobs in scheduler

	    System.setOut(System.out);
	    assertTrue(outputStream.toString().contains("No jobs available to start."), 
	                "The system should notify when there are no jobs to start.");
	}

	

	

}
