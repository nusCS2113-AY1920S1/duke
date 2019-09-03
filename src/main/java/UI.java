import java.util.Scanner;

public class UI {
    private Scanner sc;
    private String loadingError = "Formatting Issues Encountered. New Task List initialized";
    private String line = "____________________________________________________________";
    public UI(){
    }
    public void showWelcome(){
        this.sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }
    public String readCommand() throws DukeException {
        if(sc.hasNextLine()) return sc.nextLine();
        else throw new DukeException("There are no more lines to be read");

    }
    public void showLine(){
        System.out.println(line);
    }
    public void showError(String error){
        System.out.println(error);
    }
    public void showLoadingError(){
        System.out.println(loadingError);
    }
    public void close(){
        this.sc.close();
    }
}
