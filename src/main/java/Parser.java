import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Parser  {
    //private ArrayList<Task> l1;  
    private ArrayList<Task> temp;
    private TaskList task_1;
    private Storage writer; 
    public String delete(int index) throws DukeException, Exception{
        if(index >= 1 && index <= task_1.get_size()){ 
            Task deleted = task_1.get_index(index-1);
            task_1.delete(index); 
            String message =  "Noted. I've removed this task: \n  "+ deleted +"\n"+get_end_message(task_1.get_size());
                   
            
            serial();
            return message;
        }
        else{ 
            throw new DukeException("Please give a valid task index to delete!");
        }
    }
    
    public Parser(TaskList t1,Storage writer1) throws FileNotFoundException,IOException,DukeException{
        task_1 = t1; 
        writer = writer1;
        //deserial();
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
        task_1.print_list();
    }
    public String find(String keyword){ 
       return task_1.find(keyword);
    }
    public void bye(){ 
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }
    public void done(int index) throws DukeException{
        System.out.println("Nice! I've marked this task as done: ");
        if(index <= 0 || index > task_1.get_size()){ 
            String message = "☹ OOPS!!! The index doesnt exists! Please check again!:(";
            throw new DukeException(message);
        }
        else{ 
            System.out.println("  [✓] "+task_1.get_index(index-1).get_name());
            task_1.change_status(index-1,true);
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
        //System.out.println("inside create todo");
        if(command.split(" ",2).length>= 2 && !(command_list[1].trim().equals("")) ){ 
            command = command.split(" ",2)[1];

            Task c1 = new Todo(false,command);
            task_1.add_items(c1);
            //System.out.println("inside create todo after splitting");
            System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
            System.out.println(get_end_message(task_1.get_size()));
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
            Task c1;
            try{ 
                //LocalDateTime date_type = new ParseTime().parseStringToDate(deadline_time.trim());
                //System.out.println("Before : " + date_type);
                String [] timing_list= deadline_time.split("-");

                if(timing_list.length >= 2 && !(timing_list[1].trim().equals(""))){

                    //System.out.println(Arrays.toString(timing_list)); 

                   // System.out.println(Arrays.toString(timing_list)); 
                    String[] string_list = {task_to_be_done,task_to_be_done,deadline_time};
                    c1 = writer.get_first_e(string_list, false);
                    task_1.add_items(c1);
                    //System.out.println(c1);
                }
                else{ 
                    
                    throw new DukeException("Please enter the start and end time");
                }
                
            }
            catch (DukeTimeException e){ 
            System.out.println(e.getMessage()); 
               throw e; 
            }
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            //LocalDateTime dt = LocalDateTime.parse(deadline_time.trim(),formatter);
            //System.out.println("Before : " + dt);
           
            System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
            System.out.println(get_end_message(task_1.get_size()));
        }
        else{ 
            String message = "☹ OOPS!!! The description of a events should have a time! ";
            throw new DukeException(message); 
        }
        
    }
    public void create_deadline(String work) throws DukeException{ 
        String[] tasks = work.split("\\/by");
        //System.out.println(Arrays.toString(tasks)+tasks[1].trim());
        if(tasks.length>= 2 && !(tasks[1].trim().equals(""))){ 
            //System.out.println("hello1");
            String task_to_be_done = tasks[0];
            String deadline_time = tasks[1];
            //Task c1 = new Deadline(false,task_to_be_done,deadline_time);
            Task c1;
            try{ 
                LocalDateTime date_type = new ParseTime().parseStringToDate(deadline_time.trim());
                //System.out.println("Before : " + date_type);
                c1 = new Deadline(false,task_to_be_done,date_type,deadline_time.trim());
            }
            catch (DukeTimeException e){ 
                c1 = new Deadline(false,task_to_be_done,deadline_time);
            }
            task_1.add_items(c1);
            System.out.println("Got it. I've added this task: \n"+"     "+c1.toString());
            System.out.println(get_end_message(task_1.get_size()));
        }
        else{ 
            String message = "☹ OOPS!!! The description of a deadline should have a deadline timing! ";
            throw new DukeException(message); 
        }
       
    }
    public void serial(){ 
        try{ 
            writer.serial(task_1.get_list());
        }
        catch(Exception e){ 
            System.out.println(e);
        }
    }
}