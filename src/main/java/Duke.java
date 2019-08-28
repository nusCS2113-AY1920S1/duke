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
        ArrayList<String> tasks =new ArrayList<String>();
        while(sc.hasNextLine() && !input.equalsIgnoreCase("bye"))
        {
            input = sc.nextLine();
            if(input.equalsIgnoreCase("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
            }
            if(input.equals("list"))
            {
                int counter = 1;
                for(String task:tasks)
                    System.out.println(counter++ +". " + task);
            }
            else {
                tasks.add(input);
                System.out.println("added: " + input);
            }

        }

        sc.close();
    }

}
