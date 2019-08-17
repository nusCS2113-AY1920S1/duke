import java.util.Scanner;

public class Duke {

    public String getGreeting() {
        return "Hello! I'm Duke \nWhat can I do for you?";
    }

    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            else if (!input.isEmpty() && !input.isBlank()) {
                System.out.println(input);
            }
            else {
                System.out.println("Please do not leave any blanks");
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.getGreeting());
        duke.getInput();
    }
}
