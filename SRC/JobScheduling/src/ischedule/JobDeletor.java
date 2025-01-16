package ischedule;
import java.util.List;
public class JobDeletor {
    public static void deleteJob(List<Job> jobs, String jobId) {
        jobs.removeIf(job -> job.getJobId().equals(jobId));
    }
}
