import myduke.MyDuke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        MyDuke myduke = new MyDuke();
        myduke.greet();
        Scanner scan = new Scanner(System.in);
        String description   = scan.nextLine();

        while(true){
            if(description.equals("bye")){
                myduke.exit();
                break;
            }else if (description.equals("list")){
                myduke.showTaskBox();
                description = scan.nextLine();
            }else{
                myduke.echo(description);
                description = scan.nextLine();
            }
        }
    }
}
