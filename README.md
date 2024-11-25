public enum Priority {
    HIGH, MEDIUM, LOW;
}

public class Job {
    private String jobId;
    private String description;
    private Priority priority; // HIGH, MEDIUM, LOW
    private boolean completed;

    //Constructor
    public Job(String jobId, String description, Priority priority) {
        this.jobId = jobId;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    // Getters and Setters
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getJobId() {
        return jobId;
    }
}
