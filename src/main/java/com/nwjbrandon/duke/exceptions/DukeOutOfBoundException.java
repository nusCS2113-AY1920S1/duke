package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class DukeOutOfBoundException extends DukeException {

    public DukeOutOfBoundException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String errorMessage = "☹ OOPS!!! I'm cannot find the task.";
        String output = "\t" + Ui.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Ui.divider + "\n";
        System.out.println(output);
    }
}
