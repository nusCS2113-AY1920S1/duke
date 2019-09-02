package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.interfaces.Ui;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    String taskName;
    private boolean isDone;
    private int taskIndex = 0;
    String by = "";
    private String taskDescription = "";

    protected Task(String taskDescription, int numberOfTasks) throws Exception {
        this(taskDescription, numberOfTasks, false);
    }

    private Task(String taskDescription, int numberOfTasks, boolean isDone) throws Exception {
        this.taskDescription = this.formatTaskName(taskDescription);
        this.isDone = isDone;
        this.taskIndex =  ++numberOfTasks;
        this.showAddString();
    }

    /**
     * Format the task name.
     * @return task name
     */
    public String formatTaskName(String taskDescription) throws Exception {
        return taskName;
    }

    /**
     * get the date.
     * @return date string
     */
    public String getBy() {
        return this.by;
    }

    /**
     * get the description of task.
     * @return description of the task
     */
    String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * get the number of task.
     * @return number of the task
     */
    int getNumberOfTasks() {
        return taskIndex;
    }

    /**
     * get the name of task.
     * @return name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * get the status of the task.
     * @return true for done
     */
    public boolean getIsDoneStatus() {
        return isDone;
    }

    /**
     * set the done status of the task.
     * @param isDone - status of the task
     */
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
        this.showSetDoneStatus();
    }

    /**
     * show the done status of the task.
     */
    private void showSetDoneStatus() {
        String output = "\t" + Ui.divider + "\n"
                      + "\t Nice! I've marked this task as done:\n"
                      + "\t " + this.toTaskDescriptionString() + "\n"
                      + "\t" + Ui.divider + "\n";
        System.out.println(output);
    }

    /**
     * get the status icon of the task.
     * @return status icon of task
     */
    String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    /**
     * get the formatted task string to show.
     * @return formatted task string to show
     */
    public String addTaskString() {
        return "added: " + this.getTaskName();
    }

    /**
     * get the formatted task string to show.
     * @return formatted task string to show
     */
    public String removeTaskString(int size) {
        return "added: " + this.getTaskName();
    }

    /**
     * show add string.
     */
    private void showAddString() {
        String output = "\t" + Ui.divider + "\n"
                      + "\t " + this.addTaskString() + "\n"
                      + "\t" + Ui.divider + "\n";
        System.out.println(output);
    }

    /**
     * show add string.
     */
    public void showRemoveString(int size) {
        String output = "\t" + Ui.divider + "\n"
                + "\t " + this.removeTaskString(size) + "\n"
                + "\t" + Ui.divider + "\n";
        System.out.println(output);
    }

    /**
     * get task description.
     * @return string of description
     */
    public String toTaskDescriptionString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

    /**
     * check user input.
     * @return user input
     */
    String checkUserInput(String userInput, String baseTaskName) throws DukeEmptyCommandException {
        if (userInput.isBlank()) {
            throw new DukeEmptyCommandException(baseTaskName);
        } else {
            return userInput;
        }
    }

    /**
     * get task description.
     * @return formatted date string
     */
    String dateFormatter(String originalDate) throws ParseException {
        String pattern = "dd/MM/yyyy hhmm";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(originalDate);

        pattern = "d";
        formatter = new SimpleDateFormat(pattern);
        final String a = formatter.format(date);

        pattern = "MMMM yyyy, h";
        formatter = new SimpleDateFormat(pattern);
        final String b = formatter.format(date);

        pattern = "a";
        formatter = new SimpleDateFormat(pattern);
        final String c = formatter.format(date).toLowerCase();

        String symbol = "";
        switch (a) {
        case "1":
            symbol = "st";
            break;
        case "2":
            symbol = "nd";
            break;
        case "3":
            symbol = "rd";
            break;
        default:
            symbol = "th";
            break;
        }

        return "" + a + symbol + " of " + b + c;
    }

}
