import java.util.Scanner;
import java.util.*;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void print_list(List<Task> list1){
        int i = 0;
        for (Task temp : list1) {
            System.out.print(i+1);
            System.out.print(".");
            System.out.println(temp);
            i +=1;
		}
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
        try{ 

            Parser analyser = new Parser(); 
            while(true){
                Scanner input = new Scanner(System.in);
                if(input.hasNextLine()){
                    String command = input.nextLine();
    
                    String end =  "bye";
                    String show_data = "list";
                    String done = "done";
                    String deadline = "deadline";
                    String todo = "todo";
                    String events = "event";
    
                    if(end.equals(command)){
                        try {
                            analyser.serial();
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
                        analyser.bye();
                        input.close();
                        return; 
                    }
    
                    if(show_data.equals(command)){
                        analyser.print_list();
                        //System.out.println(l1);
                    }
    
                    else{
                        String[] values = command.split(" ",2);
                        //marking as done
                        //System.out.println(Arrays.toString(values));
                        
                        if(done.equals(values[0])){
                            int index;
                            try{ 
                                index =  Integer.parseInt(values[1]);
                                analyser.done(index);  
                                analyser.serial();                                    
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
                                analyser.create_todo(command);
                                analyser.serial();
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
                                analyser.serial();
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
                                analyser.serial();
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
                else{ 
                    try {
                        analyser.serial();
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
                    input.close();
                    return;
                }
            }

        }
        catch(Exception e){ 
            System.out.println("error");
            System.out.println(e.getMessage());
        }
       
    }
}
