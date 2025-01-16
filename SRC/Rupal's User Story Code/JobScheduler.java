package jobscheduler;

import java.util.ArrayList;
import java.util.List;

public class JobScheduler {
    private List<Job> jobs; // List to store jobs

    // Constructor
    public JobScheduler() {
        this.jobs = new ArrayList<>();
    }

    // Method to check if a worker/resource is free
    private boolean isWorkerFree(String workerOrResource) {
        for (Job job : jobs) {
            if (job.getAssignedTo() != null && job.getAssignedTo().equals(workerOrResource)) {
                return false; // Worker/resource is already assigned to another job
            }
        }
        return true; // Worker/resource is free
    }

    // Method to check if a worker/resource is already assigned
    private boolean isWorkerAssigned(String workerOrResource) {
        for (Job job : jobs) {
            if (workerOrResource.equals(job.getAssignedTo())) {
                return true; // Worker is already assigned
            }
        }
        return false; // Worker is free
    }

    
    
 // Method to visualize the job schedule
    public void visualizeSchedule() {
        System.out.println("\n=== Job Schedule ===");
        if (jobs.isEmpty()) {
            System.out.println("No jobs scheduled.");
        } else {
            for (Job job : jobs) {
                System.out.println(job.getJobDetailsAsString());
            }
        }
    }
      
    
    
 // In JobScheduler.java
    public boolean addJob(Job job) {
        if (job == null || job.getJobName() == null || job.getJobName().trim().isEmpty()) {
            System.out.println("Error: Cannot add a job with an empty or whitespace-only name.");
            return false;
        }

        if (jobExists(job.getJobName())) {
            System.out.println("Error: Job '" + job.getJobName() + "' already exists.");
            return false;
        }

        if (job.getAssignedTo() != null && isWorkerAssigned(job.getAssignedTo())) {
            System.out.println("Error: Worker/Resource '" + job.getAssignedTo() + "' is already assigned.");
            return false;
        }

        jobs.add(job);
        System.out.println("Job added: " + job.getJobDetailsAsString());
        return true;
    }




    
    // Method to search for jobs by keyword
    public void searchJobsByKeyword(String keyword) {
        System.out.println("\n=== Search Results for Keyword: '" + keyword + "' ===");
        boolean found = false;
        for (Job job : jobs) {
            if (job.getJobDetailsAsString().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(job.getJobDetailsAsString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No jobs found matching the keyword: '" + keyword + "'.");
        }
    }

    // Method to notify and start jobs
    public void notifyForJobStart() {
        System.out.println("\n=== Job Start Notifications ===");
        if (jobs.isEmpty()) {
            System.out.println("No jobs available to start.");
            return;
        }
        for (Job job : jobs) {
            if (!job.isCompleted()) {
                job.startJob(); // Notify for each job
            } else {
                System.out.println("Cannot start a completed job: '" + job.getJobName() + "'.");
            }
        }
    }


    // Method to start a specific job
    public void startJob(String jobName) {
        for (Job job : jobs) {
            if (job.getJobName().equals(jobName)) {
                if (job.isCompleted()) {
                    System.out.println("Cannot start a completed job: '" + jobName + "'.");
                } else {
                    job.startJob();
                }
                return;
            }
        }
        System.out.println("Job '" + jobName + "' not found in the schedule.");
    }

    // Method to check if a specific job exists in the schedule
    public boolean jobExists(String jobName) {
        for (Job job : jobs) {
            if (job.getJobName().equals(jobName)) {
                return true; // Job exists
            }
        }
        return false; // Job not found
    }

    // Method to mark a job as completed
    public void markJobAsCompleted(String jobName) {
        for (Job job : jobs) {
            if (job.getJobName().equals(jobName)) {
                job.markAsCompleted();
                return;
            }
        }
        System.out.println("Job '" + jobName + "' not found in the schedule.");
    }

    // Method to list all jobs assigned to a specific worker/resource
    public void listJobsForWorker(String worker) {
        System.out.println("\n=== Jobs Assigned to Worker: '" + worker + "' ===");
        boolean found = false;
        for (Job job : jobs) {
            if (worker.equals(job.getAssignedTo())) {
                System.out.println(job.getJobDetailsAsString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No jobs assigned to worker: '" + worker + "'.");
        }
    }

    // Method to remove a job from the schedule
    public void removeJob(String jobName) {
        for (Job job : jobs) {
            if (job.getJobName().equals(jobName)) {
                jobs.remove(job);
                System.out.println("Job '" + jobName + "' removed from the schedule.");
                return;
            }
        }
        System.out.println("Job '" + jobName + "' not found in the schedule.");
    }
}
