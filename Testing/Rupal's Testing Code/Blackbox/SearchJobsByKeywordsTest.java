package test.blackbox;

import jobscheduler.Job;
import jobscheduler.JobScheduler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory9_SearchJobsByKeywordTest {

    @Test
    void testSearchJobsByExactKeyword() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create and add jobs
        Job job1 = new Job("Design Homepage");
        job1.assignTo("John Doe");
        scheduler.addJob(job1);

        Job job2 = new Job("Backend Development");
        job2.assignTo("Jane Smith");
        scheduler.addJob(job2);

        // Search by exact keyword
        scheduler.searchJobsByKeyword("Design");

        // Restore console output
        System.setOut(System.out);

        // Verify the search result
        assertTrue(outputStream.toString().contains("Design Homepage"),
                "Search should return the job with 'Design' in the name.");
    }

    @Test
    void testSearchJobsByPartialKeyword() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create and add jobs
        Job job1 = new Job("Database Migration");
        job1.assignTo("Alice");
        scheduler.addJob(job1);

        Job job2 = new Job("Server Maintenance");
        job2.assignTo("Bob");
        scheduler.addJob(job2);

        // Search by partial keyword
        scheduler.searchJobsByKeyword("Server");

        // Restore console output
        System.setOut(System.out);

        // Verify the search result
        assertTrue(outputStream.toString().contains("Server Maintenance"),
                "Search should return the job with 'Server' in the name.");
    }

    @Test
    void testSearchJobsWithNoMatches() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create and add jobs
        Job job1 = new Job("UI Design");
        scheduler.addJob(job1);

        Job job2 = new Job("API Integration");
        scheduler.addJob(job2);

        // Search with a keyword that doesn't exist
        scheduler.searchJobsByKeyword("Testing");

        // Restore console output
        System.setOut(System.out);

        // Verify no jobs are found
        String expectedMessage = "No jobs found matching the keyword: 'Testing'.";
        assertTrue(outputStream.toString().contains(expectedMessage),
                "Search should display a message when no jobs match.");
    }

    @Test
    void testSearchIsCaseInsensitive() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create and add jobs
        Job job1 = new Job("Frontend Development");
        scheduler.addJob(job1);

        // Search using lowercase keyword
        scheduler.searchJobsByKeyword("frontend");

        // Restore console output
        System.setOut(System.out);

        // Verify search is case-insensitive
        assertTrue(outputStream.toString().contains("Frontend Development"),
                "Search should be case-insensitive.");
    }
}
