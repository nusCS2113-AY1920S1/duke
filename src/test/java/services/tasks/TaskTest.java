package com.nwjbrandon.duke.services.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskInit() {
        Task task = new Task("added book");
        String s = task.getTaskName();
        assertEquals(s, "added book");
    }

}
