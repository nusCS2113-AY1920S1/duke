package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;

public class Events extends Task {

    private static int numberOfTodos = 0;

    public Events(String taskName) throws DukeException {
        super(taskName);
    }

    @Override
    public String formatTaskName(String taskName) throws DukeException {
        String task = checkUserInput(taskName, 6, "events");
        String parts[] = task.split(" /at ");
        return parts[0] + " (at: " + parts[1] + ")";
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
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskName();
    }

}

