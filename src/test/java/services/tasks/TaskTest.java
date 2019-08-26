package com.nwjbrandon.duke.services.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskInitialize() {
        Task task = new Task("added book");
        String taskName = task.getTaskName();
        assertEquals("added book", taskName);
    }

    @Test
    public void testTaskStatus() {
        Task task = new Task("added book");
        boolean doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
    }

    @Test
    public void testsetTaskDone() {
        Task task = new Task("added book");
        task.setDoneStatus(true);
        boolean doneStatus = task.getIsDoneStatus();
        assertEquals(doneStatus, true);
        task.setDoneStatus(false);
        boolean notDoneStatus = task.getIsDoneStatus();
        assertEquals(false, notDoneStatus);
    }

}
