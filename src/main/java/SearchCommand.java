public class SearchCommand extends Command {
    private String command;
    private String arguments;
    SearchCommand(String command, String input){
        this.command = command;
        this.arguments = input;
    }
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        if(this.command.matches("find")){
            tasks.find(this.arguments);
        }
    }
}
