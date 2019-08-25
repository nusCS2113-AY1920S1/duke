package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.services.tasks.Task;
import java.util.Scanner;

public class TaskManager {

    Task task;

    public TaskManager() {
    }

    public boolean run() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        task = new Task(userInput);
        if (task.getTaskName().equals("bye")) {
            return false;
        } else {
            return true;
        }
    }

    public Task getTask() {
        return task;
    }

}
