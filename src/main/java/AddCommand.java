public class AddCommand extends Command {
    private String arguments;
    private String command;
    AddCommand(String command, String arguments){
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        tasks.add(this.command, this.arguments);
    }

}
