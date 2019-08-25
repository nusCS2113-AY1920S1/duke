package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.services.tasks.Task;
import java.util.Scanner;

public class TaskManager {

    private Task task;

    public TaskManager() {
    }

    public boolean run() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (userInput.equals("bye")) {
            return false;
        } else {
            task = new Task(userInput);
            return true;
        }
    }

}
