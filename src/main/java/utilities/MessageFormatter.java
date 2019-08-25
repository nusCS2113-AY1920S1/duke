package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.constants.Messages;

public class MessageFormatter {

    MessageFormatter() {
    }

    public static void getTaskSuccessMessage(Task task) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t");
        System.out.print(" ");
        System.out.println(task.getTaskName());
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

}
