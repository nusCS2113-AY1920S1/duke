import java.util.Scanner;
import java.util.Vector;



public class Duke {
    /**
     * Why checkstyle liddat.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        Vector<String> v = new Vector<>();
        int[] checkbox = new int[100];

        System.out.println(line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line);

        String typing = input.nextLine();
        String[] splitarray = typing.split(" ", 2);
        String first = splitarray[0];
        String second = "0";
        if (splitarray.length == 2)
        {
            second = splitarray[1];
        }

        while (!first.equals("bye"))
        {
            if (first.equals("done"))
            {
                int temp = Integer.parseInt(second);
                System.out.println(temp);
                System.out.print(line);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[✓] " + v.get(temp - 1) + "\n" + line);
                checkbox[temp - 1] = 1;
            }

            else if (!first.equals("list"))
            {
                v.add(typing);
                System.out.println(line + "added: " + typing + "\n" + line);
            }

            else
            {
                System.out.print(line);
                for (int i = 0; i < v.size(); i += 1)
                {
                    System.out.print((i + 1) + ". ");
                    if (checkbox[i] != 1)
                    {
                        System.out.print("[✗] ");
                    }
                    else
                    {
                        System.out.print("[✓] ");
                    }
                    System.out.println(v.get(i));
                }
                System.out.print(line);
            }

            typing = input.nextLine();
            splitarray = typing.split(" ", 2);
            first = splitarray[0];
            if (splitarray.length == 2)
            {
                second = splitarray[1];
            }
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "____________________________________________________________\n");

    }
}
