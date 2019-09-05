import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StringParser {
    private Parser analyser; 
    private Storage storage;
    private TaskList tasks;
    public StringParser(Parser analyser_1,Storage storage_1,TaskList tasks_1){ 
        analyser = analyser_1;
        storage = storage_1; 
        tasks = tasks_1; 
    }
    public void get_info(String command){ 
        String end =  "bye";
        String show_data = "list";
        String done = "done";
        String deadline = "deadline";
        String todo = "todo";
        String events = "event";
        String remove = "delete";
        String find = "find"; 
        if(show_data.equals(command.trim())){
            analyser.print_list();
            //System.out.println(l1);
        }
        
        else{
            String[] values = command.trim().split(" ",2);
            //marking as done
            //System.out.println(Arrays.toString(values));

            if(remove.equals(values[0])){ 
                try{ 
                    System.out.println(analyser.delete( Integer.parseInt(values[1].trim())));
                }
                catch (NumberFormatException e){ 
                    System.out.println("please enter a valid number");
                }
                catch (ArrayIndexOutOfBoundsException e){ 
                    System.out.println("please give a second value!");
                }
                catch(DukeException e){ 
                    System.out.println(e.getMessage());
                }
                catch(Exception e){ 
                    System.out.println(e.getMessage());
                }
            }
        
            else if(find.equals(values[0])){ 
                try{                            
                    System.out.println(analyser.find(values[1]));     
                }
                catch(ArrayIndexOutOfBoundsException e){ 
                    System.out.println("Please enter a keyword");
                }

            }
            else if(done.equals(values[0])){
                int index;
                try{ 
                    index =  Integer.parseInt(values[1]);
                    analyser.done(index);  
                    storage.serial(tasks.get_list());                                    
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch(NumberFormatException nfe){ 
                    System.out.println("Sorry, you dint enter a valid index number!");
                }
                catch (Exception e) {
                    System.out.println(e);
                }                            
                
            }

            else if(todo.equals(values[0])){ 
                try{ 
                    //System.out.println("todo caaled");
                    analyser.create_todo(command);
                    //System.out.println("todo caaled after create");
                    storage.serial(tasks.get_list());
                } 
                catch (DukeException e){ 
                    System.out.println(e.getMessage());
                }
                
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
                catch (Exception e) {
                    System.out.println(e);
                }
            }

            else if(events.equals(values[0])){ 
                try{ 
                    analyser.create_events(values[1]);
                    storage.serial(tasks.get_list());
                } 
                catch (DukeException e){ 
                    System.out.println(e.getMessage());
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch (Exception e){ 
                    System.out.println(e.getMessage());
                }
            }

            else if(deadline.equals(values[0])){ 
                String work = values[1];
                try{ 
                    analyser.create_deadline(work);
                    storage.serial(tasks.get_list());
                } 
                catch (DukeException e){ 
                    System.out.println(e.getMessage());
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch (Exception e){ 
                    System.out.println(e.getMessage());
                }
            }

            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");  
            }
        }
    }
}   