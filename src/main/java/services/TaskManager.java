package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.tasks.Deadlines;
import com.nwjbrandon.duke.services.tasks.Events;
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
            output += "\t " + (i + 1) + "." + tasksList.get(i).toTaskString() + "\n";
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
            if (userInput.equals("list")) {
                showTasksList();
            } else if (userInput.startsWith("done")) {
                this.markDone(userInput);
            } else if (userInput.startsWith("todo")) {
                Todos task = new Todos(userInput);
                tasksList.add(task);
            } else if (userInput.startsWith("event")) {
                Events task = new Events(userInput);
                tasksList.add(task);
            } else if (userInput.startsWith("deadline")) {
                Deadlines task = new Deadlines(userInput);
                tasksList.add(task);
            } else if (userInput.equals("bye")) {
                return false;
            } else {
                this.showWrongInput();
            }
        } catch (NumberFormatException e) {
            ErrorMessageFormatter err = new ErrorMessageFormatter("Please input instruction in the correct format!");
        } catch (Exception e) {
            ErrorMessageFormatter err = new ErrorMessageFormatter("An uncaught error has occurred!");
        }
        return true;
    }

    private void markDone(String userInput) {
        String taskIndexString = userInput.substring(5);
        Integer taskIndex = Integer.valueOf(taskIndexString) - 1;
        tasksList.get(taskIndex).setDoneStatus(true);
    }

    private void showWrongInput() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Wrong command given\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

}
