package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.validations.Parser;

public class SearchCommand extends Command {
    private String taskDescription;

    public SearchCommand(String userInput, String command) throws DukeEmptyCommandException {
        this.taskDescription = parseCommand(userInput, command);
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String parseCommand(String userInput, String command) throws DukeEmptyCommandException {
        return Parser.checkCommandInput(userInput, command);
    };
}
