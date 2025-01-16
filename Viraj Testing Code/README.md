Testing of Unit Job Scheduling Application

The application was tested using two primary methodologies:

White Box Testing: Focused on verifying the internal logic and individual methods of the application.

Black Box Testing: Focused on validating the application’s behavior and outputs without considering its internal implementation.

The testing ensured all core functionalities, including job addition, finalization, tag assignment, and report generation, perform as expected.

Testing Methodologies

1. White Box Testing

White box testing was conducted using the teststories class. This approach tested the internal logic of the application’s methods, ensuring they behave as intended.

Key Areas Tested:

Job Addition: Ensured jobs are added correctly with valid details (ID, name, and tags).

Job Finalization: Verified that finalized jobs cannot be modified.

Tag Assignment: Confirmed finalized jobs cannot have tags reassigned.

Unique Job ID Generation: Checked IDs are generated correctly based on user roles (Admin, Manager, Developer, etc.).

Tag-Based Filtering: Tested filtering logic to ensure only jobs with specified tags are displayed.

2. Black Box Testing

Black box testing was performed using the teststory1 class. This approach validated the application’s outputs and interactions without considering its internal logic.

Key Areas Tested:

Adding Jobs: Ensured jobs are created and initialized successfully.

Finalizing Jobs: Tested the behavior when attempting to finalize jobs multiple times.

Non-Existent Job Operations: Checked error handling when assigning tags to or finalizing non-existent jobs.

Filtering by Tags: Validated the application’s response to filtering with existing and non-existent tags.

Summary Report Generation: Ensured accurate summaries of total, finalized, and pending jobs.

Test Execution

Test Classes

teststories: Implements white box tests targeting individual methods in the application.

teststory1: Implements black box tests focusing on the application’s external behavior.

*Steps to Execute Tests*

Import the org.junit.jupiter.api library into your project.

Open the test classes (teststories and teststory1) in your IDE (e.g., Eclipse).

Right-click the test file and select Run As > JUnit Test.

View test results in the JUnit Console:

Green Bar: All tests passed successfully.

Red Bar: Test failures (details available in the console output).

Test Coverage

Features Tested

Job Addition: Validated with various input scenarios.

Finalization: Ensured jobs cannot be finalized or modified multiple times.

Tag Filtering: Tested for both valid and invalid tag inputs.

Summary Reports: Checked for accurate data representation.

Error Handling: Confirmed appropriate error messages are displayed for invalid operations.

Test Results

The tests verified the following:

Jobs are added, finalized, and tagged as expected.

Tag filtering operates correctly, displaying jobs with specified tags only.

The system gracefully handles errors for invalid operations, such as finalizing a non-existent job.

Summary reports accurately reflect the job statuses.
