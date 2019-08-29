import java.util.Scanner;
import java.util.ArrayList;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[✓] " : "[✗] "); //return tick or X symbols
    }

    public void done() {
        isDone = true;
    }

    public String toString() {
        return (this.getStatus() + description);
    }
}

class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Duke {
    /**
     * Why checkstyle liddat.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        ArrayList<Task> tasklist = new ArrayList<Task>();

        System.out.println(line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line);
        String typing = input.nextLine();

        while(!typing.equals("bye")) {
            int indexOfSpace = typing.indexOf(" ");
            String instruction = (indexOfSpace != -1) ? typing.substring(0, indexOfSpace) : typing;

            if (instruction.equals("todo")) {
                Task t = new Todo(typing.substring(indexOfSpace + 1));
                tasklist.add(t);
                System.out.println(line + "Got it. I've added this task: \n" + "  " + t);
                System.out.println("Now you have " + tasklist.size() + " task(s) in the list.\n" + line);
            }

            if (instruction.equals("deadline")) {
                int indexOfSlash = typing.indexOf("/");
                Task t = new Deadline(typing.substring(indexOfSpace + 1, indexOfSlash), typing.substring(indexOfSlash + 4));
                tasklist.add(t);
                System.out.println(line + "Got it. I've added this task: \n" + "  " + t);
                System.out.println("Now you have " + tasklist.size() + " task(s) in the list.\n" + line);
            }

            if (instruction.equals("event")) {
                int indexOfSlash = typing.indexOf("/");
                Task t = new Event(typing.substring(indexOfSpace + 1, indexOfSlash), typing.substring(indexOfSlash + 4));
                tasklist.add(t);
                System.out.println(line + "Got it. I've added this task: \n" + "  " + t);
                System.out.println("Now you have " + tasklist.size() + " task(s) in the list.\n" + line);
            }

            if (instruction.equals("done")) {
                int temp = Integer.parseInt(typing.substring(5));
                tasklist.get(temp - 1).done();
                System.out.print(line);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasklist.get(temp - 1) + "\n" + line);
            }

            if (instruction.equals("list")) {
                System.out.println(line + "Here are the tasks in your list: ");
                for (int i = 0; i < tasklist.size(); i += 1) {
                    System.out.print((i + 1) + ". ");
                    System.out.println(tasklist.get(i));
                }
                System.out.print(line);
            }

            typing = input.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
