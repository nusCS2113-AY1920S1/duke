import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Parser  {
    private ArrayList<Task> l1;  
    private ArrayList<Task> temp;
    public static Task get_first_e(String[] string_list,boolean t) throws DukeException{ 
        Task c1;
        //System.out.println("help me");
        //System.out.println("here "+Arrays.toString(string_list));
        try{ 
            String[] timing = string_list[2].split("-");
            if(timing.length>= 2 && !(timing[1].trim().equals("")) ){ 
                //System.out.println(Arrays.toString(timing)); 
                LocalDateTime start_date = new ParseTime().parseStringToDate(timing[0].trim());
                LocalDateTime end_date =  new ParseTime().parseStringToDate(timing[1].trim());
                //System.out.println("Before : " + date_type);
                c1 = new Events(t,string_list[1],start_date,end_date,timing[0],timing[1]);
            }
            else{ 
                throw new DukeException("Please give a starting and ending time!");
            }
            
        }
        catch (DukeTimeException e){ 
            String[] timing = string_list[2].split("-");
            if(timing.length>= 2 && !(timing[1].trim().equals("")) ){ 
                c1 = new Events(t,string_list[1],timing[0],timing[1]);
            }
            else{ 
                throw new DukeException("Please give a starting and ending time!");
            }
        }
        return c1; 
    }
    public void deserial() throws  FileNotFoundException,IOException,DukeException{ 
        try{ 
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
                    Task c1; 
                    try{ 
                        String[] name_list = {string_list[1],string_list[2],string_list[3]+"-"+string_list[4]};
                        l1.add(get_first_e(name_list,t));
                    }
                    catch (DukeException e) { 
                        throw e; 
                    }
                    
                    //Task temp = new Events(t,string_list[2],string_list[3]); 
                    //l1.add(c1); 
                }
                else if(string_list[0].equals("D")){ 
                    boolean t; 
                    if(string_list[1].equals("1")){ 
                        t= true; 
                    } 
                    else{ 
                        t = false;
                    }
                    Task c1;
                    try{ 
                        LocalDateTime date_type = new ParseTime().parseStringToDate(string_list[3].trim());
                        //System.out.println("Before : " + date_type);
                        c1 = new Deadline(t,string_list[2],date_type,string_list[3]);
                    }
                    catch (DukeTimeException e){ 
                        c1 = new Deadline(t,string_list[2],string_list[3]);
                    }
                    //Task temp = new Events(t,string_list[2],string_list[3]); 
                    l1.add(c1);
                    
                }
            }
            fstream.close();
        }
        catch (FileNotFoundException e){ 
            System.out.println("no file found");

        }
        
    }  
    public Parser() throws FileNotFoundException,IOException,DukeException{
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
    public String find(String keyword){ 
        String message = "";
        int num = 1; 
        for (Task temp : l1) {
            //System.out.println("PRINTING " );
            //System.out.println(temp.get_attrib());
            
            if(temp.get_name().contains(keyword)){ 
                if(num==1){
                    message +="Here are the matching tasks in your list:\n";
                } 
                message +=String.valueOf(num)+"."+ temp+"\n";
                num+=1; 
            }
        }
        if(message == ""){ 
            return "No such keyword found!"; 
        }
        else{ 
            return message; 
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
            Task c1;
            try{ 
                //LocalDateTime date_type = new ParseTime().parseStringToDate(deadline_time.trim());
                //System.out.println("Before : " + date_type);
                String [] timing_list= deadline_time.split("-");

                if(timing_list.length >= 2 && !(timing_list[1].trim().equals(""))){
                   // System.out.println(Arrays.toString(timing_list)); 
                    String[] string_list = {task_to_be_done,task_to_be_done,deadline_time};
                    c1 = get_first_e(string_list, false);
                    l1.add(c1);
                    System.out.println(c1);
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
            System.out.println(get_end_message(l1.size()));
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
                //System.out.println("PRINTING " );
                //System.out.println(temp.get_attrib());
                serialized += temp.get_attrib() +"\n"; 
            }
            //System.out.println(serialized);
            out.println(serialized);
            out.close ();    
        }
        catch (Exception e){ 
            throw e; 
        }
    }
}