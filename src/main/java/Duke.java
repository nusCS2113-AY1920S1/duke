import java.util.Scanner;
import java.util.*; 

public class Duke {
        public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(true){  
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();; 
            //String s=String.valueOf(number);
            //Objects.equals("bye", command)
            String end =  "bye";
            
            if(end.equals(command)){ 
                System.out.println("Bye. Hope to see you again soon!");
                return; 
            }       
            else{ 
                System.out.println(command);
            }
            //l1.add(0, 1);  // adds 1 at 0 index 
            // adds 2 at 1 index 
            //System.out.println(l1);  // [1, 2] 
            //System.out.println(command);
        }


    }
}
