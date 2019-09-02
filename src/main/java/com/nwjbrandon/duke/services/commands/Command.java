package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeException;

public abstract class Command {

    Command() {
    }

    abstract public String parseCommand(String userInput, String command) throws DukeException;

}
