package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.constants.Messages;

public class DukeException extends Exception {

    private String errorMessage;

    public DukeException() {
    }

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * show the error message.
     */
    public void showError() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

}
