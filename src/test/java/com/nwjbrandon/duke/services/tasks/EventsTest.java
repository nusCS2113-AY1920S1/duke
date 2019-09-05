package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EventsTest extends TestExtender {

    @Test
    void testTodoIsoDate() {
        new Events("borrow book /at 2/2/2019 1900", 1);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [E][✗] borrow book (at: 2nd of February 2019, 7pm)\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t____________________________________________________________\n";
        assertEquals(expected, output);
    }

    @Test
    void testTodo() {
        new Events("borrow book /at Sunday", 1);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t   [E][✗] borrow book (at: Sunday)\n"
                + "\t Now you have 1 tasks in the list.\n"
                + "\t____________________________________________________________\n";
        assertEquals(expected, output);
    }

    @Test
    void testGetTaskName() {
        Events task = new Events("borrow book /at Sunday",1);
        String taskName = task.getTaskName();
        assertEquals("borrow book /at Sunday", taskName);
    }

    @Test
    void testGetTaskNameIsoDate() {
        Events task = new Events("borrow book /at 2/2/2019 1900",1);
        String taskName = task.getTaskName();
        assertEquals("borrow book", taskName);
    }

    @Test
    void testGetIsDoneStatus() {
        Events task = new Events("borrow book /at Sunday", 1);
        boolean doneStatus = task.getIsDoneStatus();
        assertFalse(doneStatus);
    }

}
