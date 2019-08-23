import java.util.Scanner;
import java.util.*; 

public class Duke {
        public static void print_list(List<String> list1){ 
            int i = 0; 
            for (String temp : list1) {
                System.out.print(i+1);
                System.out.print("."); 
                System.out.println(temp);
                i +=1; 
            }
            //System.out.println(list1);
        }
        public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        List<String> l1 = new ArrayList<String>(); 
        while(true){  
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();; 
            //String s=String.valueOf(number);
            //Objects.equals("bye", command)
            String end =  "bye";
            String show_data = "list";
            if(end.equals(command)){ 
                System.out.println("Bye. Hope to see you again soon!");
                return; 
            }       
            else if(show_data.equals(command)){ 
                print_list(l1);
            }
            else{ 
                l1.add(command);
                System.out.print("added: ");
                System.out.println(command);
            }
            //l1.add(0, 1);  // adds 1 at 0 index 
            // adds 2 at 1 index 
            //System.out.println(l1);  // [1, 2] 
            //System.out.println(command);
        }


    }
}
