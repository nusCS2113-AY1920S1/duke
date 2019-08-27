import java.util.Scanner;
import java.util.*;

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
                        int index =  Integer.parseInt(values[1]);
                        analyser.done(index);
                    }

                    else if(todo.equals(values[0])){ 
                        analyser.create_todo(command);
                    }

                    else if(events.equals(values[0])){ 
                        analyser.create_events(values[1]);
                    }

                    else if(deadline.equals(values[0])){ 
                        String work = values[1];
                        analyser.create_deadline(work);
                    }

                    else{
                        System.out.println("Sorry I din't understand what you said! :)");  
                    }
                }

            }
            else{ 
                input.close();
                return;
            }
        }
    }
}
