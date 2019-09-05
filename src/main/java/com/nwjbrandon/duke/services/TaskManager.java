package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.constants.TaskCommands;
import com.nwjbrandon.duke.services.commands.AddCommand;
import com.nwjbrandon.duke.services.commands.DoneCommand;
import com.nwjbrandon.duke.services.commands.DeleteCommand;
import com.nwjbrandon.duke.services.commands.SearchCommand;
import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.TaskList;
import com.nwjbrandon.duke.services.tasks.Deadlines;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.tasks.Events;
import com.nwjbrandon.duke.services.storage.Storage;
import com.nwjbrandon.duke.services.interfaces.Ui;
import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    /**
     * Container for list of task.
     */
    private TaskList tasksList;

    /**
     * Create task manager.
     */
    public TaskManager() {
        tasksList = new TaskList();
    }

    /**
     * Load tasks from file input.
     * @param taskDetails information of tasks from file input.
     * @throws DukeEmptyCommandException empty command.
     */
    private void loadTasksList(String taskDetails) throws DukeEmptyCommandException {
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
     * @param filePath file path.
     * @throws FileNotFoundException file not found.
     * @throws DukeEmptyCommandException empty command.
     */
    public void loadData(String filePath) throws FileNotFoundException, DukeEmptyCommandException {
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
     * @param filePath file path.
     * @throws IOException IO error.
     */
    public void saveData(String filePath) throws IOException {
        Storage.saveData(filePath, this.saveTaskList());
    }

    /**
     * Show the list of tasks.
     */
    private void showTasksList() {
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
            if (userInput.equals(TaskCommands.LIST.toString())) {
                this.showTasksList();
            } else if (userInput.startsWith(TaskCommands.DONE.toString())) {
                DoneCommand c = new DoneCommand(userInput, TaskCommands.DONE.toString(), size);
                tasksList.markDone(c.getTaskIndex());
            } else if (userInput.startsWith(TaskCommands.TODO.toString())) {
                AddCommand c = new AddCommand(userInput, TaskCommands.TODO.toString(), size + 1);
                tasksList.addTask(c.setTask());
            } else if (userInput.startsWith(TaskCommands.EVENT.toString())) {
                AddCommand c = new AddCommand(userInput, TaskCommands.EVENT.toString(), size + 1);
                tasksList.addTask(c.setTask());
            } else if (userInput.startsWith(TaskCommands.DEADLINE.toString())) {
                AddCommand c = new AddCommand(userInput, TaskCommands.DEADLINE.toString(), size + 1);
                tasksList.addTask(c.setTask());
            } else if (userInput.startsWith(TaskCommands.DELETE.toString())) {
                DeleteCommand c = new DeleteCommand(userInput, TaskCommands.DELETE.toString(), size);
                tasksList.removeTask(c.getTaskIndex());
            } else if (userInput.startsWith(TaskCommands.FIND.toString())) {
                SearchCommand c = new SearchCommand(userInput, TaskCommands.FIND.toString());
                tasksList.searchTask(c.getTaskDescription());
            } else if (userInput.equals(TaskCommands.BYE.toString())) {
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
