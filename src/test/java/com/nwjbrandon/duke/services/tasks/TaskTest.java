package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest extends TestExtender {

    @Test
    void testTask() {
        new Task("borrow book", 1);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t added: borrow book\n"
                        + "\t____________________________________________________________\n";
        assertEquals(expected, output);
    }

    @Test
    void testTaskName() {
        Task task = new Task("borrow book", 1);
        String taskName = task.getTaskName();
        assertNull(taskName);
    }

    @Test
    void testTaskStatus() {
        Task task = new Task("added book", 1);
        boolean doneStatus = task.getIsDoneStatus();
        assertFalse(doneStatus);
    }

    @Test
    void testsetTaskDone() {
        Task task = new Task("added book", 1);
        boolean doneStatus = task.getIsDoneStatus();
        assertFalse(doneStatus);
        task.setDoneStatus(true);
        doneStatus = task.getIsDoneStatus();
        assertTrue(doneStatus);
        task.setDoneStatus(false);
        doneStatus = task.getIsDoneStatus();
        assertFalse(doneStatus);
    }

}
