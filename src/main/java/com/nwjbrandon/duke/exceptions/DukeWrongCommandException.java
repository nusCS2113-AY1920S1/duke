package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.constants.Messages;

public class DukeWrongCommandException extends DukeException {

    public DukeWrongCommandException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }
}
