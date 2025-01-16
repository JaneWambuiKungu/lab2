package jobscheduler;

public class Main {
    public static void main(String[] args) {
        // Create the JobScheduler
        JobScheduler scheduler = new JobScheduler();

        // Create jobs
        Job job1 = new Job("Design Homepage");
        job1.setPriority("High");
        job1.setRecurrence("Weekly");
        job1.assignTo("John Doe");

        Job job2 = new Job("Backend Development");
        job2.setPriority("Medium");
        job2.setRecurrence("Daily");
        job2.assignTo("John Doe"); // Same worker as job1 to test conflict

        Job job3 = new Job("Database Migration");
        job3.setPriority("Low");
        job3.setRecurrence("Monthly");
        job3.assignTo("Jane Smith");

      
        
     // Add jobs to the scheduler with success checks
        if (scheduler.addJob(job1)) {
            System.out.println("Job1 ('Design Homepage') added successfully.");
        } else {
            System.out.println("Failed to add Job1 ('Design Homepage').");
        }

        if (scheduler.addJob(job2)) {
            System.out.println("Job2 ('Backend Development') added successfully.");
        } else {
            System.out.println("Failed to add Job2 ('Backend Development') due to worker conflict.");
        }

        if (scheduler.addJob(job3)) {
            System.out.println("Job3 ('Database Migration') added successfully.");
        } else {
            System.out.println("Failed to add Job3 ('Database Migration').");
        }

        
        
        // Mark job1 as completed
        job1.markAsCompleted();

        // Attempt to pause, resume, or reassign the completed job
        job1.pauseJob(); // Should not allow
        job1.resumeJob(); // Should not allow
        job1.assignTo("Team Alpha"); // Should not allow


        // Visualize the updated schedule
        scheduler.visualizeSchedule();
        
     // Search for jobs with specific keywords
        scheduler.searchJobsByKeyword("Design");  // Should match "Design Homepage"
        scheduler.searchJobsByKeyword("Backend"); // Should match "Backend Development"
        scheduler.searchJobsByKeyword("John");    // Should match "John Doe"
        scheduler.searchJobsByKeyword("Urgent");  // Should return "No jobs found"
        
        // Change priority of active job
        System.out.println("\n--- Changing Priority of Active Job ---");
        job1.setPriority("Low");

        // Pause the job and then change priority
        job1.pauseJob();
        System.out.println("\n--- Changing Priority of Paused Job ---");
        job1.setPriority("Medium");

        // Mark job as completed and try changing priority
        job1.resumeJob();
        job1.markAsCompleted();
        System.out.println("\n--- Attempting to Change Priority of Completed Job ---");
        job1.setPriority("High");
        
        
     // Add jobs to the scheduler
        scheduler.addJob(job1);
        scheduler.addJob(job2);

        // Notify for job starts
        scheduler.notifyForJobStart();

        // Mark one job as completed and try to start it
        job1.markAsCompleted();
        scheduler.notifyForJobStart();

        // Pause a job and try to start it
        job2.pauseJob();
        scheduler.notifyForJobStart();
    }
}
