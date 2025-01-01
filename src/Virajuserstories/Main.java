package jobscheduling;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Job");
            System.out.println("2. Mark Job as Completed");
            System.out.println("3. Assign Tags to Job");
            System.out.println("4. Filter Jobs by Tag");
            System.out.println("5. Adjust Job Visibility by Role");
            System.out.println("6. Generate Summary Report");
            System.out.println("7. Set Deadline Alert");
            System.out.println("8. List Jobs");
            System.out.println("9. Log User Actions");
            System.out.println("10. Auto-Generated Job IDs");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Job ID: ");
                    String jobId = scanner.next();
                    System.out.print("Enter Job Name: ");
                    String jobName = scanner.next();
                    System.out.print("Enter Priority: ");
                    String priority = scanner.next();
                    System.out.print("Enter Start Time: ");
                    String startTime = scanner.next();
                    System.out.print("Enter End Time: ");
                    String endTime = scanner.next();
                    scheduler.addJob(new Job(jobId, jobName, priority, startTime, endTime, null, jobId));
                    break;

                case 2:
                    System.out.print("Enter Job ID to mark as completed: ");
                    String completeJobId = scanner.next();
                    scheduler.markJobAsCompleted(completeJobId);
                    break;

                case 3:
                    System.out.print("Enter Job ID to assign tags: ");
                    String assignJobId = scanner.next();
                    System.out.print("Enter tags (comma-separated): ");
                    String tagsInput = scanner.next();
                    List<String> tags = new ArrayList<>();
                    for (String tag : tagsInput.split(",")) {
                        tags.add(tag.trim());
                    }
                    scheduler.assignTags(assignJobId, tags);
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
                    scheduler.listJobs();
                    break;

                case 9:
                    scheduler.logAction("User performed an action");
                    break;

                case 10:
                    String generatedId = scheduler.generateJobId();
                    System.out.println("Generated Job ID: " + generatedId);
                    break;

                case 11:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
