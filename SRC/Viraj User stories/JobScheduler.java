package jobscheduling;

import java.util.ArrayList;
import java.util.List;

// Class to handle all job-related operations
public class JobScheduler {
    private List<Job> jobs; // List to store all jobs
    private int idCounter; // Counter to generate unique job IDs
    public List<Job> Jobs() {
        return jobs; // `jobs` should be your list of Job objects
    }

    // Constructor to initialise the scheduler
    public JobScheduler() {
        this.jobs = new ArrayList<>();
        this.idCounter = 100; // Start IDs from 100 for better readability
    }

    // Adds a new job to the scheduler
    public void addJob(Job job) {
        jobs.add(job);
        System.out.println("Job added successfully: " + job.getJobName());
    }

    // Finalizes a job, preventing further changes
    public void finalizeJob(String jobId) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                if (job.isFinalized()) {
                    System.out.println("Error: Job is already finalized.");
                    return;
                }
                job.setFinalized(true);
                System.out.println("Job has been finalized: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: No job found with ID " + jobId);
    }

    // Assigns or updates tags for a job
    public void assignTags(String jobId, List<String> tags) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                if (job.isFinalized()) {
                    System.out.println("Error: Finalized jobs cannot have tags modified.");
                    return;
                }
                job.setTags(tags);
                System.out.println("Tags successfully assigned to job: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: Job with ID " + jobId + " not found.");
    }

    // Filters and displays jobs by a specific tag
    public void filterJobsByTags(String tag) {
        System.out.println("\nJobs with the tag '" + tag + "':");
        for (Job job : jobs) {
            if (job.getTags().contains(tag)) {
                System.out.println(job);
            }
        }
    }

    // Updates the visibility of a job based on the specified role
    public void adjustVisibility(String jobId, String role) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                job.setVisibleToRole(role);
                System.out.println("Visibility updated for job: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: Job with ID " + jobId + " not found.");
    }

    // Generates a report summarizing job statuses
    public void generateSummaryReport() {
        System.out.println("\nSummary Report:");
        int finalizedJobs = 0;
        for (Job job : jobs) {
            if (job.isFinalized()) {
                finalizedJobs++;
            }
        }
        System.out.println("Total Jobs: " + jobs.size());
        System.out.println("Finalized Jobs: " + finalizedJobs);
        System.out.println("Pending Jobs: " + (jobs.size() - finalizedJobs));
    }

    // Generates a unique ID for a job based on the user's role
    public String generateJobId(String role) {
        String rolePrefix; // Prefix based on role
        switch (role.toLowerCase()) {
            case "admin":
                rolePrefix = "ADM";
                break;
            case "manager":
                rolePrefix = "MGR";
                break;
            case "developer":
                rolePrefix = "DEV";
                break;
            default:
                rolePrefix = "USR"; // Default prefix for other roles
        }
        return rolePrefix + "-" + (idCounter++);
    }

    // Sets a deadline alert for a specific job
    public void setDeadlineAlert(String jobId, String alertMessage) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                job.setDeadlineAlert(alertMessage);
                System.out.println("Deadline alert set for job: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: Job with ID " + jobId + " not found.");
    }

    // Logs user actions for tracking purposes
    public void logAction(String action) {
        System.out.println("Action logged: " + action);
    }

    // Lists all jobs in the scheduler
    public void listJobs() {
        for (Job job : jobs) {
            System.out.println(job);
        }
    }
}
