package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.utilities.TaskMessageFormatter;

public class Task {

    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this(taskName, false);
    }

    private Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
        TaskMessageFormatter.showTaskAdded(this.taskName);
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getIsDoneStatus() {
        return isDone;
    }

    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
        TaskMessageFormatter.showTaskDoneStatus(this);
    }

}
