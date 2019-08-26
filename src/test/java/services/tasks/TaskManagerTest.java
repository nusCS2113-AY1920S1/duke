package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest extends TestExtender {

    @Test
    public void testAddTask() {
        final String testString = "read book";
        provideInput(testString);
        TaskManager taskManager = new TaskManager();
        boolean status = taskManager.run();
        assertEquals(true, status);
        String output = getOutput();
        boolean isSubString = output.indexOf("added: read book") != -1;
        assertEquals(true, isSubString);
    }

    @Test
    public void testEndTask() {
        final String testString = "bye";
        provideInput(testString);
        TaskManager taskManager = new TaskManager();
        boolean status = taskManager.run();
        assertEquals(status, false);
    }

}
