package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.constants.Messages;

public class DukeTypeConversionException extends DukeException {

    public DukeTypeConversionException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String errorMessage = "☹ OOPS!!! Cannot convert types.";
        String output = "\t" + Messages.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }
}
