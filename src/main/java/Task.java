public class Task{

    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
        this.time = "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return this.time;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

