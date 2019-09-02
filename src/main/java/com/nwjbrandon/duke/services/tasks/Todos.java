package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.TaskNames;
import com.nwjbrandon.duke.services.interfaces.Ui;

public class Todos extends Task {

    public Todos(String taskName, int numberOfTasks) throws Exception {
        super(taskName, numberOfTasks);
    }

    public Todos(String[] taskDetails, int numberOfTasks) throws Exception {
        this(taskDetails[2], numberOfTasks);
    }

    /**
     * get the formatted task name.
     * @return formatted task name
     */
    @Override
    public String formatTaskName(String taskName) throws Exception {
        String formattedTaskName = checkUserInput(taskName, TaskNames.TODO.toString());
        this.taskName = formattedTaskName;
        return formattedTaskName;
    }

    /**
     * get the formatted task string to show for deadlines.
     */
    @Override
    public void addTaskString(int size) {
        Ui.showAddTaskString("[T][" + this.getStatusIcon() + "] " + this.getTaskDescription(), size);
    }

    /**
     * get the formatted task string to show for deadlines.
     */
    @Override
    public void removeTaskString(int size) {
        Ui.showRemoveTaskString("[T][" + this.getStatusIcon() + "] " + this.getTaskDescription(), size);
    }

    /**
     * get the formatted task string to show for todos.
     * @return formatted task string to show for todos
     */
    @Override
    public String toTaskDescriptionString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

}

