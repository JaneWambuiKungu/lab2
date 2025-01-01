public void assignTags(String jobId, List<String> tags) {
    for (Job job : jobs) {
        if (job.getJobId().equals(jobId)) {
            job.setTags(tags);
            System.out.println("Tags assigned to job: " + job.getJobName());
            return;
        }
    }
    System.out.println("Error: Job with ID " + jobId + " not found.");
}
