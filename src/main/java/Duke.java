import java.util.Scanner;
import java.util.Vector;



public class Duke {
    /**
     * Why checkstyle liddat.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Vector v = new Vector();

        System.out.println("____________________________________________________________\n"
        + " Hello! I'm Duke\n" + " What can I do for you?\n" + "____________________________________________________________\n");

        String typing = input.nextLine();

        while (!typing.equals("bye"))
        {
            if (!typing.equals("list"))
            {
                v.add(typing);
                System.out.println("____________________________________________________________\n" +
                        "added: " + typing + "\n" + "____________________________________________________________\n");
            }

            else
            {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < v.size(); i += 1)
                {
                    System.out.println((i + 1) + ". " + v.get(i));
                }
                System.out.println("____________________________________________________________");
            }
            typing = input.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "____________________________________________________________\n");

    }
}
