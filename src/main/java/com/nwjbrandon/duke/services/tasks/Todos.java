package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class Todos extends Task {

    /**
     * Create todos task from command line.
     * @param taskName task name.
     * @param numberOfTasks number of tasks.
     */
    public Todos(String taskName, int numberOfTasks) {
        super(taskName, numberOfTasks);
    }

    /**
     * Create event task from file input.
     * @param taskDetails task information from file input.
     * @param numberOfTasks number of tasks.
     */
    public Todos(String[] taskDetails, int numberOfTasks) {
        this(taskDetails[2], numberOfTasks);
    }

    /**
     * Format name of task before creating task.
     * @param taskName task name
     * @return formatted task name.
     */
    @Override
    public String formatTaskName(String taskName) {
        this.taskName = taskName;
        return taskName;
    }

    /**
     * Add task.
     * @param size number of tasks.
     */
    @Override
    public void addTaskString(int size) {
        Ui.showAddTaskString("[T][" + this.getStatusIcon() + "] " + this.getTaskDescription(), size);
    }

    /**
     * Remove task.
     */
    @Override
    public void removeTaskString(int size) {
        Ui.showRemoveTaskString("[T][" + this.getStatusIcon() + "] " + this.getTaskDescription(), size);
    }

    /**
     * Get task description.
     * @return description of task.
     */
    @Override
    public String toTaskDescriptionString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

}

