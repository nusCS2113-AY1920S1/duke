import myduke.task.Deadline;
import myduke.task.Event;
import myduke.task.MyDuke;
import myduke.task.ToDo;

import java.io.*;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        MyDuke duke = new MyDuke();

        duke.runDuke();
    }
}
