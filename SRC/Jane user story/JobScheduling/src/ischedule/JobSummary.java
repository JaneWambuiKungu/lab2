package ischedule;

import java.util.List;

public class JobSummary {
    public static void printJobSummary(List<Job> jobs) {
        int totalDuration = jobs.stream().mapToInt(Job::getJobDuration).sum();
        System.out.println("Total number of jobs: " + jobs.size());
        System.out.println("Total duration of all jobs: " + totalDuration + " hours");
        jobs.forEach(job -> System.out.println(job));
    }
}