package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest extends TestExtender {

    @Test
    public void testTodosInit() throws Exception {
        Todos task = new Todos("todo borrow book");
        String taskName = task.getTaskName();
        assertEquals("borrow book", taskName);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [T][✗] borrow book\n"
                        + "\t Now you have 3 tasks in the list.\n"
                        + "\t____________________________________________________________\n"
                        + "\n";
        assertEquals(expected, output);
    }

    @Test
    public void testTodosGetTaskName() throws Exception {
        Todos task = new Todos("todo borrow book");
        String taskName = task.getTaskName();
        assertEquals("borrow book", taskName);
    }

    @Test
    public void testTodosGetIsDoneStatus() throws Exception {
        Todos task = new Todos("todo borrow book");
        boolean doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
    }

    @Test
    public void testTodosToString() throws Exception {
        Todos task = new Todos("todo borrow book");
        String objectString = task.toString();
        String expected = "\t____________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [T][✗] borrow book\n"
                        + "\t Now you have 4 tasks in the list.\n"
                        + "\t____________________________________________________________\n";
        assertEquals(expected, objectString);
        task.setDoneStatus(true);
        objectString = task.toString();
        expected = "\t____________________________________________________________\n"
                 + "\t Got it. I've added this task:\n"
                 + "\t   [T][✓] borrow book\n"
                 + "\t Now you have 4 tasks in the list.\n"
                 + "\t____________________________________________________________\n";
        assertEquals(expected, objectString);
        task.setDoneStatus(false);
        objectString = task.toString();
        expected = "\t____________________________________________________________\n"
                 + "\t Got it. I've added this task:\n"
                 + "\t   [T][✗] borrow book\n"
                 + "\t Now you have 4 tasks in the list.\n"
                 + "\t____________________________________________________________\n";
        assertEquals(expected, objectString);
    }

    @Test
    public void testTodosSetTaskDone() throws Exception {
        Todos task = new Todos("todo borrow book");
        boolean doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
        task.setDoneStatus(true);
        doneStatus = task.getIsDoneStatus();
        assertEquals(doneStatus, true);
        task.setDoneStatus(false);
        doneStatus = task.getIsDoneStatus();
        assertEquals(false, doneStatus);
    }

}
