package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class DukeEmptyCommandException extends DukeException {

    private String errorMessage = "☹ OOPS!!! The description of a %s cannot be empty.";

    public DukeEmptyCommandException(String taskName) {
        errorMessage = String.format(errorMessage, taskName);
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        Ui.showError(errorMessage);
    }
}
