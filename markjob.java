public void markJobAsCompleted(String jobId) {
    for (Job job : jobs) {
        if (job.getJobId().equals(jobId)) {
            job.setCompleted(true);
            System.out.println("Job marked as completed: " + job.getJobName());
            return;
        }
    }
    System.out.println("Error: Job with ID " + jobId + " not found.");
}
