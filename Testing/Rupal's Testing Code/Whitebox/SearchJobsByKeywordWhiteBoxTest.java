package test.whitebox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserStory9_SearchJobsByKeywordWhiteBoxTest {

    @Test
    void testSearchJobByExactKeyword() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler and add jobs
        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("Website Development");
        Job job2 = new Job("Database Backup");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        // Search for a job by exact keyword
        scheduler.searchJobsByKeyword("Website");

        // Restore console output
        System.setOut(System.out);

        // Verify search result
        String expectedOutput = "Job Name: Website Development";
        assertTrue(outputStream.toString().contains(expectedOutput),
                "The search should return the job containing 'Website'.");
    }

    @Test
    void testSearchJobByPartialKeyword() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler and add jobs
        JobScheduler scheduler = new JobScheduler();
        Job job1 = new Job("Mobile App Design");
        Job job2 = new Job("Server Maintenance");

        scheduler.addJob(job1);
        scheduler.addJob(job2);

        // Search using a partial keyword
        scheduler.searchJobsByKeyword("Design");

        // Restore console output
        System.setOut(System.out);

        // Verify search result
        String expectedOutput = "Job Name: Mobile App Design";
        assertTrue(outputStream.toString().contains(expectedOutput),
                "The search should return the job containing 'Design'.");
    }

    @Test
    void testSearchJobWithNoMatch() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler and add jobs
        JobScheduler scheduler = new JobScheduler();
        scheduler.addJob(new Job("Security Audit"));
        scheduler.addJob(new Job("Data Migration"));

        // Search with a keyword that has no match
        scheduler.searchJobsByKeyword("Marketing");

        // Restore console output
        System.setOut(System.out);

        // Verify no jobs were found
        String expectedOutput = "No jobs found matching the keyword: 'Marketing'.";
        assertTrue(outputStream.toString().contains(expectedOutput),
                "The search should indicate no matches for 'Marketing'.");
    }
}
