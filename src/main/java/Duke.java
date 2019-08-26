import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello \n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");

        //set up scanner and datastructure
        Scanner sc= new Scanner(System.in);

        ArrayList<Task> arrlist=new ArrayList<>();


        //cli loop
        while(true){
            String cmd = sc.nextLine();

            if(cmd.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                exit(0);
            }else if(cmd.equals("list")){
                int count=1;
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<arrlist.size();i++){
                    Task t = arrlist.get(i);
                    System.out.println(count+++"."+"["+t.getStatusIcon()+"] "+t.description);
                }
            }else if(cmd.matches("done ([0-9]*)")){
                Scanner sc1= new Scanner(cmd);
                sc1.next(); //get rid of the done
                System.out.println("Nice! I've marked this task as done:");
                Task t = arrlist.get(sc1.nextInt()-1);
                t.markAsDone();
                System.out.println("["+t.getStatusIcon()+"] "+t.description);

            }else{
                System.out.println("added: "+cmd);
                Task t = new Task(cmd);
                arrlist.add(t);
            }
        }

    }
}
