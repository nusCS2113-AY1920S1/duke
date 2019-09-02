package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeOutOfBoundException;
import com.nwjbrandon.duke.exceptions.DukeTypeConversionException;
import com.nwjbrandon.duke.services.validations.Parser;

public class DoneCommand extends Command {
    private String taskIndexString;
    private int size;

    public DoneCommand(String userInput, String command, int size) throws DukeEmptyCommandException {
        this.size = size;
        this.taskIndexString = parseCommand(userInput, command);
    }

    public int getTaskIndex() throws DukeOutOfBoundException, DukeTypeConversionException {
        Integer taskIndex = Parser.checkStringToIntegerConversion(taskIndexString);
        return Parser.checkIndex(taskIndex - 1, size);
    }

    @Override
    public String parseCommand(String userInput, String command) throws DukeEmptyCommandException {
        return Parser.checkCommandInput(userInput, command);
    };
}
