package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest extends TestExtender {

    @Test
    public void testAddTask() {
        final String testString = "todo borrow book";
        provideInput(testString);
        TaskManager taskManager = new TaskManager();
        taskManager.run();
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [T][✗] borrow book\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t____________________________________________________________\n"
                        + "\n";
        assertEquals(expected, output);
    }

    @Test
    public void testEndTask() {
        final String testString = "bye";
        provideInput(testString);
        TaskManager taskManager = new TaskManager();
        boolean status = taskManager.run();
        assertEquals(false, status);
    }

}
