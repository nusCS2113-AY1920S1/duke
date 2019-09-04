public abstract class Command {
    private Tasklist list;
    private UI ui;
    private Storage storage;
    boolean exit = false;
    Command() {}
    public abstract void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException;
    boolean isExit(){return this.exit;}
}
