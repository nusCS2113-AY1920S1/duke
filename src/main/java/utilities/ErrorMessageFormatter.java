package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;

public class ErrorMessageFormatter {

    private String errorMessage;

    public ErrorMessageFormatter(String errorMessage) {
        this.errorMessage = errorMessage;
        this.showError();
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
