package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;

public class Todos extends Task {

    public Todos(String taskName) {
        super(taskName);
    }

    @Override
    public String formatTaskName(String taskName) {
        String formattedTaskName = taskName.substring(5);
        return formattedTaskName;
    }

    /**
     * get the formatted task string to show for Todos.
     * @return formatted task string to show for Todos
     */
    @Override
    public String toString() {
        String output = "Got it. I've added this task:\n"
                      + "\t   " + this.toTaskString() + "\n"
                      + "\t Now you have " + this.getNumberOfTodos() + " tasks in the list.";
        return output;
    }

    @Override
    public String toTaskString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getTaskName();
    }

}

