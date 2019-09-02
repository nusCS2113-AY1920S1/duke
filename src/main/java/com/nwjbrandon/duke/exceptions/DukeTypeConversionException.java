package com.nwjbrandon.duke.exceptions;

import com.nwjbrandon.duke.services.interfaces.Ui;

public class DukeTypeConversionException extends DukeException {

    public DukeTypeConversionException() {
    }

    /**
     * show the error message.
     */
    @Override
    public void showError() {
        String errorMessage = "☹ OOPS!!! Cannot convert types.";
        String output = "\t" + Ui.divider + "\n"
                      + "\t " + errorMessage + "\n"
                      + "\t" + Ui.divider + "\n";
        System.out.println(output);
    }
}
