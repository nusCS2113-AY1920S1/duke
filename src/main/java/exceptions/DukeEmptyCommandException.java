package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;

public class DukeEmptyCommandException extends DukeException {

    private String errorMessage = "☹ OOPS!!! The description of a %s cannot be empty.";

    public DukeEmptyCommandException(String taskName) {
        this.errorMessage = String.format(this.errorMessage, taskName);
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + this.errorMessage + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }
}
