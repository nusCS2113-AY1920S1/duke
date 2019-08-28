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
        while(sc.hasNextLine() && !input.equalsIgnoreCase("bye"))
        {
            input = sc.nextLine();
            if(input.equalsIgnoreCase("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
            }
            else {
                System.out.println(input);
            }
        }

        sc.close();
    }
}
