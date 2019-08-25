package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;

public class MessageFormatter {

    MessageFormatter() {
    }

    public static void showTaskSuccessMessage(String taskName) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t");
        System.out.print(" ");
        System.out.println(taskName);
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

}
