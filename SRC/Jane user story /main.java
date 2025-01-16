
package ischedule;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create Jobs
        List<Job> jobs = JobCreator.createJobs();
        System.out.println("Initial Job List:");
        JobViewer.viewJobs(jobs);

        // 2. Validate Jobs
        System.out.println("\nValidating Jobs:");
        for (Job job : jobs) {
            if (!JobValidator.validateJob(job)) {
                System.out.println("Invalid job: " + job);
            }
        }

        // 3. Sort Jobs by Duration
        System.out.println("\nJobs Sorted by Duration:");
        JobSorter.sortJobsByDuration(jobs);
        JobViewer.viewJobs(jobs);

        // 4. Schedule Jobs
        System.out.println("\nJob Schedule:");
        JobScheduler.scheduleJobs(jobs);

        // 5. Filter Jobs with Duration >= 10
        System.out.println("\nFiltered Jobs (Duration >= 10):");
        List<Job> filteredJobs = JobFilter.filterJobsByDuration(jobs, 10);
        JobViewer.viewJobs(filteredJobs);

        // 6. Delete a Job
        System.out.println("\nJobs after Deletion:");
        JobDeletor.deleteJob(jobs, "2");
        JobViewer.viewJobs(jobs);

        // 7. Update a Job
        System.out.println("\nUpdating Job with ID '1':");
        JobUpdater.updateJob(jobs, "1", "Redesign", 6);
        JobViewer.viewJobs(jobs);

        // 8. Validate and Add a New Job
        System.out.println("\nAdding a New Job:");
        Job newJob = new Job("4", "Documentation", 3);
        if (JobEntryValidator.validateNewJob(newJob)) {
            jobs.add(newJob);
        } else {
            System.out.println("Failed to add invalid job: " + newJob);
        }
        JobViewer.viewJobs(jobs);
        // 9. Edit an Existing Job
        System.out.println("\nEditing Job with ID '3':");
        JobEditor.editJob(jobs, "3", "Final Testing", 9);
        JobViewer.viewJobs(jobs);

        // 10. Job Summary
        System.out.println("\nJob Summary:");
        JobSummary.printJobSummary(jobs);
    }
}
package ischedule;


public class Job {
    private String jobId;
    private String jobName;
    private int jobDuration; // in hours
    
    public Job(String jobId, String jobName, int jobDuration) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDuration = jobDuration;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobDuration() {
        return jobDuration;
    }

    @Override
    public String toString() {
        return "Job[ID=" + jobId + ", Name=" + jobName + ", Duration=" + jobDuration + " hours]";
    }
}
package ischedule;
import java.util.ArrayList;
import java.util.List;

public class JobCreator {
    public static List<Job> createJobs() {
        List<Job> jobs = new ArrayList<>();
        // Sample job creation
        jobs.add(new Job("1", "Design", 5));
        jobs.add(new Job("2", "Development", 15));
        jobs.add(new Job("3", "Testing", 8));
        return jobs;
    }
    public static Job createJob(String jobId, String jobName, int jobDuration) {
        return new Job(jobId, jobName, jobDuration);
    }
}
package ischedule;
import java.util.List;
public class JobDeletor {
    public static void deleteJob(List<Job> jobs, String jobId) {
        jobs.removeIf(job -> job.getJobId().equals(jobId));
    }
}
package ischedule;

import java.util.List;

public class JobEditor {
    public static void editJob(List<Job> jobs, String jobId, String newJobName, int newJobDuration) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getJobId().equals(jobId)) {
                // Create a new job object with the updated details and replace the old one
                jobs.set(i, new Job(jobId, newJobName, newJobDuration));
                break;
            }
        }
    }
}
package ischedule;

public class JobEntryValidator {
    public static boolean validateNewJob(Job job) {
        if (job == null) {
            return false;
        }
        if (job.getJobName() == null || job.getJobName().trim().isEmpty()) {
            return false;
        }
        if (job.getJobDuration() <= 0) {
            return false;
        }
        return true;
    }
}
package ischedule;
import java.util.List;
import java.util.stream.Collectors;

public class JobFilter {
    public static List<Job> filterJobsByDuration(List<Job> jobs, int minDuration) {
        return jobs.stream()
                .filter(job -> job.getJobDuration() >= minDuration)
                .collect(Collectors.toList());
    }

    public static List<Job> filterJobsByName(List<Job> jobs, String nameSubstring) {
        return jobs.stream()
                .filter(job -> job.getJobName().contains(nameSubstring))
                .collect(Collectors.toList());
    }
}
package ischedule;
import java.util.List;

public class JobScheduler {
    public static void scheduleJobs(List<Job> jobs) {
        // Simple FIFO scheduling
        System.out.println("Job Schedule:");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

public static void scheduleJobsByPriority(List<Job> jobs) {
    // Placeholder for a priority-based scheduler (could be extended further)
    jobs.sort((job1, job2) -> Integer.compare(job1.getJobDuration(), job2.getJobDuration()));
    System.out.println("Job Schedule (Priority by Duration):");
    for (Job job : jobs) {
        System.out.println(job);
    }
}
}
package ischedule;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobSorter {
    public static void sortJobsByDuration(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparingInt(Job::getJobDuration));
    }

    public static void sortJobsByName(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparing(Job::getJobName));
    }
}
package ischedule;

import java.util.List;

public class JobSummary {
    public static void printJobSummary(List<Job> jobs) {
        int totalDuration = jobs.stream().mapToInt(Job::getJobDuration).sum();
        System.out.println("Total number of jobs: " + jobs.size());
        System.out.println("Total duration of all jobs: " + totalDuration + " hours");
        jobs.forEach(job -> System.out.println(job));
    }
}
package ischedule;

import java.util.List;

//In JobUpdater.java
public class JobUpdater {
 public static void updateJob(List<Job> jobs, String jobId, String newJobName, int newJobDuration) {
     for (int i = 0; i < jobs.size(); i++) {
         if (jobs.get(i).getJobId().equals(jobId)) {
             // Create a new job with the updated details and replace the old job
             jobs.set(i, new Job(jobId, newJobName, newJobDuration));
             break;
         }
     }
 }
}
package ischedule;

import java.util.List;

public class JobValidator {
    public static boolean validateJob(Job job) {
        // Validate if job duration is greater than 0
        return job.getJobDuration() > 0;
    }

    public static boolean validateJobName(String jobName, List<Job> jobs) {
        // Validate if the job name is unique
        for (Job job : jobs) {
            if (job.getJobName().equalsIgnoreCase(jobName)) {
                return false;
            }
        }
        return true;
    }
}
package ischedule;

import java.util.List;

public class JobViewer {
    public static void viewJobs(List<Job> jobs) {
        System.out.println("Viewing all jobs:");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    public static void viewJobDetails(List<Job> jobs, String jobId) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                System.out.println("Job details: " + job);
                return;
            }
        }
        System.out.println("Job with ID " + jobId + " not found.");
    }
}
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
