package ischedule;


public class Job {
    private String jobId;
    private String jobName;
    private int jobDuration; // in hours
    
    public Job(String jobId, String jobName, int jobDuration) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDuration = jobDuration;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobDuration() {
        return jobDuration;
    }

    @Override
    public String toString() {
        return "Job[ID=" + jobId + ", Name=" + jobName + ", Duration=" + jobDuration + " hours]";
    }
}
