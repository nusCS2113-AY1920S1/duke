package com.nwjbrandon.duke;

import com.nwjbrandon.duke.services.TaskManager;
import com.nwjbrandon.duke.constants.Messages;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.*;

public class Duke {

    public Duke() {
    }

    /**
     * Main application starts running here.
     * @param args - input of array of strings from console
     */
    public static void main(String[] args) throws Exception {
        greetingMessage();
        TaskManager taskManager = new TaskManager();
        taskManager.loadData();
        while (taskManager.run()) {
        }
        taskManager.saveData();
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
