Unit Job Scheduling Application - Code Overview

Job

JobScheduler

Main

Each class is designed with a specific responsibility in mind, adhering to modular and object-oriented programming principles.

Code Structure

1. Job Class

Purpose: Represents a single job in the scheduling system.

Key Attributes:

jobId: Unique identifier for each job (e.g., "ADM-001").

jobName: The name of the job (e.g., "TaskA").

tags: A list of descriptive tags for categorizing jobs (e.g., ["urgent", "review"]).

finalized: A boolean indicating if the job has been finalized.

visibleToRole: Specifies the role that can view the job.

deadlineAlert: Stores a deadline alert message for the job.


2. JobScheduler Class

Purpose: Handles all operations related to job management, including adding jobs, finalizing jobs, filtering by tags, and generating reports.

Key Attributes:

jobs: A list to store all jobs in the system.

idCounter: A counter used to generate unique job IDs.

Key Methods:

addJob(Job job): Adds a new job to the list and logs a confirmation message.

finalizeJob(String jobId): Marks a job as finalized, preventing further modifications.

assignTags(String jobId, List tags): Assigns or updates tags for a specified job.

filterJobsByTags(String tag): Displays jobs that contain a specific tag.

generateSummaryReport(): Generates a summary of all jobs, including counts of finalized and pending jobs.

generateJobId(String role): Creates a unique job ID based on the user's role (e.g., "ADM", "MGR").

setDeadlineAlert(String jobId, String alertMessage): Adds a deadline alert message to a job.

listJobs(): Displays all jobs in the scheduler.

3. Main Class

Purpose: Acts as the entry point for the application. It provides a menu-driven interface for users to interact with the JobScheduler.

Key Features:

Menu Options:

1. Add a Job

2. Finalize Scheduled Job

3. Assign Tags to Job

4. Filter Jobs by Tag

5. Adjust Job Visibility by Role

6. Generate Summary Report

7. Set Deadline Alert

8. Log User Actions

9. List Jobs

10. Exit

 After running the Main class, the console will display this following menu
 Here is an example interaction for this-
 Enter your choice: 1
Enter your role (Admin, Manager, Developer, User): Admin
Generated Job ID: ADM-100
Enter Job Name: DesignTask
Enter Tags (comma-separated): urgent,review
Job added successfully: DesignTask

Enter your choice: 6
Summary Report:
Total Jobs: 1
Finalized Jobs: 0
Pending Jobs: 1

Enter your choice: 10
Exiting... Goodbye!
