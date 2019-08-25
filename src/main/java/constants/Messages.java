package com.nwjbrandon.duke.constants;

public class Messages {

    public static String divider = "____________________________________________________________";

    public Messages() {
    }

    public static void greetingMessage() {
        System.out.print("\t");
        System.out.println(divider);
        System.out.print("\t");
        System.out.println(" Hello! I'm Duke");
        System.out.print("\t");
        System.out.println(" What can I do for you?");
        System.out.print("\t");
        System.out.println(divider);
        System.out.println();
    }

    public static void farewellMessage() {
        System.out.print("\t");
        System.out.println(divider);
        System.out.print("\t");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.print("\t");
        System.out.println(divider);
        System.out.println();
    }

}
