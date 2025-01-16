package test.whitebox;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;      
import java.util.ArrayList;

import ischedule.*;

public class JobWhiteBoxTest {

    private List<Job> jobs;

    @Before
    public void setUp() {
        jobs = new ArrayList<>();
        jobs.add(new Job("1", "Design", 5));
        jobs.add(new Job("2", "Development", 15));
        jobs.add(new Job("3", "Testing", 8));
    }

 
    
    

    /**
     * Statement/Branch coverage for JobEditor.editJob().
     * - We edit an existing job (true path) and a non-existing job (false path).
     */
    @Test
    public void testJobEditorBranchCoverage() {
        // Edit job "1"
        JobEditor.editJob(jobs, "1", "Redesign", 6);
        assertEquals("Redesign", jobs.get(0).getJobName());
        assertEquals(6, jobs.get(0).getJobDuration());

        
        JobEditor.editJob(jobs, "99", "NoChange", 10);
        // No changes expected for jobs
        assertEquals("Development", jobs.get(1).getJobName());
        assertEquals("Testing", jobs.get(2).getJobName());
    }

    /**
     * Statement coverage for JobFilter.filterJobsByDuration()
     * - We pass a threshold that includes some jobs and excludes others to cover the filter loop.
     */
    @Test
    public void testJobFilterCoverage() {
        List<Job> filtered = JobFilter.filterJobsByDuration(jobs, 10);
        // Should only include the "Development" (duration=15)
        assertEquals(1, filtered.size());
        assertEquals("Development", filtered.get(0).getJobName());

        // Another threshold that includes "Testing" as well
        filtered = JobFilter.filterJobsByDuration(jobs, 8);
        assertEquals("Should include any job >= 8 hours", 2, filtered.size());
    }

    /**
     * Branch coverage for scheduleJobsByPriority (sort by duration ascending).
     * - We verify that sorting logic is triggered and covers the compare (job1 vs job2).
     */
    @Test
    public void testScheduleJobsByPriorityBranchCoverage() {
        // Add a job with same duration as an existing job
        jobs.add(new Job("4", "Doc", 8));

        JobScheduler.scheduleJobsByPriority(jobs);
        // Should be sorted by duration ascending: [5, 8, 8, 15]
        assertEquals(5, jobs.get(0).getJobDuration());
        // The next two are 8, we won't assert order among equals but let's check durations
        assertEquals(8, jobs.get(1).getJobDuration());
        assertEquals(8, jobs.get(2).getJobDuration());
        assertEquals(15, jobs.get(3).getJobDuration());
    }

    /**
     * Statement coverage for JobUpdater.updateJob()
     * - Test updating existing job vs. non-existent job ID.
     */
    @Test
    public void testJobUpdaterCoverage() {
        // Update existing job "3"
        JobUpdater.updateJob(jobs, "3", "NewTest", 9);
        assertEquals("NewTest", jobs.get(2).getJobName());
        assertEquals(9, jobs.get(2).getJobDuration());

        // ID doesn't exist
        JobUpdater.updateJob(jobs, "99", "NoChange", 12);
        // Should remain 3 jobs, no changes to existing
        assertEquals(3, jobs.size());
    }

    /**
     * Statement/Branch coverage for JobEntryValidator.validateNewJob()
     * - Covers checks: null job, empty name, duration <=0, etc.
     */
    @Test
    public void testJobEntryValidatorCoverage() {
        // 1. Null job
        assertFalse("Null job should be invalid", JobEntryValidator.validateNewJob(null));

        // 2. Empty name
        Job emptyName = new Job("100", "   ", 10);
        assertFalse("Empty job name should be invalid", JobEntryValidator.validateNewJob(emptyName));

        // 3. Zero or negative duration
        Job zeroDuration = new Job("101", "ZeroDurationJob", 0);
        assertFalse("Zero duration job should be invalid", JobEntryValidator.validateNewJob(zeroDuration));

        // 4. Valid new job
        Job validJob = new Job("102", "ValidNewJob", 5);
        assertTrue("Valid job should pass", JobEntryValidator.validateNewJob(validJob));
    }

    /**
     * Statement coverage for JobSummary.printJobSummary()
     * - We want to make sure the summing statement is executed
     *   and no exceptions are thrown.
     */
    @Test
    public void testJobSummaryCoverage() {
        try {
            JobSummary.printJobSummary(jobs);
        } catch (Exception e) {
            fail("printJobSummary threw an exception: " + e.getMessage());
        }
    }

    /**
     * Statement coverage for JobViewer.viewJobs() and viewJobDetails().
     * - We just check that it doesn't crash with valid and invalid IDs (branch coverage).
     */
    @Test
    public void testJobViewerCoverage() {
        try {
            JobViewer.viewJobs(jobs);
            // View details for valid ID
            JobViewer.viewJobDetails(jobs, "1");
            // View details for invalid ID (branch where job not found)
            JobViewer.viewJobDetails(jobs, "99");
        } catch (Exception e) {
            fail("JobViewer methods threw an exception: " + e.getMessage());
        }
    }
}
