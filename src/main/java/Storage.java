import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Storage {
    private String file_path; 
    private ArrayList<Task> l1 = new ArrayList<Task>();  
    public  Task get_first_e(String[] string_list,boolean t) throws DukeException{ 
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
            FileInputStream fstream = new FileInputStream(file_path);
        
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

    public Storage(String file_path_1)  {   
            file_path = file_path_1; 
    }
    public ArrayList<Task> load() throws FileNotFoundException,IOException,DukeException{ 
        deserial();
        //System.out.println(file_path);
        //System.out.println(l1.get(0));
        return l1;
    }
    public void serial(ArrayList<Task> l1) throws Exception{ 
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
    

 