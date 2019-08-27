package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.utilities.ErrorMessageFormatter;
import com.nwjbrandon.duke.constants.Messages;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<Task>();
    }

    /**
     * read the console input.
     * @return input value
     */
    private static String readInput() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput;
    }

    /**
     * show the list of tasks.
     */
    private void showTasksList() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Here are the tasks in your lists:\n";
        for (int i = 0; i < tasksList.size(); i++) {
            output += "\t " + (i + 1) + ". [" + tasksList.get(i).getStatusIcon() + "] " 
                    + tasksList.get(i).getTaskName() + "\n";
        }
        output += "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    /**
     * run the application.
     * @return whether the program is done
     */
    public boolean run() {
        try {
            String userInput = readInput();
            if (userInput.equals("bye")) {
                return false;
            } else if (userInput.equals("list")) {
                showTasksList();
                return true;
            } else if (userInput.startsWith("todo ")) {
                String taskName = userInput.substring(5);
                Todos task = new Todos(taskName);
                tasksList.add(task);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            ErrorMessageFormatter err = new ErrorMessageFormatter("Please input instruction in the correct format!");
        } catch (Exception e) {
            ErrorMessageFormatter err = new ErrorMessageFormatter("An uncaught error has occurred!");
        }
        return true;
    }

}
