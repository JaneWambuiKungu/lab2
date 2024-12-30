import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JobSchedulingApp {
    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Job Scheduling Application ===");
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Export job schedule as a text report");
            System.out.println("2. View all jobs");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1 -> scheduler.exportSchedule();
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
    private final List<Job> activeJobs;
    private final List<Job> completedJobs;

    public JobScheduler() {
        this.activeJobs = new ArrayList<>();
        this.completedJobs = new ArrayList<>();
        // Sample data
        activeJobs.add(new Job("Job1", "Active"));
        activeJobs.add(new Job("Job2", "Active"));
        completedJobs.add(new Job("Job3", "Completed"));
    }

    // This will basically export text report of Jobs with details
    public void exportSchedule() {
        System.out.println("Exporting job schedule to a text file...");
        try (FileWriter writer = new FileWriter("JobScheduleReport.txt")) { //This will create file in which the report of job will be stored
            writer.write("=== Job Schedule Report ===\n\n");

            writer.write("Active Jobs:\n");
            for (Job job : activeJobs) {
                writer.write(job + "\n");
            }

            writer.write("\nCompleted Jobs:\n");
            for (Job job : completedJobs) {
                writer.write(job + "\n");
            }

            System.out.println("Schedule exported successfully to JobScheduleReport.txt");
        } catch (IOException e) {
            System.out.println("Error exporting schedule: " + e.getMessage());
        }
    }

    
    public void viewAllJobs() {
        System.out.println("=== Active Jobs ===");
        for (Job job : activeJobs) {
            System.out.println(job);
        }

        System.out.println("\n=== Completed Jobs ===");
        for (Job job : completedJobs) {
            System.out.println(job);
        }
    }
}

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
