package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.tasks.Deadlines;
import com.nwjbrandon.duke.services.tasks.Events;
import com.nwjbrandon.duke.services.reader.FileReader;
import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringIndexOutOfBoundsException;

public class TaskManager {

    private ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    /**
     * Add tasks.
     */
    private void addTask(Task task) {
        tasksList.add(task);
    }

    /**
     * Differentiate tasks.
     */
    private void loadTasksList(String taskDetails) throws Exception {
        String[] details = taskDetails.split("\\s\\|\\s");
        Task task;
        if (details[0].equals("T")) {
            task = new Todos(details, numberOfTasks());
        } else if (details[0].equals("D")) {
            task = new Deadlines(details, numberOfTasks());
        } else {
            task = new Events(details, numberOfTasks());
        }

        if (details[1].equals("1")) {
            task.setDoneStatus(true);
        }
        addTask(task);
    }

    /**
     * Load data from file.
     */
    public void loadData(String filePath) throws Exception {
        ArrayList<String> inputList = FileReader.loadData(filePath);
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
        for (Task task : tasksList) {
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

    private int numberOfTasks() {
        return tasksList.size();
    }

    /**
     * Save data to file.
     */
    public void saveData(String filePath) throws Exception {
        FileReader.saveData(filePath, this.saveTaskList());
    }

    /**
     * Read the console input.
     * @return input value
     */
    private static String readInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    /**
     * Show the list of tasks.
     */
    private void showTasksList() {
        StringBuilder output = new StringBuilder("\t" + Messages.divider + "\n"
                + "\t Here are the tasks in your lists:\n");
        for (int i = 0; i < tasksList.size(); i++) {
            output.append("\t ").append(i + 1).append(".").append(tasksList.get(i)
                    .toTaskDescriptionString()).append("\n");
        }
        output.append("\t").append(Messages.divider).append("\n");
        System.out.println(output);
    }

    /**
     * Run the application.
     * @return whether the program is done
     */
    public boolean run() {
        try {
            String userInput = readInput();
            if (userInput.equals("list")) {
                showTasksList();
            } else if (userInput.startsWith("done")) {
                markDone(checkCommandInput(userInput, "done"));
            } else if (userInput.startsWith("todo")) {
                Todos task = new Todos(checkCommandInput(userInput, "todo"), numberOfTasks());
                addTask(task);
            } else if (userInput.startsWith("event")) {
                Events task = new Events(checkCommandInput(userInput, "event"), numberOfTasks());
                addTask(task);
            } else if (userInput.startsWith("deadline")) {
                Deadlines task = new Deadlines(checkCommandInput(userInput, "deadline"), numberOfTasks());
                addTask(task);
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

    /**
     * Checks whether string can be converted to integer.
     * @return integer
     */
    private Integer checkStringToIntConversion(String taskIndexString) throws DukeTypeConversionException {
        try {
            return Integer.parseInt(taskIndexString);
        } catch (Exception e) {
            throw new DukeTypeConversionException();
        }
    }

    /**
     * Checks whether index is within the arraylist.
     * @return integer
     */
    private Integer checkIndex(Integer index) throws DukeOutOfBoundException {
        if (tasksList.size() <= index || index < 0) {
            throw new DukeOutOfBoundException();
        } else {
            return index;
        }
    }

    /**
     * Validates user input for done.
     * @return string after done
     */
    private String checkUserInput(String userInput) throws DukeEmptyCommandException {
        if (userInput.isBlank()) {
            throw new DukeEmptyCommandException("done");
        } else {
            return userInput;
        }
    }

    /**
     * Validates user input for instructions
     * @return string for correct command input
     */
    private String checkCommandInput(String userInput, String instruction) throws DukeEmptyCommandException {
        try {
            return userInput.substring(instruction.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyCommandException(instruction);
        }
    }

    /**
     * Mark task as done.
     */
    private void markDone(String userInput) throws DukeException {
        String taskIndexString = checkUserInput(userInput);
        Integer taskIndex = checkStringToIntConversion(taskIndexString);
        tasksList.get(checkIndex(taskIndex - 1)).setDoneStatus(true);
    }

}
