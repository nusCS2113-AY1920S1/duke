package com.nwjbrandon.duke;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.services.tasks.TaskManager;

public class Duke {

    public Duke() {
    }

    public static void main(String[] args) {
        Messages.greetingMessage();
        TaskManager taskManager = new TaskManager();
        while(taskManager.run());
        Messages.farewellMessage();
    }

}
