public void filterJobsByTags(String tag) {
    System.out.println("\nJobs with tag: " + tag);
    for (Job job : jobs) {
        if (job.getTags().contains(tag)) {
            System.out.println(job.getJobId() + ": " + job.getJobName());
        }
    }
}
