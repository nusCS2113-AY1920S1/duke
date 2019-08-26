import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Duke! What can I do for you today?");
        input = scanner.nextLine();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }

        {
            System.out.println("Bye. See you again soon!");
        }

    }
//        if(input == "bye"){
//            System.out.println("Bye. Hope to see you again soon!\n");
//        }
//        else{
//            System.out.println( input );
//        }
    }
