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

    /**
     * get the name of task.
     * @return name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * get the status of the task.
     * @return true for done
     */
    public boolean getIsDoneStatus() {
        return isDone;
    }

    /**
     * set the done status of the task.
     * @param isDone - status of the task
     */
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
        TaskMessageFormatter.showTaskDoneStatus(this);
    }

}
