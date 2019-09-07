package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testToString() {
        Event newEvent = new Event("project meeting", "20/11/2019 1800");
        assertEquals("[E]" + "[\u2718]" + " " + "project meeting (at: 7th May)", newEvent.toString());
    }

    @Test
    void getStatusIcon() {
        Event newEvent = new Event("project meeting", "20/11/2019 1800");
        assertEquals("\u2718", newEvent.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Event newEvent = new Event("project meeting", "20/11/2019 1800");
        newEvent.markAsDone();
        assertEquals("\u2713", newEvent.getStatusIcon());
    }
}
