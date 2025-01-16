
package jobscheduler;

import java.io.Serializable;

public class Job implements Serializable {
    private String jobName;
    private String priority; // High, Medium, Low
    private String recurrence; // Recurrence frequency (e.g., Daily, Weekly, Monthly)
    private String assignedTo; // Worker or resource to whom the job is assigned
    private boolean isPaused; // Tracks if the job is paused
    private boolean isCompleted; // Tracks if the job is completed

    // Constructor
    public Job(String jobName) {
        this.jobName = jobName;
        this.priority = "Medium"; // Default priority
        this.recurrence = "None"; // Default recurrence
        this.assignedTo = null; // Default assignedTo (Not Assigned)
        this.isPaused = false; // Default status is active
        this.isCompleted = false; // Default status is not completed
    }

    // Method to check if the job is completed
    public boolean isCompleted() {
        return this.isCompleted;
    }

    // Method to assign a priority to the job
    public void setPriority(String priority) {
        if (isCompleted) {
            System.out.println("Cannot change priority of a completed job: '" + jobName + "'.");
            return;
        }
        if (priority.equalsIgnoreCase("High") || 
            priority.equalsIgnoreCase("Medium") || 
            priority.equalsIgnoreCase("Low")) {
            this.priority = priority;
            System.out.println("Priority set to " + priority + " for job: " + jobName);
        } else {
            System.out.println("Invalid priority! Please choose High, Medium, or Low.");
        }
    }

    // Method to retrieve the priority of the job
    public String getPriority() {
        return this.priority;
    }

    // Method to set recurrence for the job
    public void setRecurrence(String recurrence) {
        if (isCompleted) {
            System.out.println("Cannot change recurrence of a completed job: '" + jobName + "'.");
            return;
        }
        if (recurrence.equalsIgnoreCase("Daily") || 
            recurrence.equalsIgnoreCase("Weekly") || 
            recurrence.equalsIgnoreCase("Monthly") || 
            recurrence.equalsIgnoreCase("None")) {
            this.recurrence = recurrence;
            System.out.println("Recurrence set to " + recurrence + " for job: " + jobName);
        } else {
            System.out.println("Invalid recurrence! Please choose Daily, Weekly, Monthly, or None.");
        }
    }

    // Method to retrieve recurrence of the job
    public String getRecurrence() {
        return this.recurrence;
    }

    // Method to assign the job to a worker/resource
    public void assignTo(String workerOrResource) {
        if (workerOrResource == null || workerOrResource.trim().isEmpty()) {
            System.out.println("Error: Worker/Resource cannot be null or empty.");
            return;
        }
        if (isCompleted) {
            System.out.println("Cannot reassign a completed job: '" + jobName + "'.");
            return;
        }
        this.assignedTo = workerOrResource;
        System.out.println("Job: " + jobName + " assigned to: " + workerOrResource);
    }


    // Method to retrieve the worker/resource the job is assigned to
    public String getAssignedTo() {
        return this.assignedTo;
    }

 // UPDATED pauseJob() METHOD
    public boolean pauseJob() {
        if (isCompleted) {
            System.out.println("Cannot pause a completed job: '" + jobName + "'.");
            return false;
        }
        if (!isPaused) {
            isPaused = true;
            System.out.println("Job '" + jobName + "' is now paused.");
            return true;
        } else {
            System.out.println("Job '" + jobName + "' is already paused.");
            return false;
        }
    }

    
 
 // UPDATED resumeJob() METHOD
    public boolean resumeJob() {
        if (isCompleted) {
            System.out.println("Cannot resume a completed job: '" + jobName + "'.");
            return false;
        }
        if (isPaused) {
            isPaused = false;
            System.out.println("Job '" + jobName + "' has been resumed.");
            return true;
        } else {
            System.out.println("Job '" + jobName + "' is already active.");
            return false;
        }
    }

    
  
 // UPDATED markAsCompleted() METHOD
    public void markAsCompleted() {
        if (isPaused) {
            System.out.println("Cannot complete a paused job. Please resume it first.");
            return;
        }
        if (!isCompleted) {
            isCompleted = true;
            System.out.println("Job '" + jobName + "' has been marked as completed.");
        } else {
            System.out.println("Job '" + jobName + "' is already completed.");
        }
    }
  
    
    
    // Method to start the job
    public void startJob() {
        if (isCompleted) {
            System.out.println("Cannot start a completed job: '" + jobName + "'.");
            return;
        }
        if (isPaused) {
            System.out.println("Cannot start a paused job: '" + jobName + "'. Please resume it first.");
            return;
        }
        System.out.println("Notification: Job '" + jobName + "' has started.");
    }

    // Method to display job details
    public void displayJobDetails() {
        System.out.println("Job Name: " + jobName);
        System.out.println("Priority: " + priority);
        System.out.println("Recurrence: " + recurrence);
        System.out.println("Assigned To: " + (assignedTo == null ? "Not Assigned" : assignedTo));
        System.out.println("Status: " + (isCompleted ? "Completed" : (isPaused ? "Paused" : "Active")));
    }

    // Method to get job details as a formatted string for visualization
    public String getJobDetailsAsString() {
        return String.format(
            "Job Name: %s | Priority: %s | Recurrence: %s | Assigned To: %s | Status: %s",
            jobName,
            priority,
            recurrence,
            (assignedTo == null ? "Not Assigned" : assignedTo),
            (isCompleted ? "Completed" : (isPaused ? "Paused" : "Active"))
        );
    }

    // Method to get the job name
    public String getJobName() {
        return this.jobName;
    }
}
