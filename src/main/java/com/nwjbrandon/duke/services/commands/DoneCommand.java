package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.services.validations.Parser;

public class DoneCommand extends Command {

    /**
     * Index of task in string.
     */
    private String taskIndexString;

    /**
     * Number of tasks.
     */
    private int size;

    /**
     * Create done command.
     * @param userInput input by user.
     * @param command type of command.
     * @param size number of tasks.
     * @throws DukeEmptyCommandException empty command.
     */
    public DoneCommand(String userInput, String command, int size) throws DukeEmptyCommandException {
        this.size = size;
        this.taskIndexString = parseCommand(userInput, command);
    }

    /**
     * Get task index.
     * @return task index.
     * @throws DukeOutOfBoundException index is not within given list.
     * @throws DukeTypeConversionException input cannot convert into integer.
     */
    public int getTaskIndex() throws DukeOutOfBoundException, DukeTypeConversionException {
        Integer taskIndex = Parser.checkStringToIntegerConversion(taskIndexString);
        return Parser.checkIndex(taskIndex - 1, size);
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
