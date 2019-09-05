package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeadlinesTest extends TestExtender {

    @Test
    void testTodoISODate() {
        new Deadlines("borrow book /by 2/2/2019 1900", 1);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [D][✗] borrow book (by: 2nd of February 2019, 7pm)\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t____________________________________________________________\n";
        assertEquals(expected, output);
    }

    @Test
    void testTodo() {
        new Deadlines("borrow book /by Sunday", 1);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t   [D][✗] borrow book (by: Sunday)\n"
                + "\t Now you have 1 tasks in the list.\n"
                + "\t____________________________________________________________\n";
        assertEquals(expected, output);
    }

    @Test
    void testGetTaskName() {
        Deadlines task = new Deadlines("borrow book /by Sunday",1);
        String taskName = task.getTaskName();
        assertEquals("borrow book /by Sunday", taskName);
    }

    @Test
    void testGetTaskNameISODate() {
        Deadlines task = new Deadlines("borrow book /by 2/2/2019 1900",1);
        String taskName = task.getTaskName();
        assertEquals("borrow book", taskName);
    }

    @Test
    void testGetIsDoneStatus() {
        Deadlines task = new Deadlines("borrow book /by Sunday", 1);
        boolean doneStatus = task.getIsDoneStatus();
        assertFalse(doneStatus);
    }

}
