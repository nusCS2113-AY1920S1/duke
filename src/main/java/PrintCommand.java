public class PrintCommand extends Command {
    PrintCommand() {    }
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        tasks.print();
    }
}
