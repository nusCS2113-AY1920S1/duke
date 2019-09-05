package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest extends TestExtender {

    @Test
    public void testTaskInit() throws Exception {
        Task task = new Task("borrow book", 1);
        String taskName = task.getTaskName();
        assertEquals("borrow book", taskName);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t added: borrow book\n"
                        + "\t____________________________________________________________\n"
                        + "\n";
        assertEquals(expected, output);
    }

    @Test
    public void testTaskName() throws Exception {
        Task task = new Task("borrow book", 1);
        String taskName = task.getTaskName();
        assertEquals("borrow book", taskName);
    }

    @Test
    public void testTaskStatus() throws Exception {
        Task task = new Task("added book", 1);
        boolean doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
    }

    @Test
    public void testsetTaskDone() throws Exception {
        Task task = new Task("added book", 1);
        boolean doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
        task.setDoneStatus(true);
        doneStatus = task.getIsDoneStatus();
        assertEquals(true, doneStatus);
        task.setDoneStatus(false);
        doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
    }

}
