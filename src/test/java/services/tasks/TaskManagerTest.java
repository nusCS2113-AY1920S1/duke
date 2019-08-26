// https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
package com.nwjbrandon.duke.services.tasks;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

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
