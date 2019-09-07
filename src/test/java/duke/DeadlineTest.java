package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testToString() {
        Deadline newDeadline = new Deadline("I need to test the code", "20/11/2019 1800");
        assertEquals("[D]" + "[\u2718]" + " " + "I need to test the code (by: tonight)", newDeadline.toString());
    }

    @Test
    void getStatusIcon() {
        Deadline newDeadline = new Deadline("I need to test the code", "20/11/2019 1800");
        assertEquals("\u2718", newDeadline.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Deadline newDeadline = new Deadline("I need to test the code", "20/11/2019 1800");
        newDeadline.markAsDone();
        assertEquals("\u2713", newDeadline.getStatusIcon());
    }
}
