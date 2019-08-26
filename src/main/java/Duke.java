import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello \n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        Scanner sc= new Scanner(System.in);
        ArrayList<String> arrlist=new ArrayList<>();

        while(true){
            String cmd = sc.nextLine();

            if(cmd.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                exit(0);
            }else if(cmd.equals("list")){
                int count=1;
                for(String s:arrlist){
                    System.out.println(count+++". "+s);
                }

            }else{
                System.out.println("added: "+cmd);
                arrlist.add(cmd);
            }
        }

    }
}
