package test.whitebox;

import jobscheduler.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory2_CreateRecurringJobsWhiteBoxTest {

    @Test
    void testValidRecurrenceAssignment() {
        // Create a new job
        Job job = new Job("Data Backup");

        // Assign valid recurrence values
        job.setRecurrence("Daily");
        assertEquals("Daily", job.getRecurrence(), "Recurrence should be set to Daily.");

        job.setRecurrence("Weekly");
        assertEquals("Weekly", job.getRecurrence(), "Recurrence should be set to Weekly.");

        job.setRecurrence("Monthly");
        assertEquals("Monthly", job.getRecurrence(), "Recurrence should be set to Monthly.");

        job.setRecurrence("None");
        assertEquals("None", job.getRecurrence(), "Recurrence should be set to None.");
    }

    @Test
    void testInvalidRecurrenceAssignment() {
        // Create a new job
        Job job = new Job("Log Analysis");

        // Assign invalid recurrence values
        job.setRecurrence("Yearly");  // Invalid
        assertNotEquals("Yearly", job.getRecurrence(), "Invalid value 'Yearly' should not be accepted.");

        job.setRecurrence("Hourly");  // Invalid
        assertNotEquals("Hourly", job.getRecurrence(), "Invalid value 'Hourly' should not be accepted.");
    }
}
