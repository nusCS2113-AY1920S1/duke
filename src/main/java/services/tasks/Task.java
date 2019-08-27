package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;

public class Task {

    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this(taskName, false);
        this.showObjString();
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
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
        this.showSetDoneStatus();
    }

    public void showSetDoneStatus() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Nice! I've marked this task as done:\n"
                      + "\t [" + this.getStatusIcon() + "] " + this.getTaskName() + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    /**
     * get the status icon of the task.
     * @return status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    /**
     * get the formatted task string to show.
     * @return formatted task string to show
     */
    public String toString() {
        String output = "\t added: " + this.getTaskName() + "\n";
        return output;
    }

    protected void showObjString() {
        System.out.print("\t" + Messages.divider + "\n");
        System.out.print(this.toString());
        System.out.println("\t" + Messages.divider + "\n");
    }



}
