import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    private static final String WHITESPACE = " ";

    public void loadParse(ArrayList<Task> taskList , String input) {
        String[] arr =  input.split(" \\| ");
        String taskType = arr[0].trim();
        String taskStatus = arr[1].trim();
        String taskDescription = arr[2].trim();
        String taskTime = "";

        if (taskType.equals("T")) {
            Task todo = new ToDo(taskDescription);
            if (taskStatus.equals("\u2713")) {
                todo.markAsDone();
            }
            taskList.add(todo);
        }else if (taskType.equals("D")) {
            taskTime = arr[3].trim();
            Task deadline = new Deadline(taskDescription , taskTime);
            if (taskStatus.equals("\u2713")) {
                deadline.markAsDone();
            }
            taskList.add(deadline);
        }else {
            taskTime = arr[3].trim();
            Task event = new Event(taskDescription , taskTime);
            if (taskStatus.equals("\u2713")) {
                event.markAsDone();
            }
            taskList.add(event);
        }
    }

    public String dayExtractor(String[] secondBox) {
        String day = secondBox[1].trim().split("/")[0];
        return day;
    }

    public String timeFormatter(String dateInString , String day) throws ParseException {
        DateFormat parser = new SimpleDateFormat("dd/M/yyyy HHmm");
        DateFormat stFormatter = new SimpleDateFormat("d'st of' MMMM yyyy , ha");
        DateFormat ndFormatter = new SimpleDateFormat("d'nd of' MMMM yyyy , ha");
        DateFormat rdFormatter = new SimpleDateFormat("d'rd of' MMMM yyyy , ha");
        DateFormat thFormatter = new SimpleDateFormat("d'th of' MMMM yyyy , ha");

        String output;

        Date convertedDate = parser.parse(dateInString);
        if (day.equals("1")){
            output = stFormatter.format(convertedDate);
        }else if (day.equals("2")) {
            output = ndFormatter.format(convertedDate);
        }else if (day.equals("3")) {
            output = rdFormatter.format(convertedDate);
        }else{
            output = thFormatter.format(convertedDate);
        }
        return output;
    }

    public String[] firstFilter(String userInput) {
        String[] filter = userInput.split(" ");
        String firstWord = filter[0];
        StringBuilder str = new StringBuilder();
        for (int i = 1 ; i < filter.length ; i++) {
            str.append(filter[i]);
            str.append(WHITESPACE);
        }
        String[] result = {firstWord , str.toString().trim()};

        return result;
    }

    public String[] secondFilter(String newString , String firstWord) {
        String[] filter;
        if (firstWord.equals("deadline")) {
            filter = newString.split(" /by ");
        }else{
            filter = newString.split(" /at ");
        }
        return filter;
    }
}
