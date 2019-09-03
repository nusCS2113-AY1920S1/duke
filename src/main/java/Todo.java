public class Todo extends Task {
    protected String type = "T";
    public Todo(String description){
        super(description);
    }
    public Todo(String bool, String description){
        super(description);
        this.isDone = (1 == Integer.parseInt(bool));
    }
    @Override
    public String toList(){
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
    @Override
    public String getType(){ return "T";}
}
