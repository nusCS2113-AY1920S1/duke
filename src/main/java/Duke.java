import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<String> array = new ArrayList<>();
        ArrayList<String> checkbox = new ArrayList<>();

        String input;
        Scanner scan = new Scanner( System.in );
        input = scan.nextLine();
        int counter = 0;
        while(!input.equals("bye")){

            //List all saved contents in array
            if(input.equals("list")){
                for(int i=0; i<array.size(); i++)
                    System.out.println((i+1) + ".[" + checkbox.get(i) + "] " + array.get(i));
            }

            //Check for whether user wants to mark as done or add more input
            else{
                if(input.length()>4 && input.substring(0,4).equals("done")){
                    String digit = input.replace("done ", "");
                    int num = Integer.parseInt(digit);
                    checkbox.set(num-1, "\u2713");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[\u2713] " + array.get(num-1));

                }
                else{
                    array.add(counter, input);
                    checkbox.add(counter, "\u2718");
                    counter++;
                    System.out.println("added: " + input);
                }
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}