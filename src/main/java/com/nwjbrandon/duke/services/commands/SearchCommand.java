package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.services.validations.Parser;

public class SearchCommand extends Command {

    /**
     * Description of task.
     */
    private String taskDescription;

    /**
     * Create search command.
     * @param userInput input by user.
     * @param command type of command.
     * @throws DukeEmptyCommandException empty command.
     */
    public SearchCommand(String userInput, String command) throws DukeEmptyCommandException {
        this.taskDescription = parseCommand(userInput, command);
    }

    /**
     * Get description of task.
     * @return description of task.
     */
    public String getTaskDescription() {
        return taskDescription;
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
