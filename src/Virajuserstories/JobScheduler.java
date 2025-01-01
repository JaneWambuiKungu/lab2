package jobscheduling;

import java.util.ArrayList;
import java.util.List;

public class JobScheduler {
    private List<Job> jobs;

    public JobScheduler() {
        this.jobs = new ArrayList<>();
    }

    // Add a job
    public void addJob(Job job) {
        jobs.add(job);
        System.out.println("Job added: " + job.getJobName());
    }

    // Mark a job as completed
    public void markJobAsCompleted(String jobId) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                job.setCompleted(true);
                System.out.println("Job marked as completed: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: Job with ID " + jobId + " not found.");
    }

    // Assign tags to a job
    public void assignTags(String jobId, List<String> tags) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                job.setTags(tags);
                System.out.println("Tags assigned to job: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: Job with ID " + jobId + " not found.");
    }

    // Filter jobs by tags
    public void filterJobsByTags(String tag) {
        for (Job job : jobs) {
            if (job.getTags().contains(tag)) {
                System.out.println(job);
            }
        }
    }

    // Set visibility by role
    public void adjustVisibility(String jobId, String role) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                job.setVisibleToRole(role);
                System.out.println("Visibility adjusted for job: " + job.getJobName());
                return;
            }
        }
        System.out.println("Error: Job with ID " + jobId + " not found.");
    }

    // Generate a summary report
    public void generateSummaryReport() {
        System.out.println("\nSummary Report:");
        int completedJobs = 0;
        for (Job job : jobs) {
            if (job.isCompleted()) {
                completedJobs++;
            }
        }
        System.out.println("Total Jobs: " + jobs.size());
        System.out.println("Completed Jobs: " + completedJobs);
        System.out.println("Pending Jobs: " + (jobs.size() - completedJobs));
    }

    // Set alerts for deadlines
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

    // List all jobs
    public void listJobs() {
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

	public void logAction(String string) {
		// TODO Auto-generated method stub
		
	}

	public String generateJobId() {
		// TODO Auto-generated method stub
		return null;
	}
}
