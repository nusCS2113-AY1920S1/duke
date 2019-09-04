import java.util.Scanner;

class UI {
    private Scanner sc;
    private String loadingError = "Formatting Issues Encountered. New Task List initialized";
    private String line = "____________________________________________________________";
    private String goodBye = "Bye. Hope to see you again soon!";
    UI(){
    }
    void showWelcome(){
        this.sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }
    String readCommand() throws DukeException {
        if(sc.hasNextLine()) return sc.nextLine();
        else throw new DukeException("There are no more lines to be read");

    }
    void showLine(){
        System.out.println(this.line);
    }
    void showError(String error){
        System.out.println(error);
    }
    void showLoadingError(){
        System.out.println(this.loadingError);
    }
    void hastaLaVista(){System.out.println(this.goodBye);}
    void close(){
        this.sc.close();
    }
}
