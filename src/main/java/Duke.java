import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String command = new String();
        ArrayList<Task> list =new ArrayList<Task>();
        while(sc.hasNextLine() && !command.equalsIgnoreCase("bye"))
        {

            command = sc.next();
            boolean inputExists = true;
            String input = sc.nextLine();
            if (list.equals(command)) {
                int counter = 1;
                for (Task task : list) {
                    System.out.println(counter++ + ". " + task.toList());
                }
            }
            else {
                try {
                    input = input.substring(1);
                } catch (StringIndexOutOfBoundsException oob) {
                    if (!command.equals("list")) {
                        System.out.println("there was nothing after the command");
                    }
                    inputExists = false;
                    //throw error time: there was nothing after the command
                }
            }
            if (command.equals("todo")) {
                list.add(new Todo(input));
            } else if (command.equals("deadline")) {
                list.add(new Deadline(input));
            } else if (command.equals("event")) {
                list.add(new Event(input));
            } else if (command.equals("done")) {
                int request = Integer.parseInt(input);
                request -= 1;
                list.get(request).markDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "  " + list.get(request).toList());
            } else {
                System.out.println("I don't understand you");
            }
        }

        sc.close();
    }

}
