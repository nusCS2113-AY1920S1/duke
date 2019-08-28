package com.nwjbrandon.duke;

import com.nwjbrandon.duke.TestExtender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest extends TestExtender {

    @Test
    public void testCase1() throws Exception {
        provideInput("bye");
        Duke.main(new String[0]);
        String output = getOutput();
        String expected = "\t____________________________________________________________\n"
                        + "\t Hello! I'm Duke\n"
                        + "\t What can I do for you?\n"
                        + "\t____________________________________________________________\n"
                        + "\n"
                        + "\t____________________________________________________________\n"
                        + "\t Bye. Hope to see you again soon!\n"
                        + "\t____________________________________________________________\n"
                        + "\n";
        assertEquals(expected, output);
    }

}
