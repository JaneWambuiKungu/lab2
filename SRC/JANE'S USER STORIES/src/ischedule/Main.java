package ischedule;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create Jobs
        List<Job> jobs = JobCreator.createJobs();
        System.out.println("Initial Job List:");
        JobViewer.viewJobs(jobs);

        // 2. Validate Jobs
        System.out.println("\nValidating Jobs:");
        for (Job job : jobs) {
            if (!JobValidator.validateJob(job)) {
                System.out.println("Invalid job: " + job);
            }
        }

        // 3. Sort Jobs by Duration
        System.out.println("\nJobs Sorted by Duration:");
        JobSorter.sortJobsByDuration(jobs);
        JobViewer.viewJobs(jobs);

        // 4. Schedule Jobs
        System.out.println("\nJob Schedule:");
        JobScheduler.scheduleJobs(jobs);

        // 5. Filter Jobs with Duration >= 10
        System.out.println("\nFiltered Jobs (Duration >= 10):");
        List<Job> filteredJobs = JobFilter.filterJobsByDuration(jobs, 10);
        JobViewer.viewJobs(filteredJobs);

        // 6. Delete a Job
        System.out.println("\nJobs after Deletion:");
        JobDeletor.deleteJob(jobs, "2");
        JobViewer.viewJobs(jobs);

        // 7. Update a Job
        System.out.println("\nUpdating Job with ID '1':");
        JobUpdater.updateJob(jobs, "1", "Redesign", 6);
        JobViewer.viewJobs(jobs);

        // 8. Validate and Add a New Job
        System.out.println("\nAdding a New Job:");
        Job newJob = new Job("4", "Documentation", 3);
        if (JobEntryValidator.validateNewJob(newJob)) {
            jobs.add(newJob);
        } else {
            System.out.println("Failed to add invalid job: " + newJob);
        }
        JobViewer.viewJobs(jobs);
        // 9. Edit an Existing Job
        System.out.println("\nEditing Job with ID '3':");
        JobEditor.editJob(jobs, "3", "Final Testing", 9);
        JobViewer.viewJobs(jobs);

        // 10. Job Summary
        System.out.println("\nJob Summary:");
        JobSummary.printJobSummary(jobs);
    }
}
