package ischedule;
import java.util.List;

public class JobScheduler {
    public static void scheduleJobs(List<Job> jobs) {
        // Simple FIFO scheduling
        System.out.println("Job Schedule:");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

public static void scheduleJobsByPriority(List<Job> jobs) {
    // Placeholder for a priority-based scheduler (could be extended further)
    jobs.sort((job1, job2) -> Integer.compare(job1.getJobDuration(), job2.getJobDuration()));
    System.out.println("Job Schedule (Priority by Duration):");
    for (Job job : jobs) {
        System.out.println(job);
    }
}
}