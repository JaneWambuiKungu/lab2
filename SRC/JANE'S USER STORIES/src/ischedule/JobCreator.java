package ischedule;
import java.util.ArrayList;
import java.util.List;

public class JobCreator {
    public static List<Job> createJobs() {
        List<Job> jobs = new ArrayList<>();
        // Sample job creation
        jobs.add(new Job("1", "Design", 5));
        jobs.add(new Job("2", "Development", 15));
        jobs.add(new Job("3", "Testing", 8));
        return jobs;
    }
    public static Job createJob(String jobId, String jobName, int jobDuration) {
        return new Job(jobId, jobName, jobDuration);
    }
}