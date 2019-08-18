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

        String input;
        Scanner scan = new Scanner( System.in );
        input = scan.nextLine();
        int counter = 0;
        while(!input.equals("bye")){
            if(input.equals("list")){
                for(int i=0; i<array.size(); i++)
                    System.out.println((i+1) + ". " + array.get(i));
            }
            else{
                array.add(counter, input);
                counter++;
                System.out.println("added: " + input);
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}