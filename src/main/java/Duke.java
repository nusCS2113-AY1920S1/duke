import java.util.Scanner;
import java.util.*;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static void print_list(List<Task> list1){
        int i = 0;
        for (Task temp : list1) {
            System.out.print(i+1);
            System.out.print(".");
            System.out.println(temp);
            i +=1;
		}
    }
    public Duke(String filePath) {
        ui = new Ui();        
        storage = new Storage(filePath);
        try {
            
            tasks = new TaskList(storage.load());
            //System.out.println(storage.load().get(0));
            
        } catch (DukeException e) {
            System.out.println("error for loading file");
            ui.showLoadingError();
            tasks = new TaskList();
        }
        catch (FileNotFoundException e){ 
            System.out.println("error for loading file");
            ui.showLoadingError();
            tasks = new TaskList();
        }
        catch(Exception e){ 
            System.out.println(e);
            ui.showLoadingError();
            tasks = new TaskList(); 
        }
    }
    public void run(){ 
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        //List<Task> l1 = new ArrayList<Task>(); 
        try{ 
            Parser analyser = new Parser(tasks,storage); 
            StringParser parse_string = new StringParser(analyser,storage,tasks);
            Scanner input = new Scanner(System.in);
            while(true){
                if(input.hasNextLine()){
                    String command = input.nextLine();
                   
                    String end =  "bye";
                    if(end.equals(command)){
                        try {
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
                        catch (Exception e) {
                            System.out.println(e);
                        }
                        analyser.bye();
                        input.close();
                        return; 
                    }
                    else{ 
                        //System.out.println("hello1221");
                        parse_string.get_info(command);
                    }
                }
                else{
                    //System.out.println("hdahhda"); 
                    try {
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
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
