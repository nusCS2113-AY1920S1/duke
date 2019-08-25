package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.utilities.MessageFormatter;

public class Task {

    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        MessageFormatter.showTaskSuccessMessage(this.taskName);
    }

}
