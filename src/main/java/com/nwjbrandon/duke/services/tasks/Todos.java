package com.nwjbrandon.duke.services.tasks;

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
        String formattedTaskName = checkUserInput(taskName, "todos");
        this.taskName = formattedTaskName;
        return formattedTaskName;
    }

    /**
     * get the formatted task string to show for todos.
     * @return formatted task string to show for todos
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n"
                      + "\t   " + this.getTaskName() + "\n"
                      + "\t Now you have " + this.getNumberOfTasks() + " tasks in the list.";
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

