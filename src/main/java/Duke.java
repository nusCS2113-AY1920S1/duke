import java.util.Scanner;
import java.util.*; 

class Task{ 
    private boolean status; 
    private String name; 
    public Task(boolean to_add_status, String name_to_add){ 
        status = to_add_status; 
        name = name_to_add; 
    }
    public void change_status(boolean to_change_status){ 
        status  = to_change_status;  
    }
    public void change_task(String to_change_task){ 
        name = to_change_task;
    }
    public boolean get_status(){
        return status; 
    }
    public String get_name(){ 
        return name; 
    }
}

public class Duke {
    public static void print_list(List<Task> list1){ 
        int i = 0; 
        for (Task temp : list1) {
            System.out.print(i+1);
            System.out.print("."); 
            boolean status = temp.get_status(); 
            if(status == true){ 
                System.out.print("[✓] ");
            }
            else{
                System.out.print("[✗] ");
            }
            System.out.println(temp.get_name());
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
        List<Task> l1 = new ArrayList<Task>(); 
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
