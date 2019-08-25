package com.nwjbrandon.duke;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.utilities.MessageFormatter;
import com.nwjbrandon.duke.services.tasks.TaskManager;
import com.nwjbrandon.duke.services.tasks.Task;

public class Duke {

    public Duke() {
    }

    public static void main(String[] args) {
        Messages.greetingMessage();
        TaskManager taskManager = new TaskManager();
        while(taskManager.run()) {
            Task task = taskManager.getTask();
            MessageFormatter.getTaskSuccessMessage(task);
        }
        Messages.farewellMessage();
    }

}
