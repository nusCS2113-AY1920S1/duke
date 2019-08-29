public class Task {
    protected String description;
    protected boolean isDone;
    protected String dueDate;
    public static long counter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter++;
    }
    public Task(){

    }


    public long numTasksCreated(){
        return counter;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markDone(){
        this.isDone = true;
    }
    public boolean checkCompletion() {return this.isDone;}
    public String getType(){ return "G";}
    public String getDescription() {
        return this.description;
    }
    public String getDueDate() { return this.dueDate; }

    public String toList(){
        return "[?][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
