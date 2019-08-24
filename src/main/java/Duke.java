import java.util.Scanner;
import java.util.*;
//this is the Task class
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
            String show_data = "list";
            String done = "done";
            if(end.equals(command)){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if(show_data.equals(command)){
                print_list(l1);
                //System.out.println(l1);
            }
            else{
                String[] values = command.split(" ");
                //marking as done
                if(done.equals(values[0])){
                    System.out.println("Nice! I've marked this task as done: ");
                    int index =  Integer.parseInt(values[1]);
                    System.out.println("  [✓] "+l1.get(index-1).get_name());



                    l1.get(index-1).change_status(true);
                }
                else{

                    Task c1 = new Task(false,command);
                    l1.add(c1);
                    System.out.println("added: " + command);
                }

            }

            //l1.add(0, 1);  // adds 1 at 0 index
            // adds 2 at 1 index
            //System.out.println(l1);  // [1, 2]
            //System.out.println(command);
        }


    }
}
