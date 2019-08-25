package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.services.tasks.Task;

public class MessageFormatter {

    private static String divider = "____________________________________________________________";

    MessageFormatter() {
    }

    public static void getTaskSuccessMessage(Task task) {
        System.out.print("\t");
        System.out.println(divider);
        System.out.print("\t");
        System.out.print(" ");
        System.out.println(task.getTaskName());
        System.out.print("\t");
        System.out.println(divider);
        System.out.println();
    }

}
