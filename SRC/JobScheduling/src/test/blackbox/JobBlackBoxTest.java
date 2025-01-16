package test.blackbox;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ischedule.Job;
import ischedule.JobEntryValidator;

public class JobBlackBoxTest {

    @Test
    public void testValidJobEntry() {
        Job job = new Job("1", "Design", 5);
        assertTrue(JobEntryValidator.validateNewJob(job));
    }

    @Test
    public void testJobEntryWithEmptyName() {
        Job job = new Job("2", "", 4);
        assertFalse(JobEntryValidator.validateNewJob(job));
    }

    @Test
    public void testJobEntryWithNullName() {
        Job job = new Job("3", null, 3);
        assertFalse(JobEntryValidator.validateNewJob(job));
    }

    @Test
    public void testJobEntryWithZeroDuration() {
        Job job = new Job("4", "Development", 0);
        assertFalse(JobEntryValidator.validateNewJob(job));
    }

    @Test
    public void testJobEntryWithNegativeDuration() {
        Job job = new Job("5", "Testing", -1);
        assertFalse(JobEntryValidator.validateNewJob(job));
    }

    @Test
    public void testRandomJobEntry() {
        Job job = new Job("6", "RandomTask" + Math.random(), (int) (Math.random() * 10));
        assertEquals(job.getJobName().length() > 0 && job.getJobDuration() > 0, JobEntryValidator.validateNewJob(job));
    }
}
