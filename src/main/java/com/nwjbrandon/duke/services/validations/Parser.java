package com.nwjbrandon.duke.services.validations;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;

public class Parser {

    /**
     * Checks whether string can be converted to integer.
     * @return integer
     */
    private static Integer checkStringToIntegerConversion(String taskIndexString) throws DukeTypeConversionException {
        try {
            return Integer.parseInt(taskIndexString);
        } catch (Exception e) {
            throw new DukeTypeConversionException();
        }
    }

    /**
     * Checks whether index is within the list.
     * @return integer
     */
    private static Integer checkIndex(Integer index, int size) throws DukeOutOfBoundException {
        if (size <= index || index < 0) {
            throw new DukeOutOfBoundException();
        } else {
            return index;
        }
    }

    /**
     * Validates user input for done.
     * @return string after checking
     */
    private static String checkBlanksInUserInput(String userInput, String command) throws DukeEmptyCommandException {
        if (userInput.isBlank()) {
            throw new DukeEmptyCommandException(command);
        } else {
            return userInput;
        }
    }

    /**
     * Validates user input for instructions
     * @return string for correct command input
     */
    private static String checkLengthOfCommandInput(String userInput, String command) throws DukeEmptyCommandException {
        try {
            return userInput.substring(command.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyCommandException(command);
        }
    }

    public static int getIndexFromCommandInput(String userInput, String command, int size) throws DukeException {
        String taskIndexString = Parser.checkBlanksInUserInput(checkLengthOfCommandInput(userInput, command), command) ;
        Integer taskIndex = Parser.checkStringToIntegerConversion(taskIndexString);
        return Parser.checkIndex(taskIndex - 1, size);
    }

    public static String checkCommandInput(String userInput, String command) throws DukeEmptyCommandException {
        return checkBlanksInUserInput(checkLengthOfCommandInput(userInput, command), command);
    }

}
