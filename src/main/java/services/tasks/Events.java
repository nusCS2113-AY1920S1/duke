package com.nwjbrandon.duke.services.tasks;

import com.nwjbrandon.duke.constants.Messages;
import com.nwjbrandon.duke.exceptions.DukeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Events extends Task {

    public Events(String taskName) throws Exception {
        super(taskName);
    }

    public Events(String[] taskDetails) throws Exception {
        this("event " + taskDetails[2] + " /at " + taskDetails[3]);
    }

    @Override
    public String formatTaskName(String taskName) throws Exception {
        String task = checkUserInput(taskName, 6, "events");
        String parts[] = task.split(" /at ");
        this.description = parts[0];
        this.by = parts[1];
        return parts[0] + " (at: " + dateFormatter(parts[1]) + ")";
    }

    /**
     * get the formatted task string to show for Todos.
     * @return formatted task string to show for Todos
     */
    @Override
    public String toString() {
        String output = "Got it. I've added this task:\n"
                      + "\t   " + this.toTaskString() + "\n"
                      + "\t Now you have " + this.getNumberOfTodos() + " tasks in the list.";
        return output;
    }

    @Override
    public String toTaskString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskName();
    }

    private String dateFormatter(String originalDate) throws ParseException {
        String pattern = "dd/MM/yyyy hhmm";
        SimpleDateFormat originalFormat = new SimpleDateFormat(pattern);
        Date date = originalFormat.parse(originalDate);

        String displayPattern = "MMMM yyyy, h";
        SimpleDateFormat displayFormat = new SimpleDateFormat(displayPattern);
        String b = displayFormat.format(date);

        displayPattern = "d";
        displayFormat = new SimpleDateFormat(displayPattern);
        String a = displayFormat.format(date);

        displayPattern = "a";
        displayFormat = new SimpleDateFormat(displayPattern);
        String c = displayFormat.format(date).toLowerCase();

        String symbol = "";
        if (a.equals("1")) {
            symbol = "st";
        } else if (a.equals("2")) {
            symbol = "nd";
        } else if (a.equals("3")) {
            symbol = "rd";
        } else {
            symbol = "th";
        }

        return "" + a + symbol + " of " + b + c ;
    }

}

