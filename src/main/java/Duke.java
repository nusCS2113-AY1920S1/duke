package com.nwjbrandon.duke;

import com.nwjbrandon.duke.services.TaskManager;
import com.nwjbrandon.duke.constants.Messages;

public class Duke {

    public Duke() {
    }

    /**
     * Main application starts running here.
     * @param args - input of array of strings from console
     */
    public static void main(String[] args) {
        greetingMessage();
        TaskManager taskManager = new TaskManager();
        while (taskManager.run()) {
        }
        farewellMessage();
    }

    /**
     * Show greeting message.
     */
    public static void greetingMessage() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Hello! I'm Duke\n"
                      + "\t What can I do for you?\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    /**
     * Show farewell message.
     */
    public static void farewellMessage() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Bye. Hope to see you again soon!\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

}
