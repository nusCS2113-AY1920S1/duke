package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.constants.Messages;

public class DukeOutOfBoundException extends DukeException {

    public DukeOutOfBoundException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String errorMessage = "☹ OOPS!!! I'm cannot find the task.";
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }
}
