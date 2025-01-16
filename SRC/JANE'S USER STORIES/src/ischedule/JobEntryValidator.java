package ischedule;

public class JobEntryValidator {
    public static boolean validateNewJob(Job job) {
        if (job == null) {
            return false;
        }
        if (job.getJobName() == null || job.getJobName().trim().isEmpty()) {
            return false;
        }
        if (job.getJobDuration() <= 0) {
            return false;
        }
        return true;
    }
}



