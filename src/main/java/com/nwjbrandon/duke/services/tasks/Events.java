package com.nwjbrandon.duke.services.tasks;

import java.text.ParseException;

public class Events extends Task {

    public Events(String taskName, int numberOfTasks) throws Exception {
        super(taskName, numberOfTasks);
    }

    public Events(String[] taskDetails, int numberOfTasks) throws Exception {
        this(taskDetails[2] + " /at " + taskDetails[3], numberOfTasks);
    }

    /**
     * get the formatted task name.
     * @return formatted task name
     */
    @Override
    public String formatTaskName(String taskName) throws Exception {
        String task = checkUserInput(taskName, "events");
        String[] parts = task.split(" /at ");
        try {
            this.taskName = parts[0];
            this.by = parts[1];
            return parts[0] + " (at: " + dateFormatter(parts[1]) + ")";
        } catch (ParseException e) {
            this.taskName = taskName;
            this.by = parts[1];
            return parts[0] + " (at: " + parts[1] + ")";
        }

    }

    /**
     * get the formatted task string to show for events.
     * @return formatted task string to show for events
     */
    @Override
    public String addTaskString() {
        return "Got it. I've added this task:\n"
                      + "\t   " + this.getTaskDescription() + "\n"
                      + "\t Now you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * get the formatted task string to show for deadlines.
     * @return formatted task string to show for deadline
     */
    @Override
    public String removeTaskString(int size) {
        return "Noted. I've removed this task:\n"
                + "\t   " + this.getTaskDescription() + "\n"
                + "\t Now you have " + (size - 1) + " tasks in the list.";
    }

    /**
     * get the formatted description string to show for deadlines.
     * @return formatted description string to show for deadline
     */
    @Override
    public String toTaskDescriptionString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

}

