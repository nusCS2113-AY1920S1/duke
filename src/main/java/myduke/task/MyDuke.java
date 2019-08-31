package myduke.task;

import myduke.DukeException;
import myduke.DukeFileOperation;
import myduke.DukeTools;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MyDuke {
    DukeTools dukeTools = new DukeTools();
    Scanner inputs = new Scanner(System.in);
    DukeFileOperation dfo = new DukeFileOperation();


    public void runDuke() {
        //ArrayList<Task> userInput = dfo.loadFile();
        String newString;
        String[] secondBox;
        String description;
        String time;
        String userInput = inputs.nextLine();

        while(!userInput.equals("bye")) {
            String[] firstBox = dukeTools.firstFilter(userInput); // return a string array with commands and description
            String commands = firstBox[0];
            try {
                switch (commands) {
                    case "list":
                        dukeTools.showList();
                        break;
                    case "done":
                        dukeTools.runDone(userInput);
                        break;
                    case "todo":
                        try {
                            if (firstBox[1].isBlank()){
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }else {
                                description = firstBox[1];
                                dukeTools.runToDo(description);
                            }
                        }catch(DukeException e) {
                            e.printStackTrace();
                        }finally{
                            break;
                        }

                    case "deadline":
                        newString = firstBox[1];
                        secondBox = dukeTools.secondFilter(newString , "deadline");
                        description = secondBox[0];
                        time = secondBox[1];
                        dukeTools.runDeadline(description , time);
                        break;
                    case "event":
                        newString = firstBox[1];
                        secondBox = dukeTools.secondFilter(newString , "event");
                        description = secondBox[0];
                        time = secondBox[1];
                        dukeTools.runEvent(description , time);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }catch(DukeException e) {
                e.printStackTrace();
            }finally {
                userInput = inputs.nextLine().trim();
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }



}
