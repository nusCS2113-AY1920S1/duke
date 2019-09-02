package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.services.interfaces.Ui;
import com.nwjbrandon.duke.services.validations.Parser;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList;

    public TaskList () {
        tasksList = new ArrayList<>();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Add tasks.
     */
    public void addTask(Task task) {
        tasksList.add(task);
    }

    /**
     * Remove task
     */
    public void removeTask(String userInput, String command) throws DukeException {
        int taskIndex = Parser.getIndexFromCommandInput(userInput, command, numberOfTasks());
        tasksList.get(taskIndex).removeTaskString(this.numberOfTasks());
        tasksList.remove(taskIndex);
    }

    /**
     * Mark task as done.
     */
    public void markDone(String userInput, String command) throws DukeException {
        int taskIndex = Parser.getIndexFromCommandInput(userInput, command, numberOfTasks());
        tasksList.get(taskIndex).setDoneStatus(true);
    }

    public int numberOfTasks() {
        return tasksList.size();
    }

    public Task getTask(int taskIndex) {
        return tasksList.get(taskIndex);
    }

    /**
     * Show the list of tasks by keywords.
     */
    public void searchTask(String keyword) {
        Ui.showSearchTask(this, keyword);
    }

}
