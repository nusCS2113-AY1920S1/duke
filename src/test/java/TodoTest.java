import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        Task todo = new Todo(false,"Buy Eggs");
        assertEquals(todo.toString(), "[T][✗] Buy Eggs");
    }
}