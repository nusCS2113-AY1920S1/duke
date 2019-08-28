package myduke.task;

public class Event extends Task {
    protected String time;
    public Event(String description) {
        super(description);
        this.type = "E";
        this.time = "";
    }

    public void setTime (String time) {
        this.time = " (at: " + time + ")";
    }

    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.time;
    }


}
