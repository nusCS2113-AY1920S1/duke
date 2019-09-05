package com.nwjbrandon.duke.services.validations;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;

public class Parser {

    /**
     * Check whether string can convert to integer.
     * @param taskIndexString task index in string.
     * @return task index.
     * @throws DukeTypeConversionException type conversion error.
     */
    public static Integer checkStringToIntegerConversion(String taskIndexString) throws DukeTypeConversionException {
        try {
            return Integer.parseInt(taskIndexString);
        } catch (Exception e) {
            throw new DukeTypeConversionException();
        }
    }

    /**
     * Check whether index is within list.
     * @param index task index.
     * @param size number of tasks.
     * @return task index.
     * @throws DukeOutOfBoundException index falls outside of list.
     */
    public static Integer checkIndex(Integer index, int size) throws DukeOutOfBoundException {
        if (size <= index || index < 0) {
            throw new DukeOutOfBoundException();
        } else {
            return index;
        }
    }

    /**
     * Check whether the user input is blank.
     * @return string if no blanks found.
     */
    private static String checkBlanksInUserInput(String userInput, String command) throws DukeEmptyCommandException {
        if (userInput.isBlank()) {
            throw new DukeEmptyCommandException(command);
        } else {
            return userInput;
        }
    }

    /**
     * Check whether the instruction is found in the input.
     * @param userInput input by user.
     * @param command type of command.
     * @return instruction in input if found.
     * @throws DukeEmptyCommandException empty command.
     */
    private static String checkLengthOfCommandInput(String userInput, String command) throws DukeEmptyCommandException {
        try {
            return userInput.substring(command.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyCommandException(command);
        }
    }

    /**
     * Validates user input before creating task.
     * @param userInput input by user.
     * @param command type of command.
     * @return instructions in input
     * @throws DukeEmptyCommandException empty command.
     */
    public static String checkCommandInput(String userInput, String command) throws DukeEmptyCommandException {
        return checkBlanksInUserInput(checkLengthOfCommandInput(userInput, command), command);
    }

}
