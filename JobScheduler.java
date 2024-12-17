package scheduler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JobScheduler {
	 private List<Job> jobs;

	    public JobScheduler() {
	        this.jobs = new CopyOnWriteArrayList<>();
	    }

	    public void addJob(Job job) {
	        jobs.add(job);
	    }

	    public void cancelAllJobs() {
	        for (Job job : jobs) {
	            job.cancel();
	        }
	        jobs.clear();
	    }

	    public static void main(String[] args) {
	        JobScheduler scheduler = new JobScheduler();
	        
	        // Add jobs to the scheduler
	        scheduler.addJob(new Job("Job 1"));
	        scheduler.addJob(new Job("Job 2"));
	        scheduler.addJob(new Job("Job 3"));
	        
	        // Cancel all jobs
	        scheduler.cancelAllJobs();
	    }

}
