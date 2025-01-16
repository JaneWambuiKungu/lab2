package ischedule;

import java.util.List;

public class JobEditor {
    public static void editJob(List<Job> jobs, String jobId, String newJobName, int newJobDuration) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getJobId().equals(jobId)) {
                // Create a new job object with the updated details and replace the old one
                jobs.set(i, new Job(jobId, newJobName, newJobDuration));
                break;
            }
        }
    }
}
