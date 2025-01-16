package ischedule;

import java.util.List;

public class JobViewer {
    public static void viewJobs(List<Job> jobs) {
        System.out.println("Viewing all jobs:");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    public static void viewJobDetails(List<Job> jobs, String jobId) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                System.out.println("Job details: " + job);
                return;
            }
        }
        System.out.println("Job with ID " + jobId + " not found.");
    }
}
