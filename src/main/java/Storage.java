import duke.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private ArrayList<Task> taskList = new ArrayList<>();
    Parser parser = new Parser();

    public ArrayList<Task> load() {
        File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String input = bufferedReader.readLine();

            while (input != null) {
                parser.loadParse(taskList , input);
                input = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            return taskList;

        }
    }

    public void save () {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");
            fileWriter = new FileWriter(file , false);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task t : taskList) {
                String taskType = t.getType();
                String taskStatus = t.getStatusIcon();
                String taskDescription = t.getDescription();
                String taskTime = t.getTime();

                if (taskType.equals("T")) {
                    bufferedWriter.write(taskType + " | " + taskStatus + " | " + taskDescription);
                    bufferedWriter.newLine();

                }else{
                    bufferedWriter.write(taskType + " | " + taskStatus + " | " + taskDescription + " | " + taskTime);
                    bufferedWriter.newLine();
                }


                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
