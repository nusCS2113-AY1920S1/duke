import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class Duke {

    //set up scanner and datastructures

    private static final String saveFilePath="./duke.txt";

    //objects supporting Duke
    Ui ui;
    Storage storage;
    TaskList tasklist;


    /**
     * @ClassConstructor
     * No Params, No Return Values
     * Initializes the supporting objects
     * Starts off the parser CLI parsing loop
     */
    public Duke(){

        //Instantiate objects
        tasklist = new TaskList(this);

        ui = new Ui(tasklist.arrlist);
        ui.showWelcome();

        storage = new Storage(saveFilePath);
        storage.loadDuke(tasklist.arrlist); //load from the file into the arraylist, if any thing to load at all

        //start parsing commands
        new Parser(this);
    }


    /**
     * @Function
     * No Params, No Return Value
     * This function handles the exiting/shutdown of the program Duke
     * @UsedIn: parser.processCommands
     * TODO: Save data on exit
     */
    public void exitDuke(){
        System.out.println("Bye. Hope to see you again soon!");
        exit(0);
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
     * @UsedIn: ui.getDescription
     * This function parses the date in the format dd/MM/yyyy HHmm and returns a date in the format
     * dd MMMM yyyy hh:mma
     */
    protected static String dateParse(String when){
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
        //start the program flow
        new Duke();
    }



    /**
     * @InnerClass
     * Extends Exception
     *
     * This static inner class is the custom exception class extending Exception
     * that overwrites toString() for returning custom exception messages
     *
     * It is thrown when command is unknown or when there are invalid arguments
     */
    static class DukeException extends Exception{

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



