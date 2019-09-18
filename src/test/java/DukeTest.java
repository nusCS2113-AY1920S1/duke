import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        Task todo = new Deadline(false,"assignment","monday 2359");
        System.out.println(todo.toString());
        assertEquals(todo.toString(), "[D][✗] assignment (by: monday 2359)");
    }
}