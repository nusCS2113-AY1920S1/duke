package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.services.tasks.Task;
import java.util.ArrayList;

public class TaskMessageFormatter {

    TaskMessageFormatter() {
    }

    public static void showTaskAdded(String taskName) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t");
        System.out.print(" added: ");
        System.out.println(taskName);
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

    public static void showTasksList(ArrayList<Task> tasksList) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.print("\t");
            System.out.print(" ");
            System.out.print(i+1);
            System.out.print(". [");
            System.out.print(tasksList.get(i).getIsDoneStatus() ? "✓" : "✗");
            System.out.print("] ");
            System.out.println(tasksList.get(i).getTaskName());
        }
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

    public static void showTaskDoneStatus(Task task) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print("\t");
        System.out.print("  [");
        System.out.print(task.getIsDoneStatus() ? "✓" : "✗");
        System.out.print("] ");
        System.out.println(task.getTaskName());
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

}
