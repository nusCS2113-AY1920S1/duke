package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;

public class DukeWrongCommandException extends DukeException {

    final private String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public DukeWrongCommandException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }
}
