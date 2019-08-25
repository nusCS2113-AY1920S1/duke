package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;
import java.util.ArrayList;

public class MessageFormatter {

    MessageFormatter() {
    }

    public static void showTaskSuccessMessage(String taskName) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t");
        System.out.print(" added: ");
        System.out.println(taskName);
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

    public static void showTasksList(ArrayList<String> taskNamesList) {
        System.out.print("\t");
        System.out.println(Messages.divider);
        for (int i = 0; i < taskNamesList.size(); i++) {
            System.out.print("\t");
            System.out.print(" ");
            System.out.print(i+1);
            System.out.print(". ");
            System.out.println(taskNamesList.get(i));
        }
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

}
