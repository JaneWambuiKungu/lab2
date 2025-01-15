package jobscheduling;

import java.util.List;

// Class to represent a job in the scheduler
public class Job {
    private String jobId; // Unique identifier for the job
    private String jobName; // Descriptive name of the job
    private boolean isFinalized; // Indicates if the job is finalized
    private List<String> tags; // Tags to categorise the job
    private String visibleToRole; // Specifies the role that can see this job
    private String deadlineAlert; // Stores any deadline alerts for the job

    // Constructor to initialize job details
    public Job(String jobId, String jobName, List<String> tags) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.isFinalized = false; // Jobs are not finalized by default
        this.tags = tags; // List of tags provided during creation
        this.visibleToRole = ""; // Default visibility is empty
        this.deadlineAlert = ""; // No alerts by default
    }

    // Getter methods to access private fields
    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getVisibleToRole() {
        return visibleToRole;
    }

    public String getDeadlineAlert() {
        return deadlineAlert;
    }

    // Setter methods to modify private fields
    public void setFinalized(boolean finalized) {
        isFinalized = finalized;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setVisibleToRole(String visibleToRole) {
        this.visibleToRole = visibleToRole;
    }

    public void setDeadlineAlert(String deadlineAlert) {
        this.deadlineAlert = deadlineAlert;
    }

    // toString method to display job details in a readable format
    @Override
    public String toString() {
        String status = isFinalized ? "[Finalized]" : "[Pending]";
        return jobId + ": " + jobName + " " + status + ", Tags: " + tags +
                ", Role: " + visibleToRole + ", Alert: " + deadlineAlert;
    }
}

