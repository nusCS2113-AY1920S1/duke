package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;

abstract class Command {

    /**
     * Create new command.
     */
    Command() {
    }

    /**
     * Validate user input.
     * @param userInput input by user.
     * @param command type of command.
     * @return instruction in input.
     * @throws DukeEmptyCommandException empty command.
     */
    abstract String parseCommand(String userInput, String command) throws DukeEmptyCommandException;

}
