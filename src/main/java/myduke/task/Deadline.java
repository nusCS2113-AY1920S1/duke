package myduke.task;

public class Deadline extends Task {

    public Deadline(String description , String time) {
        super(description);
        this.time = time;
        this.type = "D";
    }

    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + " (by: " + time + ")";
    }
}
