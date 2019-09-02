package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.services.validations.Parser;
import com.nwjbrandon.duke.services.tasks.*;
import com.nwjbrandon.duke.services.storage.Storage;
import com.nwjbrandon.duke.services.interfaces.Ui;
import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;

import java.util.ArrayList;

public class TaskManager {

    private TaskList tasksList;

    public TaskManager() {
        tasksList = new TaskList();
    }

    /**
     * Differentiate tasks.
     */
    private void loadTasksList(String taskDetails) throws Exception {
        String[] details = taskDetails.split("\\s\\|\\s");
        Task task;
        if (details[0].equals("T")) {
            task = new Todos(details, tasksList.numberOfTasks());
        } else if (details[0].equals("D")) {
            task = new Deadlines(details, tasksList.numberOfTasks());
        } else {
            task = new Events(details, tasksList.numberOfTasks());
        }
        if (details[1].equals("1")) {
            task.setDoneStatus(true);
        }
        tasksList.addTask(task);
    }

    /**
     * Load data from file.
     */
    public void loadData(String filePath) throws Exception {
        ArrayList<String> inputList = Storage.loadData(filePath);
        for (String taskDetails: inputList) {
            loadTasksList(taskDetails);
        }
        showTasksList();
    }

    /**
     * Format data to save.
     * @return data to save
     */
    private String saveTaskList() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasksList.getTasksList()) {
            if (task instanceof Todos) {
                output.append("T");
            } else if (task instanceof Deadlines) {
                output.append("D");
            } else {
                output.append("E");
            }
            output.append(" | ").append(task.getIsDoneStatus() ? "1" : "0").append(" | ").append(task.getTaskName());
            if (!(task instanceof Todos)) {
                output.append(" | ").append(task.getBy());
            }
            output.append("\n");
        }
        return output.toString();
    }

    /**
     * Save data to file.
     */
    public void saveData(String filePath) throws Exception {
        Storage.saveData(filePath, this.saveTaskList());
    }

    /**
     * Show the list of tasks.
     */
    private void showTasksList() {
        int size = tasksList.numberOfTasks();
        Ui.showTasksList(tasksList);
    }

    /**
     * Run the application.
     * @return whether the program is done
     */
    public boolean run() {
        try {
            String userInput = Ui.readInput();
            int size = tasksList.numberOfTasks();
            if (userInput.equals("list")) {
                this.showTasksList();
            } else if (userInput.startsWith("done")) {
                tasksList.markDone(userInput, "done");
            } else if (userInput.startsWith("todo")) {
                Todos task = new Todos(Parser.checkCommandInput(userInput, "todo"), size);
                tasksList.addTask(task);
            } else if (userInput.startsWith("event")) {
                Events task = new Events(Parser.checkCommandInput(userInput, "event"), size);
                tasksList.addTask(task);
            } else if (userInput.startsWith("deadline")) {
                Deadlines task = new Deadlines(Parser.checkCommandInput(userInput, "deadline"), size);
                tasksList.addTask(task);
            } else if (userInput.startsWith("delete")) {
                tasksList.removeTask(userInput, "delete");
            } else if (userInput.startsWith("find")) {
                tasksList.searchTask(Parser.checkCommandInput(userInput, "find"));
            } else if (userInput.equals("bye")) {
                return false;
            } else {
                throw new DukeWrongCommandException();
            }
        } catch (DukeWrongCommandException e) {
            e.showError();
        } catch (DukeOutOfBoundException e) {
            e.showError();
        } catch (DukeEmptyCommandException e) {
            e.showError();
        } catch (DukeTypeConversionException e) {
            e.showError();
        } catch (Exception e) {
            DukeException err = new DukeException(e.toString());
            err.showError();
        }
        return true;
    }

}
