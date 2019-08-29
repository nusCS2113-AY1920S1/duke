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
        String input = new String();
        ArrayList<Task> list =new ArrayList<Task>();
        while(sc.hasNextLine() && !input.equalsIgnoreCase("bye"))
        {
            input = sc.nextLine();
            String[] split = input.split(" ");
            if(input.equalsIgnoreCase("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
            }
            if(input.equals("list"))
            {
                int counter = 1;
                for(Task task:list)
                    System.out.println(counter++ +". [" + task.getStatusIcon() +  "] "+ task.getDescription());
            }
            if(split.length>1)
            {
                if(split[0].equals("done"))
                {
                    int request = Integer.parseInt(split[1]);
                    request -= 1;
                    list.get(request).markDone();
                    System.out.println("Nice! I've marked this task as done:\n" +
                            "  [" + list.get(request).getStatusIcon() + "] " + list.get(request).getDescription());
                }
                else
                {
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                }
            }
            else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }

        sc.close();
    }

}
