package ischedule;

import java.util.List;

//In JobUpdater.java
public class JobUpdater {
 public static void updateJob(List<Job> jobs, String jobId, String newJobName, int newJobDuration) {
     for (int i = 0; i < jobs.size(); i++) {
         if (jobs.get(i).getJobId().equals(jobId)) {
             // Create a new job with the updated details and replace the old job
             jobs.set(i, new Job(jobId, newJobName, newJobDuration));
             break;
         }
     }
 }
}
