package ischedule;
import java.util.List;
import java.util.stream.Collectors;

public class JobFilter {
    public static List<Job> filterJobsByDuration(List<Job> jobs, int minDuration) {
        return jobs.stream()
                .filter(job -> job.getJobDuration() >= minDuration)
                .collect(Collectors.toList());
    }

    public static List<Job> filterJobsByName(List<Job> jobs, String nameSubstring) {
        return jobs.stream()
                .filter(job -> job.getJobName().contains(nameSubstring))
                .collect(Collectors.toList());
    }
}
