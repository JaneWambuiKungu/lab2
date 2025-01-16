package ischedule;

import java.util.List;

public class JobValidator {
    public static boolean validateJob(Job job) {
        // Validate if job duration is greater than 0
        return job.getJobDuration() > 0;
    }

    public static boolean validateJobName(String jobName, List<Job> jobs) {
        // Validate if the job name is unique
        for (Job job : jobs) {
            if (job.getJobName().equalsIgnoreCase(jobName)) {
                return false;
            }
        }
        return true;
    }
}
