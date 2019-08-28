import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        while(true){
            String input = scan.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            else{
                System.out.println(input + "\n");
            }
        }
    }
}
