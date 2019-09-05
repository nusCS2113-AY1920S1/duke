package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.services.interfaces.Ui;

import java.text.ParseException;

public class Events extends Task {

    /**
     * Create event task from command line.
     * @param taskName task name.
     * @param numberOfTasks number of tasks.
     */
    public Events(String taskName, int numberOfTasks) {
        super(taskName, numberOfTasks);
    }

    /**
     * Create event task from file input.
     * @param taskDetails task information from file input.
     * @param numberOfTasks number of tasks.
     */
    public Events(String[] taskDetails, int numberOfTasks) {
        this(taskDetails[2] + " /at " + taskDetails[3], numberOfTasks);
    }

    /**
     * Format name of task before creating task.
     * @param taskName task name.
     * @return formatted task name.
     */
    @Override
    public String formatTaskName(String taskName) {
        String[] parts = taskName.split(" /at ");
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
     * Add task.
     * @param size number of tasks.
     */
    @Override
    public void addTaskString(int size) {
        Ui.showAddTaskString("[E][" + this.getStatusIcon() + "] " + this.getTaskDescription(), size);
    }

    /**
     * Remove task.
     * @param size number of tasks.
     */
    @Override
    public void removeTaskString(int size) {
        Ui.showRemoveTaskString("[E][" + this.getStatusIcon() + "] " + this.getTaskDescription(), size);
    }

    /**
     * Get task description.
     * @return description of task.
     */
    @Override
    public String toTaskDescriptionString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

}

