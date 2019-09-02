package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class DukeException extends Exception {

    private String errorMessage;

    DukeException() {
    }

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * show the error message.
     */
    public void showError() {
        Ui.showError(errorMessage);
    }

}
