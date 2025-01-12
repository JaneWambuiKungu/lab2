	package test.whitebox;
	
	import static org.junit.jupiter.api.Assertions.*;
	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	
	
	
	class WhiteBoxJobSchedulerTest {
	
		public JobScheduler scheduler;
	
	    @BeforeEach
	    public void setUp() {
	        scheduler = new JobScheduler();
	    }
	
	    @Test
	    public void testAddJob() {
	        String jobId = "job123";
	        scheduler.addJob(jobId);
	
	        assertEquals(1, scheduler.getActiveJobs().size(), "Job count should be 1 after adding a job.");
	        assertEquals(jobId, scheduler.getActiveJobs().get(0).getJobId(), "Job ID should match the added job.");
	        assertTrue(scheduler.getLogs().containsKey(jobId), "Log should contain entry for added job.");
	    }
	
	    @Test
	    public void testValidateInputsForDependencies() {
	        Job jobA = new Job("jobA");
	        jobA.addDependency("nonexistentJob");
	        scheduler.getActiveJobs().add(jobA);
	
	        scheduler.validateInputs();
	
	        // No direct assertion for output.
	    }
	
	    @Test
	    public void testCancelAllJobs() {
	        scheduler.addJob("job1");
	        scheduler.addJob("job2");
	
	        scheduler.cancelAllJobs();
	
	        assertEquals(0, scheduler.getActiveJobs().size(), "All jobs should be cancelled and activeJobs should be empty.");
	    }
	
	    @Test
	    public void testSetJobDependencies() {
	        scheduler.addJob("jobB");
	        Job jobB = scheduler.getActiveJobs().get(0);
	
	        jobB.addDependency("jobC");
	
	        assertTrue(jobB.getDependencies().contains("jobC"), "Dependency should be set correctly.");
	    }
	
	    @Test
	    public void testExportSchedule() throws IOException {
	        scheduler.addJob("job1");
	        scheduler.addJob("job2");
	        scheduler.exportSchedule();
	
	        try (BufferedReader reader = new BufferedReader(new FileReader("JobScheduleReport.txt"))) {
	            String content = reader.lines().reduce("", (acc, line) -> acc + line + "\n");
	            assertTrue(content.contains("Active Jobs"), "Exported file should contain 'Active Jobs' header.");
	            assertTrue(content.contains("job1"), "Exported file should contain job1 details.");
	            assertTrue(content.contains("job2"), "Exported file should contain job2 details.");
	        }
	    }
	
	}
