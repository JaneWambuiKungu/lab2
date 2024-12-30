package example1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main class 
public class JobSchedulingApp {
    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Job Scheduling Application ===");
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View a historical list of completed jobs");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1 -> scheduler.viewCompletedJobs();
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

// JobScheduler Class
class JobScheduler {
    private final List<Job> completedJobs;

    public JobScheduler() {
        this.completedJobs = new ArrayList<>();
        // Sample data for completed jobs
        completedJobs.add(new Job("Job1", "Completed"));
        completedJobs.add(new Job("Job2", "Completed"));
        completedJobs.add(new Job("Job3", "Completed"));
    }

    // Method to view the historical list of completed jobs
    public void viewCompletedJobs() {
        if (completedJobs.isEmpty()) {
            System.out.println("No completed jobs available.");
        } else {
            System.out.println("=== Completed Jobs ===");
            for (Job job : completedJobs) {
                System.out.println(job);
            }
        }
    }
}

// Job Class
class Job {
    private final String jobId;
    private final String status;

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

    @Override
    public String toString() {
        return "Job ID: " + jobId + ", Status: " + status;
    }
}
