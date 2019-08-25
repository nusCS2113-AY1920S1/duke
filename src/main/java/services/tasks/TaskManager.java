package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.utilities.TaskMessageFormatter;
import com.nwjbrandon.duke.utilities.ErrorMessageFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<Task>();
    }

    private static String readInput() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput;
    }

    private void showTasksList() {
        TaskMessageFormatter.showTasksList(tasksList);
    }

    public boolean run() {
        try {
            String userInput = readInput();
            if (userInput.equals("bye")) {
                return false;
            } else if (userInput.equals("list")) {
                showTasksList();
                return true;
            } else if (userInput.startsWith("done")) {
                String[] inputs = userInput.split(" ");
                Integer taskIndex = Integer.valueOf(inputs[1]) - 1;
                tasksList.get(taskIndex).setDoneStatus(true);
                return true;
            } else {
                Task task = new Task(userInput);
                tasksList.add(task);
                return true;
            }
        } catch (NumberFormatException e) {
            ErrorMessageFormatter err = new ErrorMessageFormatter("Please input instruction in the correct format!");
        } catch (Exception e) {
            ErrorMessageFormatter err = new ErrorMessageFormatter("An uncaught error has occurred!");
        }
        return true;
    }

}
