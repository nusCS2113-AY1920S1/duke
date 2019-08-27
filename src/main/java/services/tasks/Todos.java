package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;

public class Todos extends Task {

    private static int numberOfTodos = 0;

    public Todos(String taskName) {
        super(taskName, false);
        numberOfTodos++;
        super.showObjString();
    }

    private int getNumberOfTodos() {
        return numberOfTodos;
    }

    /**
     * get the formatted task string to show for Todos.
     * @return formatted task string to show for Todos
     */
    @Override
    public String toString() {
        String output = "\t Got it. I've added this task:\n"
                      + "\t   [T][" + this.getStatusIcon() + "] " + this.getTaskName() + "\n"
                      + "\t Now you have " + this.getNumberOfTodos() + " tasks in the list.\n";
        return output;
    }

}

