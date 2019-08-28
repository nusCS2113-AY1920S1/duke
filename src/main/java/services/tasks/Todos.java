package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;

public class Todos extends Task {

    public Todos(String taskName) throws Exception {
        super(taskName);
    }

    public Todos(String[] taskDetails) throws Exception {
        this("todo " + taskDetails[2]);
    }

    @Override
    public String formatTaskName(String taskName) throws Exception {
        String formattedTaskName = checkUserInput(taskName, 5, "todos");
        this.description = formattedTaskName;
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

