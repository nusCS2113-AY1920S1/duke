package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.interfaces.Ui;

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
        String output = "\t" + Ui.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Ui.divider + "\n";
        System.out.println(output);
    }

}
