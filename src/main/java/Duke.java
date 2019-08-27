import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class Duke {

    //set up scanner and datastructures
    private Scanner sc;
    private ArrayList<Task> arrlist;
    private static final String saveFilePath="./duke.txt";


    /**
     * @ClassConstructor
     * No Params, No Return Values
     * Initializes the Scanner for CLI parsing, ArrayList for storing Task Objects
     * Loads any stored tasks from the savefile ./duke.txt (same directory)
     * Starts off the CLI parsing loop
     */
    public Duke(){
        sc = new Scanner(System.in);
        arrlist=new ArrayList<>();

        //load from the file into the arraylist, if any thing to load at all
        loadDuke(arrlist);
        processCommands();
    }


    /**
     * @Function
     * No Params, No Return Value
     * This function handles the main CLI parsing loop
     */
    public void processCommands(){

        //cli loop
        while(true){
            String cmd = sc.nextLine();

            if(cmd.equals("bye")){
                exitDuke();
            }
            else if(cmd.equals("list")){
                listTasks();
            }
            else if(cmd.matches("done ([0-9]+)")){
                taskDone(cmd);

            }
            else if(cmd.matches("delete ([0-9]+)")){
                deleteTask(cmd);
            }
            else if (cmd.matches("(todo|event|deadline) .+")){  //use regex to make life easier
                addTask(cmd);
            }
            else if (cmd.matches("find (.*)")){
                findTask(cmd);
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


    /**
     * @Function
     * No Params, No Return Value
     * This function handles the exiting/shutdown of the program Duke
     * @UsedIn: processCommands
     * TODO: Save data on exit
     */
    public void exitDuke(){
        System.out.println("Bye. Hope to see you again soon!");
        exit(0);
    }


    /**
     * @Function
     * No Params, No Return Value
     * This function handles the list command which lists the tasks currently in Duke's tracking
     * It will display the task symbol (T,E,D), the status (done or not done) and the description string
     * @UsedIn: processCommands
     */
    public void listTasks(){
        int count=1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : arrlist) {
            showTask(t);
        }
    }


    /**
     * @Function
     * @param cmd, No Return Value
     * This function handles the completion of tasks by marking them as done
     * @UsedIn: processCommands
     */
    public void taskDone(String cmd){
        Scanner sc1= new Scanner(cmd);
        sc1.next(); //skip over the 'done'
        System.out.println("Nice! I've marked this task as done:");
        Task t = arrlist.get(sc1.nextInt()-1);
        t.markAsDone();
        showTask(t);
        saveDuke(arrlist);
    }

    /**
     * @Function
     * @param cmd, No Return Value
     * This function handles the deletion of tasks
     * @UsedIn: processCommands
     */
    public void deleteTask(String cmd){
        Scanner sc1= new Scanner(cmd);
        sc1.next(); //skip over the 'delete'
        System.out.println("Noted. I've removed this task:");
        Task t = arrlist.remove(sc1.nextInt()-1);
        t.markAsDone();
        showTask(t);
        saveDuke(arrlist);
        showSize();
    }


    /**
     * @Function
     * @param cmd, No Return Value
     * @calls dateParse(String when)
     * This function handles the adding of the tasks (Events, Deadlines, Todos)
     * It tests for the event type, then parses it according to the correct syntax
     * @UsedIn: processCommands
     */
    public void addTask(String cmd){
        System.out.println("Got it. I've added this task:");
        Scanner sc1= new Scanner(cmd);
        String s=sc1.next(); //get the command string
        String cs=sc1.nextLine(); //get the description string
        String token;
        String description;
        switch(s){
            case "todo":
                arrlist.add(new Todo(cs.trim()));
                System.out.println("[T][\u2718] "+cs);
                break;
            case "event":
                token="/at";

                description= getDescription(cs,token);
                arrlist.add(new Event(description));
                System.out.println("[E][\u2718] "+description);
                break;
            case "deadline":
                token="/by";

                description=getDescription(cs,token);
                arrlist.add(new Deadline(description));
                System.out.println("[D][\u2718] "+description);
                break;

        }

        //at this point, an update is made to the task list, so save to file
        saveDuke(arrlist);
        showSize();


    }


    /**
     * @Function
     * @param cs
     * @param token
     * @return description
     * @UsedIn: : addTask
     * This function builds a description from the description string according to the token (/at or /by etc)
     */
    public String getDescription(String cs, String token){
        int splitPoint=cs.indexOf(token);
        String when=cs.substring(splitPoint+token.length()+1);

        //call the date parser to parse and return a date string
        String check=dateParse(when);
        if(!check.equals("false")){
            when=check;
        }


        token=token.replace("/","");
        String what = cs.substring(0,splitPoint).trim();
        return what+" ("+token+": "+when+")";

    }


    /**
     * @Function
     * No Params, No Return Value
     * This function simply shows the number of tasks in the arraylist
     * @UsedIn: addTask
     */
    public void showSize(){
        System.out.println("Now you have "+arrlist.size()+" tasks in the list");
    }


    /**
     * @Function
     * No Params, No Return Value
     * This function simply displays the task passed into it
     * @UsedIn: taskDone,deleteTask
     */
    public void showTask(Task t){
        System.out.println("[" + t.getSymbol() + "]" + "[" + t.getStatusIcon() + "] " + t.description);
    }

    /**
     * @Function
     * @param cmd, No Return Value
     * This function handles the deletion of tasks
     * @UsedIn: processCommands
     */
    public void findTask(String cmd){
        Scanner sc1= new Scanner(cmd);
        sc1.next(); //skip over the 'find'
        String pattern = sc1.next();
        System.out.println("Here are the matching tasks in your list:");

        int count=1;

        for(Task t:arrlist){
            if(t.description.contains(pattern)){
                System.out.print(count+++".");
                showTask(t);
            }
        }

    }



    /***
     * @Function
     * This function saves the arraylist of tasks to a file called duke.txt in the current directory.
     * It writes all the properties of Task t (which are strings) to the file using PrintWriter
     * @UsedIn: addTask,taskDone
     *
     */
    private void saveDuke(ArrayList<Task> tasks){
        File f = new File(saveFilePath);
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
     * @Function
     * This function loads from the textfile the list of tasks into the arraylist on startup.
     * It creates new Task objects based on the symbol read i.e if E, then Task t = new Event(description);
     * and then we add the task to the arraylist
     *
     * @UsedIn: Duke Constructor
     *
     */
    private void loadDuke(ArrayList<Task> tasks){
        File f = new File(saveFilePath);
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
                        String done = strScanner.next().trim();
                        Task t = new Event(strScanner.nextLine().strip());
                        if(done.equals("true")){
                            t.isDone=true;
                        }
                        tasks.add(t);
                        break;
                    case "D":
                        String done1 = strScanner.next().trim();
                        Task t1 = new Deadline(strScanner.nextLine().strip());
                        if(done1.equals("true")){
                            t1.isDone=true;
                        }
                        tasks.add(t1);
                        break;
                    case "T":
                        String done2 = strScanner.next().trim();
                        Task t2 = new Todo(strScanner.nextLine().strip());
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


    /**
     * @Function
     * This function returns the number cardinal when passed an integer
     * @param n, No Return Value
     * @UsedIn:
     */
    private static String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }


    /**
     * @Function
     * @param when
     * @return dateString
     * @UsedIn: getDescription
     * This function parses the date in the format dd/MM/yyyy HHmm and returns a date in the format
     * dd MMMM yyyy hh:mma
     */
    private static String dateParse(String when){
        //parse date
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = null;
        try {
            date = format.parse(when);
        } catch (ParseException e) {
            return "false";
        }
        format = new SimpleDateFormat("dd MMMM yyyy hh:mma");
        when = format.format(date);
        return when;
    }





    //Test Client
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello there!\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");

        //start the program flow
        new Duke();
    }



    /**
     * @InnerClass
     * Extends Exception
     *
     * This inner class is the custom exception class extending Exception
     * that overwrites toString() for returning custom exception messages
     *
     * It is thrown when command is unknown or when there are invalid arguments
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



}



