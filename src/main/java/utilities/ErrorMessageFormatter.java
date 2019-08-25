package com.nwjbrandon.duke.utilities;

import com.nwjbrandon.duke.constants.Messages;

public class ErrorMessageFormatter {

    private String errorMessage;

    public ErrorMessageFormatter(String errorMessage) {
        this.errorMessage = errorMessage;
        this.showError();
    }

    public void showError() {
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.print("\t ");
        System.out.println(errorMessage);
        System.out.print("\t");
        System.out.println(Messages.divider);
        System.out.println();
    }

}
