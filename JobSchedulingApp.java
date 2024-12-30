import java.util.*;

public class JobSchedulingApp {
    public static void main(String[] args) {
        //Public string cancelId, name;
        
        JobScheduler scheduler = new JobScheduler();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Job Scheduling Application ===");
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Cancel jobs that are no longer needed");
            System.out.println("2. View all jobs");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1 -> scheduler.cancelUnneededJobs(scanner);
                    case 2 -> scheduler.viewAllJobs();
                    case 0 -> {
                        System.out.println("Exiting application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}

class JobScheduler {
    private final List<Job> jobs;

    public JobScheduler() {
        this.jobs = new ArrayList<>();
      
        jobs.add(new Job("Job1", "Active"));
        jobs.add(new Job("Job2", "Active"));
        jobs.add(new Job("Job3", "Active"));
      
    }

    // Method to cancel jobs that are no longer needed
    public void cancelUnneededJobs(Scanner scanner) {
        System.out.println("=== Cancel Unneeded Jobs ===");
        System.out.print("Enter the job ID to cancel: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);

        if (job != null) {
            job.setStatus("Cancelled");
            System.out.println("Job " + jobId + " has been cancelled.");
        } else {
            System.out.println("Job not found.");
        }
    }

    // Method to view all jobs
    public void viewAllJobs() {
        System.out.println("=== Jobs ===");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    private Job findJobById(String jobId) {
        for (Job job : jobs) {
            if (job.getJobId().equals(jobId)) {
                return job;
            }
        }
        return null;
    }
}

class Job {
    private final String jobId;
    private String status;

    public Job(String jobId, String status) {
        this.jobId = jobId;
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job ID: " + jobId + ", Status: " + status;
    }
}
