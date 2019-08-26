import java.io.*;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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


        //set up scanner and datastructures
        Scanner sc= new Scanner(System.in);
        ArrayList<Task> arrlist=new ArrayList<>();

        //load from the file into the arraylist, if any thing to load at all
        loadDuke(arrlist);

        //cli loop
        while(true){
            String cmd = sc.nextLine();

            if(cmd.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                exit(0);
            }
            else if(cmd.equals("list")){
                int count=1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : arrlist) {
                    System.out.println(count++ + "." + "[" + t.getSymbol() + "]" + "[" + t.getStatusIcon() + "] " + t.description);
                }
            }
            else if(cmd.matches("done ([0-9]*)")){
                Scanner sc1= new Scanner(cmd);
                sc1.next(); //get rid of the done
                System.out.println("Nice! I've marked this task as done:");
                Task t = arrlist.get(sc1.nextInt()-1);
                t.markAsDone();
                System.out.println("["+t.getStatusIcon()+"] "+t.description);

            }
            else if (cmd.matches("(todo|event|deadline) .+")){  //use regex to make life easier
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
                        String when=cs.substring(splitPointE+4);
                        


                        String whatE = cs.substring(0,splitPointE);
                        String descriptionE=whatE+" (at: "+when+")";
                        arrlist.add(new Event(descriptionE));
                        System.out.println("[E][\u2718]"+descriptionE);
                        break;
                    case "deadline":
                        int splitPoint=cs.indexOf("/by");
                        String deadline=cs.substring(splitPoint+5);
                        String what = cs.substring(0,splitPoint);
                        String description=what+" (by: "+deadline+")";
                        arrlist.add(new Deadline(description));
                        System.out.println("[D][\u2718]"+description);
                        break;

                }

                //at this point, an update is made to the task list, so save to file
                saveDuke(arrlist);
                System.out.println("Now you have "+arrlist.size()+" tasks in the list");



            }
            else if (cmd.matches("(todo|event|deadline)")){
                try {
                    throw new DukeException("☹ OOPS!!! The description of a "+cmd+" cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            else{
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }

    }


    /***
     * This function saves the arraylist of tasks to a file called duke.txt in the current directory.
     * It writes all the properties of Task t (which are strings) to the file using PrintWriter
     *
     */
    private static void saveDuke(ArrayList<Task> tasks){
        File f = new File("./duke.txt");
        try {
            PrintWriter pw = new PrintWriter(f);
            for(Task t:tasks){
                pw.printf("%s %s %s\n",t.symbol,t.isDone,t.description);
            }
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /***
     * This function loads from the textfile the list of tasks into the arraylist on startup.
     * It creates new Task objects based on the symbol read i.e if E, then Task t = new Event(description);
     * and then we add the task to the arraylist
     *
     */
    private static void loadDuke(ArrayList<Task> tasks){
        File f = new File("./duke.txt");
        try {
            FileReader fr = new FileReader(f);

            //read into a char array
            StringBuilder sb = new StringBuilder();
            int c;
            while((c=fr.read())!=-1){
                sb.append((char)c);
            }

            //Set up a scanner to read and parse the strings
            String cmds=sb.toString();
            Scanner strScanner = new Scanner(cmds);
            while(strScanner.hasNext()){
                switch(strScanner.next()){
                    case "E":
                        String done = strScanner.next();
                        Task t = new Event(strScanner.nextLine());
                        if(done.equals("true")){
                            t.isDone=true;
                        }
                        tasks.add(t);
                        break;
                    case "D":
                        String done1 = strScanner.next();
                        Task t1 = new Deadline(strScanner.nextLine());
                        if(done1.equals("true")){
                            t1.isDone=true;
                        }
                        tasks.add(t1);
                        break;
                    case "T":
                        String done2 = strScanner.next();
                        Task t2 = new Todo(strScanner.nextLine());
                        if(done2.equals("true")){
                            t2.isDone=true;
                        }
                        tasks.add(t2);
                        break;

                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/**
 * This class is the custom exception class extending Exception
 * that overwrites toString() for returning custom exception messages
 *
 * It is thrown when command is unknown or when there are iinvalid arguments
 */
class DukeException extends Exception{

    String description;


    public DukeException(String description){
        this.description=description;
    }

    @Override
    public String toString() {
        return description;
    }
}
