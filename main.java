

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static final List<Job> jobs = new ArrayList<>();
    private static final Map<String, String> logs = new HashMap<>();
    private static int idCounter = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Job Scheduling App ===");
            System.out.println("1. Add Job");
            System.out.println("2. Finalize Job");
            System.out.println("3. Assign Tags to Job");
            System.out.println("4. Delete Job");
            System.out.println("5. List All Jobs");
            System.out.println("6. Filter Jobs by Tag");
            System.out.println("7. Sort Jobs by Duration");
            System.out.println("8. Generate Summary Report");
            System.out.println("9. View Completed Jobs");
            System.out.println("10. View Estimated Completion Times");
            System.out.println("11. Schedule Jobs Based on System Load");
            System.out.println("12. Simulate Email Notification for Failures");
            System.out.println("13. Validate Job Dependencies");
            System.out.println("14. Cancel All Jobs");
            System.out.println("15. Set Job Dependencies");
            System.out.println("16. Cancel Unneeded Jobs");
            System.out.println("17. View Logs");
            System.out.println("18. Export Schedule");
            System.out.println("19. Mark Job as Completed");
            System.out.println("20. Prevent Overlapping Jobs for Workers");
            System.out.println("21. Assign Priorities to Jobs");
            System.out.println("22. Create Recurring Jobs");
            System.out.println("23. Pause and Resume Jobs");
            System.out.println("24. Visualize Job Schedule");
            System.out.println("25. Change Job Priority");
            System.out.println("26. Search Jobs by Keyword");
            System.out.println("27. Notifications for Job Start");
            System.out.println("28. Assign Jobs to Workers");
            System.out.println("29. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addJob(scanner);
                case 2 -> finalizeJob(scanner);
                case 3 -> assignTags(scanner);
                case 4 -> deleteJob(scanner);
                case 5 -> listJobs();
                case 6 -> filterJobsByTag(scanner);
                case 7 -> sortJobsByDuration();
                case 8 -> generateSummaryReport();
                case 9 -> viewCompletedJobs();
                case 10 -> viewEstimatedCompletionTimes();
                case 11 -> scheduleBasedOnSystemLoad();
                case 12 -> simulateEmailNotification();
                case 13 -> validateDependencies();
                case 14 -> cancelAllJobs();
                case 15 -> setJobDependencies(scanner);
                case 16 -> cancelUnneededJobs(scanner);
                case 17 -> viewLogs();
                case 18 -> exportSchedule();
                case 19 -> markJobAsCompleted(scanner);
                case 20 -> preventOverlappingJobs(scanner);
                case 21 -> assignPriorities(scanner);
                case 22 -> createRecurringJobs(scanner);
                case 23 -> pauseResumeJobs(scanner);
                case 24 -> visualizeJobSchedule();
                case 25 -> changeJobPriority(scanner);
                case 26 -> searchJobsByKeyword(scanner);
                case 27 -> notifyJobStart(scanner);
                case 28 -> assignJobsToWorkers(scanner);
                case 29 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addJob(Scanner scanner) {
        System.out.print("Enter Job Name: ");
        String jobName = scanner.nextLine();
        System.out.print("Enter Job Duration (in hours): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Tags (comma-separated): ");
        String tagInput = scanner.nextLine();
        List<String> tags = Arrays.asList(tagInput.split(","));
        String jobId = "JOB-" + (idCounter++);
        jobs.add(new Job(jobId, jobName, duration, tags));
        logs.put(jobId, "Job added.");
        System.out.println("Job added successfully: " + jobName);
    }

    private static void finalizeJob(Scanner scanner) {
        System.out.print("Enter Job ID to finalize: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            job.setFinalized(true);
            logs.put(jobId, "Job finalized.");
            System.out.println("Job finalized: " + job.getJobName());
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void assignTags(Scanner scanner) {
        System.out.print("Enter Job ID: ");
        String jobId = scanner.nextLine();
        System.out.print("Enter Tags (comma-separated): ");
        String tagInput = scanner.nextLine();
        List<String> tags = Arrays.asList(tagInput.split(","));
        Job job = findJobById(jobId);
        if (job != null) {
            job.setTags(tags);
            logs.put(jobId, "Tags updated.");
            System.out.println("Tags updated for job: " + job.getJobName());
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void deleteJob(Scanner scanner) {
        System.out.print("Enter Job ID to delete: ");
        String jobId = scanner.nextLine();
        if (jobs.removeIf(job -> job.getJobId().equals(jobId))) {
            logs.put(jobId, "Job deleted.");
            System.out.println("Job deleted.");
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void listJobs() {
        jobs.forEach(System.out::println);
    }

    private static void filterJobsByTag(Scanner scanner) {
        System.out.print("Enter tag to filter by: ");
        String tag = scanner.nextLine();
        jobs.stream().filter(job -> job.getTags().contains(tag)).forEach(System.out::println);
    }

    private static void sortJobsByDuration() {
        jobs.sort(Comparator.comparingInt(Job::getJobDuration));
        System.out.println("Jobs sorted by duration.");
    }

    private static void generateSummaryReport() {
        long finalizedCount = jobs.stream().filter(Job::isFinalized).count();
        System.out.println("Total Jobs: " + jobs.size());
        System.out.println("Finalized Jobs: " + finalizedCount);
        System.out.println("Pending Jobs: " + (jobs.size() - finalizedCount));
    }

    private static void viewCompletedJobs() {
        jobs.stream().filter(Job::isFinalized).forEach(System.out::println);
    }

    private static void viewEstimatedCompletionTimes() {
        jobs.forEach(job -> {
            System.out.println(job.getJobId() + " - Estimated Completion: " +
                    (job.getEstimatedCompletionTime() != null ? job.getEstimatedCompletionTime() : "Not Set"));
        });
    }

    private static void scheduleBasedOnSystemLoad() {
        System.out.println("Scheduling jobs based on system load...");
        jobs.sort(Comparator.comparing(Job::getJobDuration));
    }

    private static void simulateEmailNotification() {
        System.out.println("Simulating email notification for failures...");
    }

    private static void validateDependencies() {
        System.out.println("Validating dependencies...");
    }

    private static void cancelAllJobs() {
        jobs.clear();
        logs.put("ALL", "All jobs cancelled.");
    }

    private static void setJobDependencies(Scanner scanner) {
        System.out.print("Enter Job ID: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            System.out.print("Enter Dependencies (comma-separated): ");
            String[] deps = scanner.nextLine().split(",");
            Arrays.stream(deps).forEach(job::addDependency);
            logs.put(jobId, "Dependencies updated.");
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void cancelUnneededJobs(Scanner scanner) {
        System.out.print("Enter Job ID: ");
        String jobId = scanner.nextLine();
        jobs.removeIf(job -> job.getJobId().equals(jobId));
        logs.put(jobId, "Job cancelled as unneeded.");
    }

    private static void viewLogs() {
        logs.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private static void exportSchedule() {
        try (FileWriter writer = new FileWriter("JobSchedule.txt")) {
            for (Job job : jobs) {
                writer.write(job.toString() + "\n");
            }
            System.out.println("Schedule exported.");
        } catch (IOException e) {
            System.out.println("Error exporting schedule: " + e.getMessage());
        }
    }

    private static void markJobAsCompleted(Scanner scanner) {
        System.out.print("Enter Job ID to mark as completed: ");
        String jobId = scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            job.setFinalized(true);
            logs.put(jobId, "Job marked as completed.");
            System.out.println("Job marked as completed: " + job.getJobName());
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void preventOverlappingJobs(Scanner scanner) {
        System.out.println("Feature to prevent overlapping jobs is under development.");
    }

    private static void assignPriorities(Scanner scanner) {
        System.out.print("Enter Job ID to assign priority: ");
        String jobId = scanner.nextLine();
        System.out.print("Enter priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            job.setPriority(priority);
            logs.put(jobId, "Priority updated.");
            System.out.println("Priority assigned to job: " + job.getJobName());
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void createRecurringJobs(Scanner scanner) {
        System.out.print("Enter Job Name: ");
        String jobName = scanner.nextLine();
        System.out.print("Enter Job Duration (in hours): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Tags (comma-separated): ");
        String tagInput = scanner.nextLine();
        System.out.print("Enter recurrence count: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            String jobId = "JOB-" + (idCounter++);
            jobs.add(new Job(jobId, jobName + " (Instance " + (i + 1) + ")", duration, Arrays.asList(tagInput.split(","))));
            logs.put(jobId, "Recurring job created.");
        }
        System.out.println("Recurring jobs created.");
    }

    private static void pauseResumeJobs(Scanner scanner) {
        System.out.println("Feature to pause and resume jobs is under development.");
    }

    private static void visualizeJobSchedule() {
        System.out.println("Visualizing job schedule...");
        jobs.forEach(System.out::println);
    }

    private static void changeJobPriority(Scanner scanner) {
        System.out.print("Enter Job ID to change priority: ");
        String jobId = scanner.nextLine();
        System.out.print("Enter new priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            job.setPriority(priority);
            logs.put(jobId, "Priority changed.");
            System.out.println("Priority updated for job: " + job.getJobName());
        } else {
            System.out.println("Job not found.");
        }
    }

    private static void searchJobsByKeyword(Scanner scanner) {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        jobs.stream().filter(job -> job.getJobName().contains(keyword)).forEach(System.out::println);
    }

    private static void notifyJobStart(Scanner scanner) {
        System.out.println("Feature to notify job start is under development.");
    }

    private static void assignJobsToWorkers(Scanner scanner) {
        System.out.print("Enter Job ID to assign: ");
        String jobId = scanner.nextLine();
        System.out.print("Enter Worker Name: ");
        String workerName = scanner.nextLine();
        Job job = findJobById(jobId);
        if (job != null) {
            job.setWorker(workerName);
            logs.put(jobId, "Assigned to worker: " + workerName);
            System.out.println("Job " + job.getJobName() + " assigned to worker: " + workerName);
        } else {
            System.out.println("Job not found.");
        }
    }

    private static Job findJobById(String jobId) {
        return jobs.stream().filter(job -> job.getJobId().equals(jobId)).findFirst().orElse(null);
    }

    static class Job {
        private final String jobId;
        private final String jobName;
        private final int jobDuration;
        private final List<String> tags;
        private boolean finalized;
        private int priority;
        private String worker;
        private final List<String> dependencies = new ArrayList<>();
        private LocalDateTime estimatedCompletionTime;

        public Job(String jobId, String jobName, int jobDuration, List<String> tags) {
            this.jobId = jobId;
            this.jobName = jobName;
            this.jobDuration = jobDuration;
            this.tags = new ArrayList<>(tags);
            this.finalized = false;
            this.priority = 0;
            this.worker = "";
        }
        public void setTags(List<String> tags) {
            this.tags.clear();
            this.tags.addAll(tags);
        }
        public void addDependency(String dependency) {
            dependencies.add(dependency); // Add the dependency to the list
        }


        public String getJobId() {
            return jobId;
        }

        public String getJobName() {
            return jobName;
        }

        public int getJobDuration() {
            return jobDuration;
        }

        public List<String> getTags() {
            return tags;
        }

        public boolean isFinalized() {
            return finalized;
        }

        public void setFinalized(boolean finalized) {
            this.finalized = finalized;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
       


        public String getWorker() {
            return worker;
        }
        public LocalDateTime getEstimatedCompletionTime() {
            return estimatedCompletionTime;
        }

        public void setEstimatedCompletionTime(LocalDateTime estimatedCompletionTime) {
            this.estimatedCompletionTime = estimatedCompletionTime;
        }


        public void setWorker(String worker) {
            this.worker = worker;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "jobId='" + jobId + '\'' +
                    ", jobName='" + jobName + '\'' +
                    ", jobDuration=" + jobDuration +
                    ", tags=" + tags +
                    ", finalized=" + finalized +
                    ", priority=" + priority +
                    ", worker='" + worker + '\'' +
                    '}';
        }
    }
}
