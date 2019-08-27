package com.nwjbrandon.duke;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.services.TaskManager;

public class Duke {

    public Duke() {
    }

    /**
     * Main application starts running here.
     * @param args - input of array of strings from console
     */
    public static void main(String[] args) {
        Messages.greetingMessage();
        TaskManager taskManager = new TaskManager();
        while (taskManager.run()) {
        }
        Messages.farewellMessage();
    }

}
