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
                    System.out.println(count+++"."+"["+t.getSymbol()+"]"+"["+t.getStatusIcon()+"] "+t.description);
                }
            }else if(cmd.matches("done ([0-9]*)")){
                Scanner sc1= new Scanner(cmd);
                sc1.next(); //get rid of the done
                System.out.println("Nice! I've marked this task as done:");
                Task t = arrlist.get(sc1.nextInt()-1);
                t.markAsDone();
                System.out.println("["+t.getStatusIcon()+"] "+t.description);

            }else if (cmd.matches("(todo|event|deadline) .*")){
                System.out.println("Got it. I've added this task:");
                Scanner sc1= new Scanner(cmd);
                String s=sc1.next();
                String cs=sc1.nextLine();
                switch(s){
                    case "todo":
                        arrlist.add(new Todo(cs));
                        System.out.println("[T][\u2718]"+cs);
                        break;
                    case "event":
                        int splitPointE=cs.indexOf("/at");
                        String when=cs.substring(splitPointE+3);
                        String whatE = cs.substring(0,splitPointE);
                        String descriptionE=whatE+" (at: "+when+")";
                        arrlist.add(new Event(descriptionE));
                        System.out.println("[E][\u2718]"+descriptionE);
                        break;
                    case "deadline":
                        int splitPoint=cs.indexOf("/by");
                        String deadline=cs.substring(splitPoint+4);
                        String what = cs.substring(0,splitPoint);
                        String description=what+" (by: "+deadline+")";
                        arrlist.add(new Deadline(description));
                        System.out.println("[D][\u2718]"+description);
                        break;

                }
                System.out.println("Now you have "+arrlist.size()+" tasks in the list");



            }else{
                System.out.println("added: "+cmd);
                Task t = new Task(cmd);
                arrlist.add(t);
            }
        }

    }
}
