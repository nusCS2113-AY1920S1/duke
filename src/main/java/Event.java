public class Event extends Task {

    public Event(String description , String time) {
        super(description);
        this.time = time;
        this.type = "E";
    }
    public String toString() {
        return "[" + this.type +"][" + this.getStatusIcon() + "] " + this.description + " (at: " + time + ")";
    }


}
