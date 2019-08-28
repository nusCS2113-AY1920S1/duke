package com.nwjbrandon.duke.services.tasks;

import java.text.ParseException;

public class Deadlines extends Task {

    public Deadlines(String taskName, int numberOfTasks) throws Exception {
        super(taskName, numberOfTasks);
    }

    public Deadlines(String[] taskDetails, int numberOfTasks) throws Exception {
        this(taskDetails[2] + " /by " + taskDetails[3], numberOfTasks);
    }

    /**
     * get the formatted task name.
     * @return formatted task name
     */
    @Override
    public String formatTaskName(String taskName) throws Exception {
        String task = checkUserInput(taskName, "deadlines");
        String[] parts = task.split(" /by ");
        try {
            this.taskName = parts[0];
            this.by = parts[1];
            return parts[0] + " (by: " + dateFormatter(parts[1]) + ")";
        } catch (ParseException e) {
            this.taskName = taskName;
            this.by = parts[1];
            return parts[0] + " (by: " + parts[1] + ")";
        }
    }

    /**
     * get the formatted task string to show for deadlines.
     * @return formatted task string to show for deadline
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n"
                      + "\t   " + this.getTaskDescription() + "\n"
                      + "\t Now you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * get the formatted description string to show for deadlines.
     * @return formatted description string to show for deadline
     */
    @Override
    public String toTaskDescriptionString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

}

