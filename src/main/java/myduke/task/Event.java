package myduke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date date;
    public Event(String description) {
        super(description);
        this.type = "E";
    }

    public void parseTime (String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    public String toString() {
        return ("[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + " " + this.date).trim();
    }


}
