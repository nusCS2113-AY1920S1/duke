package com.nwjbrandon.duke.services.commands;

import com.nwjbrandon.duke.constants.TaskNames;
import com.nwjbrandon.duke.exceptions.DukeEmptyCommandException;
import com.nwjbrandon.duke.exceptions.DukeWrongCommandException;
import com.nwjbrandon.duke.services.tasks.Deadlines;
import com.nwjbrandon.duke.services.tasks.Events;
import com.nwjbrandon.duke.services.tasks.Task;
import com.nwjbrandon.duke.services.tasks.Todos;
import com.nwjbrandon.duke.services.validations.Parser;

public class AddCommand extends Command {

    private String taskDescription;
    private int size;
    private String command;

    public AddCommand(String userInput, String command, int size) throws DukeEmptyCommandException {
        this.taskDescription = parseCommand(userInput, command);
        this.size = size;
        this.command = command;
    }

    public Task setTask() throws Exception {
        if (command.equals(TaskNames.TODO.toString())){
            return new Todos(taskDescription, size);
        } else if (command.equals(TaskNames.DEADLINE.toString())) {
            return new Deadlines(taskDescription, size);
        } else if (command.equals(TaskNames.EVENT.toString())) {
            return new Events(taskDescription, size);
        } else {
            throw new DukeWrongCommandException();
        }
    };

    @Override
    public String parseCommand(String userInput, String command) throws DukeEmptyCommandException {
        return Parser.checkCommandInput(userInput, command);
    };
}
