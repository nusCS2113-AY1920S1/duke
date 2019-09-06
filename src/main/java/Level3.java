import java.util.Scanner;

public class Level3 {

    private static int array_size = 100;
    //"\u2713" : "\u2718"

    public static void main(String []argv) throws Exception
    {
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?"); // prints the first line

        String[] store = new String[array_size];
        String[] tick_cross = new String[array_size];
        for (int i=0;i<array_size;i++){
            tick_cross[i]= "[" + "\u2718" + "]";
        }

        int num = 0;
        int readnum;

        boolean check = true;
        while (check) {

            Scanner scanline = new Scanner(System.in); //read in lines
            String readin;
            String blackline = "--------------------";
            readin = scanline.nextLine(); //scan line and check
            String[] break_line = readin.split(" ");




            if (readin.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i=0;i<num;i++){
                    System.out.println( i+1 + ". " + store[i] +tick_cross[i]);
                }
                System.out.println(blackline);
            }

            else if (break_line[0].equals("done")){

                readnum = Integer.parseInt(break_line[1]);
                System.out.println("Nice! I've marked this task as done: ");
                tick_cross[readnum-1] = "[" + "\u2713" + "]" ;
                System.out.println(tick_cross[readnum-1] + store[readnum-1]);
            }

            else if (readin.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n" + blackline);
                check = false;
            }

            else {
                System.out.println("added : " +readin + "\n" +blackline);
                store[num] = readin;
                num +=1;
            }

        }
    }
}