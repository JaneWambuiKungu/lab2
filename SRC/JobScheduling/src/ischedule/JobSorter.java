package ischedule;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobSorter {
    public static void sortJobsByDuration(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparingInt(Job::getJobDuration));
    }

    public static void sortJobsByName(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparing(Job::getJobName));
    }
}