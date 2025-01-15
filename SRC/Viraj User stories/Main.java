package jobscheduling;

import java.util.Scanner;
import java.util.ArrayList;

// Entry point of the application
public class Main {
    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler(); // Create a JobScheduler instance
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        while (true) {
            // Display the menu options
            System.out.println("\nMenu:");
            System.out.println("1. Add Job");
            System.out.println("2. Finalize Scheduled Job");
            System.out.println("3. Assign Tags to Job");
            System.out.println("4. Filter Jobs by Tag");
            System.out.println("5. Adjust Job Visibility by Role");
            System.out.println("6. Generate Summary Report");
            System.out.println("7. Set Deadline Alert");
            System.out.println("8. Log User Actions");
            System.out.println("9. List Jobs");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read the user's choice

            switch (choice) {
                case 1:
                    System.out.print("Enter your role (Admin, Manager, Developer, User): ");
                    String userRole = scanner.next();
                    String jobId = scheduler.generateJobId(userRole); // Generate Job ID
                    System.out.println("Generated Job ID: " + jobId);

                    System.out.print("Enter Job Name: ");
                    String jobName = scanner.next();

                    System.out.print("Enter Tags (comma-separated): ");
                    String tagInput = scanner.next();
                    ArrayList<String> tags = new ArrayList<>();
                    for (String tag : tagInput.split(",")) {
                        tags.add(tag.trim());
                    }

                    scheduler.addJob(new Job(jobId, jobName, tags)); // Add the job
                    break;

                case 2:
                    System.out.print("Enter Job ID to finalize: ");
                    String finalizeJobId = scanner.next();
                    scheduler.finalizeJob(finalizeJobId);
                    break;

                case 3:
                    System.out.print("Enter Job ID to assign tags: ");
                    String assignJobId = scanner.next();
                    System.out.print("Enter tags (comma-separated): ");
                    String newTagInput = scanner.next();
                    ArrayList<String> newTags = new ArrayList<>();
                    for (String tag : newTagInput.split(",")) {
                        newTags.add(tag.trim());
                    }
                    scheduler.assignTags(assignJobId, newTags);
                    break;

                case 4:
                    System.out.print("Enter tag to filter jobs: ");
                    String filterTag = scanner.next();
                    scheduler.filterJobsByTags(filterTag);
                    break;

                case 5:
                    System.out.print("Enter Job ID to adjust visibility: ");
                    String visibilityJobId = scanner.next();
                    System.out.print("Enter role: ");
                    String role = scanner.next();
                    scheduler.adjustVisibility(visibilityJobId, role);
                    break;

                case 6:
                    scheduler.generateSummaryReport();
                    break;

                case 7:
                    System.out.print("Enter Job ID to set alert: ");
                    String alertJobId = scanner.next();
                    System.out.print("Enter alert message: ");
                    String alertMessage = scanner.next();
                    scheduler.setDeadlineAlert(alertJobId, alertMessage);
                    break;

                case 8:
                    System.out.print("Enter action to log: ");
                    String action = scanner.next();
                    scheduler.logAction(action);
                    break;

                case 9:
                    scheduler.listJobs();
                    break;

                case 10:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }
}
