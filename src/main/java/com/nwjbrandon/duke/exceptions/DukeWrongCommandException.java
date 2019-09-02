package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class DukeWrongCommandException extends DukeException {

    public DukeWrongCommandException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        Ui.showError(errorMessage);
    }
}
