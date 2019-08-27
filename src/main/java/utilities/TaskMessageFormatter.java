package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.services.tasks.Task;
import java.util.ArrayList;

public class TaskMessageFormatter {

    TaskMessageFormatter() {
    }

    /**
     * show the task added.
     */
    public static void showTaskAdded(String taskName) {
        String output = "\t" + Messages.divider + "\n"
                      + "\t added: " + taskName + "\n" 
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    /**
     * show the list of task.
     */
    public static void showTasksList(ArrayList<Task> tasksList) {
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
     * show the status of task.
     */
    public static void showTaskDoneStatus(Task task) {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Nice! I've marked this task as done:\n"
                      + "\t [" + task.getStatusIcon() + "] " + task.getTaskName() + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

}
