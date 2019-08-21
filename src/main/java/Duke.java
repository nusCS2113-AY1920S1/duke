import java.util.Scanner;

public class Duke {
    private final static String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE = "________________________________________________";
    private final static String STRING_VAR_HOLDER = "coded_start";
    public static void main(String[] args) {
        initialise();
    }

    private static void initialise() {
        // Welcome Message
        System.out.println("Hello from the other sideeeeeeeeeeee\n" + LOGO); // Logo
        System.out.println("Duke: Hello! I'm Duke.\nDuke: What can I do for you?");
        System.out.println(LINE);
        // Begin taking in input
        Scanner scanner = new Scanner(System.in);
        String user_input;
        while (true) {
            System.out.print("User: ");
            user_input = scanner.nextLine();
            if (user_input.equals("bye")) { // Only exit if user sends "bye" in lowercase
                System.out.println("Duke: Bye. Hope to see you again soon!");
                break;
            } else { // Echo input
                System.out.println("Duke: " + user_input +"\n");
            }
        }

        System.out.println(LINE);


    }
}
