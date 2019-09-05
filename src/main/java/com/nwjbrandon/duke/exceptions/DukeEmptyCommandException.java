package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class DukeEmptyCommandException extends DukeException {

    /**
     * Error message for empty command.
     */
    private String errorMessage = "☹ OOPS!!! The description of a %s cannot be empty.";

    /**
     * Create error exception for empty command.
     * @param taskName name of command.
     */
    public DukeEmptyCommandException(String taskName) {
        errorMessage = String.format(errorMessage, taskName);
    }

    /**
     * Show the error message for empty command.
     */
    @Override
    public void showError() {
        Ui.showError(errorMessage);
    }

}
