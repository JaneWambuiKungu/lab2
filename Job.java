package scheduler;

public class Job {

	private String jobName;

    public Job(String jobName) {
        this.jobName = jobName;
    }

    public void cancel() {
        System.out.println("Cancelling job: " + jobName);
    }
}
