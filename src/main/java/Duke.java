import java.util.Scanner;



public class Duke {
    /**
     * Why checkstyle liddat.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("____________________________________________________________\n"
        + " Hello! I'm Duke\n" + " What can I do for you?\n" + "____________________________________________________________\n");

        String typing = input.nextLine();

        while (!typing.equals("bye"))
        {
            System.out.println("____________________________________________________________\n" +
             typing + "\n" + "____________________________________________________________\n");
            typing = input.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "____________________________________________________________\n");

    }
}
