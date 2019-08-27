import java.util.*;
public class Parser  {
    private ArrayList<Task> l1;  
    public Parser() {
        l1 = new ArrayList<Task>();    
    }
    public static String get_end_message(Integer num){ 
        String task_num;
        if(num==1){ 
            task_num = "task";
        }
        else{ 
            task_num = "tasks";
        }
        return "Now you have " + Integer.toString(num)+ " " + task_num+ " in the list.";
    }
    public void print_list(){ 
        int i = 0;
        for (Task temp : l1) {
            System.out.print(i+1);
            System.out.print(".");
            //boolean status = temp.get_status();
            System.out.println(temp);
            i +=1;
		}
    }
    public void bye(){ 
        System.out.println("Bye. Hope to see you again soon!");
        return;

    }
    public void done(int index){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  [✓] "+l1.get(index-1).get_name());
        l1.get(index-1).change_status(true);
    }
    public static String get_end_message(int num){ 
        String task_num;
        if(num==1){ 
            task_num = "task";
        }
        else{ 
            task_num = "tasks";
        }
        return "Now you have " + Integer.toString(num)+ " " + task_num+ " in the list.";
    }
    public void create_todo(String command){ 
        command = command.split(" ",2)[1];
        Task c1 = new Todo(false,command);
        l1.add(c1);
        System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
        System.out.println(get_end_message(l1.size()));
    }
    public void create_events(String work){ 
        String[] tasks = work.split("\\/at");
        String task_to_be_done = tasks[0];
        String deadline_time = tasks[1];
        Task c1 = new Events(false,task_to_be_done,deadline_time);
        l1.add(c1);
        System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
        System.out.println(get_end_message(l1.size()));
    }
    public void create_deadline(String work){ 
        String[] tasks = work.split("\\/by");
        String task_to_be_done = tasks[0];
        String deadline_time = tasks[1];
        Task c1 = new Deadline(false,task_to_be_done,deadline_time);
        l1.add(c1);
        System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
        System.out.println(get_end_message(l1.size()));
    }
    
}