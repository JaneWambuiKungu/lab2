package scheduler;

public class Job {

	public String jbName;

    public Job(String jbName) {
        this.jbName = jbName;
    }

    public void cancel() {
        System.out.println("Cancelling job: " + jbName);
    }
}
