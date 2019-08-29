import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Parser  {
    private ArrayList<Task> l1;  
    private ArrayList<Task> temp;
    public void deserial() throws  FileNotFoundException,IOException{ 
        FileInputStream fstream = new FileInputStream("tasks.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
            //System.out.println(strLine);
            String[] string_list = strLine.split("\\^");
            //System.out.println(Arrays.toString(string_list)); 
            if(string_list[0].equals("T")){
                boolean t; 
                if(string_list[1].equals("1")){ 
                    t= true; 
                } 
                else{ 
                    t = false;
                }
                //System.out.println("hello");
                Task temp = new Todo(t,string_list[2]);
                l1.add(temp); 
            }
            else if(string_list[0].equals("E")){ 
                boolean t; 
                if(string_list[1].equals("1")){ 
                    t= true; 
                } 
                else{ 
                    t = false;
                }
                Task temp = new Events(t,string_list[2],string_list[3]); 
                l1.add(temp); 
            }
            else if(string_list[0].equals("D")){ 
                boolean t; 
                if(string_list[1].equals("1")){ 
                    t= true; 
                } 
                else{ 
                    t = false;
                }
                Task temp = new Deadline(t,string_list[2],string_list[3]); 
                l1.add(temp); 
            }
        }
        fstream.close();
    }  
    public Parser() throws FileNotFoundException,IOException{
        l1 = new ArrayList<Task>(); 
        deserial();
        //System.out.println("jjdaj");
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
    public void done(int index) throws DukeException{
        System.out.println("Nice! I've marked this task as done: ");
        if(index <= 0 || index > l1.size()){ 
            String message = "☹ OOPS!!! The index doesnt exists! Please check again!:(";
            throw new DukeException(message);
        }
        else{ 
            System.out.println("  [✓] "+l1.get(index-1).get_name());
            l1.get(index-1).change_status(true);
        }   
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
    public void create_todo(String command) throws DukeException{ 
        String[] command_list = command.split(" ",2);
        if(command.split(" ",2).length>= 2 && !(command_list[1].trim().equals("")) ){ 
            command = command.split(" ",2)[1];
            Task c1 = new Todo(false,command);
            l1.add(c1);
            System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
            System.out.println(get_end_message(l1.size()));
        }
        else{ 
            String message = "☹ OOPS!!! The description of a todo cannot be empty.";
            throw new DukeException(message); 
        }
    }
    public void create_events(String work) throws DukeException{ 
        String[] tasks = work.split("\\/at");
        if(tasks.length >=2 && !(tasks[1].trim().equals("")) ){ 
            String task_to_be_done = tasks[0];
            String deadline_time = tasks[1];
            Task c1 = new Events(false,task_to_be_done,deadline_time);
            l1.add(c1);
            System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
            System.out.println(get_end_message(l1.size()));
        }
        else{ 
            String message = "☹ OOPS!!! The description of a events should have a time! ";
            throw new DukeException(message); 
        }
        
    }
    public void create_deadline(String work) throws DukeException{ 
        String[] tasks = work.split("\\/by");
        if(tasks.length>= 2 && !(tasks[1].trim().equals(""))){ 
            String task_to_be_done = tasks[0];
            String deadline_time = tasks[1];
            Task c1 = new Deadline(false,task_to_be_done,deadline_time);
            l1.add(c1);
            System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
            System.out.println(get_end_message(l1.size()));
        }
        else{ 
            String message = "☹ OOPS!!! The description of a deadline should have a deadline timing! ";
            throw new DukeException(message); 
        }
       
    }
    public void serial() throws Exception{ 
        try{ 
            PrintWriter out = new PrintWriter("tasks.txt");
            String serialized = ""; 
            for (Task temp : l1) {
                serialized += temp.get_attrib() +"\n"; 
            }
            out.println(serialized);
            out.close ();    
        }
        catch (Exception e){ 
            throw e; 
        }
    }
}