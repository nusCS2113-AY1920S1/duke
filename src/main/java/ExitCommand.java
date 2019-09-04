public class ExitCommand extends Command {
    ExitCommand(){
        this.exit = true;
    }
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        ui.hastaLaVista();
    }
}
