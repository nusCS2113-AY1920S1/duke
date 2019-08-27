package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.services.tasks.Task;
import java.util.ArrayList;

public class TaskMessageFormatter {

    TaskMessageFormatter() {
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

}
