package j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Job {
    private final String jobId;
    private String status; // IT WILL DISPLAY STATUS OF JOB
    private LocalDateTime estimatedCompletionTime;
    private LocalDateTime completionTime;
    private final List<String> dependencies;

    public Job(String jobId) {
        this.jobId = jobId;
        this.status = "Scheduled";
        this.dependencies = new ArrayList<>();
    }

    public String getJobId() {
        return jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedCompletionTime() {
        return estimatedCompletionTime;
    }

    public void setEstimatedCompletionTime(LocalDateTime estimatedCompletionTime) {
        this.estimatedCompletionTime = estimatedCompletionTime;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void addDependency(String jobId) {
        dependencies.add(jobId);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", status='" + status + '\'' +
                ", estimatedCompletionTime=" + estimatedCompletionTime +
                ", completionTime=" + completionTime +
                ", dependencies=" + dependencies +
                '}';
    }
}
