

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JobSchedulingApp {
    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Job Scheduling Application ===");
            System.out.println("0. Add Jobs");
            System.out.println("1. View a historical list of completed jobs");
            System.out.println("2. See estimated completion time for each job");
            System.out.println("3. Schedule jobs based on system load");
            System.out.println("4. Receive an email notification for job failure (Simulated)");
            System.out.println("5. Validate inputs for job dependencies");
            System.out.println("6. Cancel all jobs at once");
            System.out.println("7. Set dependency relationships between jobs");
            System.out.println("8. Cancel jobs that are no longer needed");
            System.out.println("9. Log changes to jobs (like edits, deletions)");
            System.out.println("10. Export the job schedule as a text report");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 0 -> {  // Option for adding a job
                    System.out.print("Enter job ID: ");
                    String jobId = scanner.nextLine();
                    scheduler.addJob(jobId);
                }
                case 1 -> scheduler.viewCompletedJobs();
                case 2 -> scheduler.viewEstimatedCompletionTimes();
                case 3 -> scheduler.scheduleBasedOnSystemLoad();
                case 4 -> scheduler.simulateEmailNotification();
                case 5 -> scheduler.validateInputs();
                case 6 -> scheduler.cancelAllJobs();
                case 7 -> scheduler.setJobDependencies(scanner);
                case 8 -> scheduler.cancelUnneededJobs(scanner);
                case 9 -> scheduler.viewLogs();
                case 10 -> scheduler.exportSchedule();
                case 11 -> {
                    System.out.println("Exiting Job Scheduler. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 0 and 11.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        
        scanner.close();
        
    }
}

class JobScheduler {
    private final List<Job> activeJobs;
    private final List<Job> completedJobs;
    private final Map<String, String> logs;

    public JobScheduler() {
        this.activeJobs = new ArrayList<>();
        this.completedJobs = new ArrayList<>();
        this.logs = new HashMap<>();
    }

    public void viewCompletedJobs() {
        if (completedJobs.isEmpty()) {
            System.out.println("No completed jobs.");
        } else {
            completedJobs.forEach(System.out::println);
        }
    }
    public void addJob(String jobId) {
        Job newJob = new Job(jobId);
        activeJobs.add(newJob);
        logs.put(jobId, "Job added to the scheduler.");
        System.out.println("Job added: " + newJob);
    }
    public void viewEstimatedCompletionTimes() {
        if (activeJobs.isEmpty()) {
            System.out.println("No active jobs.");
        } else {
            activeJobs.forEach(job -> System.out.println(job.getJobId() + " - Estimated Completion: " +
                    (job.getEstimatedCompletionTime() != null ? job.getEstimatedCompletionTime() : "Not Set")));
        }
    }

    public void scheduleBasedOnSystemLoad() {
        System.out.println("Scheduling jobs based on system load...");
        activeJobs.sort(Comparator.comparing(Job::getJobId)); // Placeholder
        System.out.println("Jobs scheduled successfully.");
    }

    public void simulateEmailNotification() {
        System.out.println("Simulating email notification for a failed job...");
        System.out.println("Email sent to user@example.com.");
    }

    public void validateInputs() {
        System.out.println("Validating job dependencies...");
        for (Job job : activeJobs) {
            for (String dependency : job.getDependencies()) {
                boolean dependencyExists = activeJobs.stream().anyMatch(j -> j.getJobId().equals(dependency));
                if (!dependencyExists) {
                    System.out.println("Invalid dependency found for job: " + job.getJobId());
                }
            }
        }
        System.out.println("Validation complete.");
    }

    public void cancelAllJobs() {
        activeJobs.forEach(job -> job.setStatus("Cancelled"));
        activeJobs.clear();
        System.out.println("All jobs have been cancelled.");
    }

    public void setJobDependencies(Scanner scanner) {
        System.out.print("Enter the job ID to add dependencies for: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            System.out.print("Enter dependencies (comma-separated): ");
            String[] dependencies = scanner.nextLine().split(",");
            for (String dependency : dependencies) {
                job.addDependency(dependency.trim());
            }
            System.out.println("Dependencies added for " + jobId);
        } else {
            System.out.println("Job not found.");
        }
    }

    public void cancelUnneededJobs(Scanner scanner) {
        System.out.print("Enter the job ID to cancel: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            job.setStatus("Cancelled");
            activeJobs.remove(job);
            logs.put(jobId, "Job cancelled as no longer needed.");
            System.out.println("Job " + jobId + " has been cancelled.");
        } else {
            System.out.println("Job not found.");
        }
    }

    public void viewLogs() {
        if (logs.isEmpty()) {
            System.out.println("No logs available.");
        } else {
            logs.forEach((jobId, log) -> System.out.println(jobId + ": " + log));
        }
    }

    public void exportSchedule() {
        System.out.println("Exporting schedule...");
        try (FileWriter writer = new FileWriter("JobScheduleReport.txt")) {
            writer.write("Active Jobs:\n");
            for (Job job : activeJobs) {
                writer.write(job.toString() + "\n");
            }
            writer.write("\nCompleted Jobs:\n");
            for (Job job : completedJobs) {
                writer.write(job.toString() + "\n");
            }
            System.out.println("Schedule exported to JobScheduleReport.txt");
        } catch (IOException e) {
            System.out.println("Error exporting schedule: " + e.getMessage());
        }
    }

    private Job findJobById(String jobId) {
        return activeJobs.stream().filter(job -> job.getJobId().equals(jobId)).findFirst().orElse(null);
    }
}

