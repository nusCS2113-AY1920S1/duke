package com.nwjbrandon.duke.services;

import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.tasks.Deadlines;
import com.nwjbrandon.duke.services.tasks.Events;
import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class TaskManager {

    private ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<Task>();
    }

    private void loadTasksList(String taskDetails) throws Exception {
        String[] details = taskDetails.split("\\s\\|\\s");
        Task task;
        if (details[0].equals("T")) {
            task = new Todos(details);
        } else if (details[0].equals("D")) {
            task = new Deadlines(details);
        } else {
            task = new Events(details);
        }
        if (details[1].equals("1")) {
            task.setDoneStatus(true);
        }
        tasksList.add(task);
    }

    public void loadData() throws Exception {
        File file = new File("./data/duke.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String taskDetails = scan.nextLine();
            this.loadTasksList(taskDetails);
        }
        this.showTasksList();
    }

    private String saveTaskList() {
        String output = "";
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i) instanceof Todos) {
                output += "T";
            } else if (tasksList.get(i) instanceof Deadlines) {
                output += "D";
            } else {
                output += "E";
            }
            output +=  " | "
                    + (tasksList.get(i).getIsDoneStatus() ? "1" : "0")
                    + " | "
                    + tasksList.get(i).getDescription();
            if (!(tasksList.get(i) instanceof Todos)) {
                output +=  " | "
                        + tasksList.get(i).getBy();
            }
            output += "\n";
        }
        return output;
    }

    public void saveData() throws Exception {
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(this.saveTaskList());
        fw.close();
    }

    /**
     * read the console input.
     * @return input value
     */
    private static String readInput() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput;
    }

    /**
     * show the list of tasks.
     */
    private void showTasksList() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Here are the tasks in your lists:\n";
        for (int i = 0; i < tasksList.size(); i++) {
            output += "\t " + (i + 1) + "." + tasksList.get(i).toTaskString() + "\n";
        }
        output += "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    /**
     * run the application.
     * @return whether the program is done
     */
    public boolean run() throws Exception {
        try {
            String userInput = readInput();
            if (userInput.equals("list")) {
                showTasksList();
            } else if (userInput.startsWith("done")) {
                this.markDone(userInput);
            } else if (userInput.startsWith("todo")) {
                Todos task = new Todos(userInput);
                tasksList.add(task);
            } else if (userInput.startsWith("event")) {
                Events task = new Events(userInput);
                tasksList.add(task);
            } else if (userInput.startsWith("deadline")) {
                Deadlines task = new Deadlines(userInput);
                tasksList.add(task);
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
        } catch (Exception e) {
            DukeException err = new DukeException("An uncaught error has occurred!");
            err.showError();
        }
        return true;
    }

    private Integer checkStringToIntConversion(String taskIndexString) throws DukeTypeConversionException {
        try {
            return Integer.valueOf(taskIndexString) - 1;
        } catch (Exception e) {
            throw new DukeTypeConversionException();
        }
    }

    private Integer checkIndex(Integer index) throws DukeOutOfBoundException {
        if (tasksList.size() <= index || index < 0) {
            throw new DukeOutOfBoundException();
        } else {
            return index;
        }
    }

    private String checkUserInput(String userInput, int start, String baseTaskName) throws DukeEmptyCommandException {
        if (userInput.length() <= start) {
            throw new DukeEmptyCommandException(baseTaskName);
        } else {
            return userInput.substring(start);
        }
    }

    private void markDone(String userInput) throws DukeException {
        String taskIndexString = checkUserInput(userInput, 5, "done");
        Integer taskIndex = checkStringToIntConversion(taskIndexString);
        tasksList.get(checkIndex(taskIndex)).setDoneStatus(true);
    }

}
