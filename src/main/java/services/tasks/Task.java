package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;

public class Task {

    protected String taskName;
    protected boolean isDone;
    private static int numberOfTodos = 0;

    public Task(String taskName) throws DukeException {
        this(taskName, false);
    }

    private Task(String taskName, boolean isDone) throws DukeException {
        this.taskName = this.formatTaskName(taskName);
        this.isDone = isDone;
        numberOfTodos++;
        this.showObjString();
    }

    public String formatTaskName(String taskName) throws DukeException {
        return taskName;
    }

    protected int getNumberOfTodos() {
        return numberOfTodos;
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
                      + "\t " + this.toTaskString() + "\n"
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
        String output = "added: " + this.getTaskName();
        return output;
    }

    protected void showObjString() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + this.toString() + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    public String toTaskString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskName();
    }

    protected String checkUserInput(String userInput, int start, String baseTaskName) throws DukeEmptyCommandException {
        if (userInput.length() <= start) {
            throw new DukeEmptyCommandException(baseTaskName);
        } else {
            return userInput.substring(start);
        }
    }

}
