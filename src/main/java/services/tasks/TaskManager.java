package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.utilities.MessageFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<Task>();
    }

    public boolean run() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (userInput.equals("bye")) {
            return false;
        } else if (userInput.equals("list")) {
            showTasksList();
            return true;
        } else {
            Task task = new Task(userInput);
            tasksList.add(task);
            return true;
        }
    }

    private void showTasksList() {
        ArrayList<String> taskNamesList = new ArrayList<String>();
        for (int i = 0; i < tasksList.size(); i++) {
            taskNamesList.add(tasksList.get(i).getTaskName());
        }
        MessageFormatter.showTasksList(taskNamesList);
    }

}
