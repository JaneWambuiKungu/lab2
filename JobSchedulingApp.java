import java.util.*;

public class JobSchedulingApp {
    public static void main(String[] args) {
        //Public string a1
        //int jobId
        JobScheduler scheduler = new JobScheduler();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Job Scheduling Application ===");
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Set dependency relationships between jobs");
            System.out.println("2. View all jobs and their dependencies");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1 -> scheduler.setJobDependencies(scanner);
                    case 2 -> scheduler.viewAllJobs();
                    case 0 -> {
                        System.out.println("Exiting application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close();
    }
}

class JobScheduler {
    private final List<Job> jobs;

    public JobScheduler() {
        this.jobs = new ArrayList<>();
        // Sample data so we can add dependencies
        jobs.add(new Job("Job1"));
        jobs.add(new Job("Job2"));
        jobs.add(new Job("Job3"));
    }

    // This will set dependencies for a specific job
    public void setJobDependencies(Scanner scanner) {
        System.out.println("=== Set Job Dependencies ===");
        System.out.print("Enter the job ID to set dependencies for: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);

        if (job != null) {
            System.out.print("Enter dependencies (comma-separated Job IDs): ");
            String[] dependencies = scanner.nextLine().split(",");
            for (String dependencyId : dependencies) {
                dependencyId = dependencyId.trim();
                if (!dependencyId.equals(jobId) && findJobById(dependencyId) != null) {
                    job.addDependency(dependencyId);
                } else {
                    System.out.println("Dependency " + dependencyId + " is invalid or does not exist.");
                }
            }
            System.out.println("Dependencies for " + jobId + ": " + job.getDependencies());
        } else {
            System.out.println("Job not found.");
        }
    }

    // This is to view all jobs and their dependencies
    public void viewAllJobs() {
        System.out.println("=== Jobs and Dependencies ===");
        for (Job job : jobs) {
            System.out.println("Job ID: " + job.getJobId() + ", Dependencies: " + job.getDependencies());
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
    private final Set<String> dependencies;

    public Job(String jobId) {
        this.jobId = jobId;
        this.dependencies = new HashSet<>();
    }

    public String getJobId() {
        return jobId;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }

    public void addDependency(String dependency) {
        dependencies.add(dependency);
    }

    @Override
    public String toString() {
        return "Job ID: " + jobId + ", Dependencies: " + dependencies;
    }
}
