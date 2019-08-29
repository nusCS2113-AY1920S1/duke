public class Task {
    protected String description;
    protected boolean isDone;
    public static long counter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter++;
    }
    public Task(){
        this.isDone = false;
        counter++;
    }

    public long numTasksCreated(){
        return this.counter;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markDone(){
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }
    public String toList(){
        return "[?][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

