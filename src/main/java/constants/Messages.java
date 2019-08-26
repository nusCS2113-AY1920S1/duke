package com.nwjbrandon.duke.constants;

public class Messages {

    public static String divider = "____________________________________________________________";

    public Messages() {
    }

    /**
     * Show greeting message.
     */
    public static void greetingMessage() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Hello! I'm Duke\n"
                      + "\t What can I do for you?\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

    /**
     * Show farewell message.
     */
    public static void farewellMessage() {
        String output = "\t" + Messages.divider + "\n"
                      + "\t Bye. Hope to see you again soon!\n"
                      + "\t" + Messages.divider + "\n";
        System.out.println(output);
    }

}
