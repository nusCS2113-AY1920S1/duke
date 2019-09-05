package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.constants.TaskCommands;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandException;
import com.nwjbrandon.duke.services.tasks.Deadlines;
import com.nwjbrandon.duke.services.tasks.Events;
import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.validations.Parser;

public class AddCommand extends Command {

    /**
     * Description of task.
     */
    private String taskDescription;

    /**
     * Number of tasks.
     */
    private int size;

    /**
     * Type of command.
     */
    private String command;

    /**
     * Create add command.
     * @param userInput input by user.
     * @param command type of command.
     * @param size number of tasks.
     * @throws DukeEmptyCommandException empty command.
     */
    public AddCommand(String userInput, String command, int size) throws DukeEmptyCommandException {
        this.taskDescription = parseCommand(userInput, command);
        this.size = size;
        this.command = command;
    }

    /**
     * Create specific task.
     * @return specific task.
     * @throws DukeWrongCommandException wrong command.
     */
    public Task setTask() throws DukeWrongCommandException {
        if (command.equals(TaskCommands.TODO.toString())) {
            return new Todos(taskDescription, size);
        } else if (command.equals(TaskCommands.DEADLINE.toString())) {
            return new Deadlines(taskDescription, size);
        } else if (command.equals(TaskCommands.EVENT.toString())) {
            return new Events(taskDescription, size);
        } else {
            throw new DukeWrongCommandException();
        }
    }

    /**
     * Validate user input.
     * @param userInput input by user.
     * @param command type of command.
     * @return instruction in input.
     * @throws DukeEmptyCommandException empty command.
     */
    @Override
    public String parseCommand(String userInput, String command) throws DukeEmptyCommandException {
        return Parser.checkCommandInput(userInput, command);
    }

}
