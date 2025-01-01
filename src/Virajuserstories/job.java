package jobscheduling;

import java.util.List;

public class Job {
    private String jobId;
    private String jobName;
    private String priority;
    private String startTime;
    private String endTime;
    private boolean isCompleted;
    private List<String> tags;
    private String visibleToRole;
    private String deadlineAlert;

    // Constructor
    public Job(String jobId, String jobName, String priority, String startTime, String endTime, List<String> tags, String visibleToRole) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCompleted = false;
        this.tags = tags;
        this.visibleToRole = visibleToRole;
        this.deadlineAlert = "";
    }

    // Getters and Setters
    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getVisibleToRole() { return visibleToRole; }
    public void setVisibleToRole(String visibleToRole) { this.visibleToRole = visibleToRole; }

    public String getDeadlineAlert() { return deadlineAlert; }
    public void setDeadlineAlert(String deadlineAlert) { this.deadlineAlert = deadlineAlert; }

    @Override
    public String toString() {
        String status = isCompleted ? "[Completed]" : "[Pending]";
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", priority='" + priority + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tags=" + tags +
                ", visibleToRole='" + visibleToRole + '\'' +
                ", deadlineAlert='" + deadlineAlert + '\'' +
                "} " + status;
    }
}
