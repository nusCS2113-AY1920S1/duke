package myduke;

import myduke.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DukeFileOperation {
    private ArrayList<Task> taskList;
    //DukeTools dukeTools;
    public DukeFileOperation (ArrayList<Task> taskList) {
        this.taskList = taskList;
        //dukeTools = new DukeTools();
    }

    File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");


//    public ArrayList<Task> loadFile() {
//        FileReader fileReader = null;
//        BufferedReader bufferedReader = null;
//        try {
//            fileReader = new FileReader(file);
//            bufferedReader = new BufferedReader(fileReader);
//
//            String inputs = bufferedReader.readLine();
//
//            while (inputs != null) {
//
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (fileReader != null) {
//                    fileReader.close();
//                }
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//
//    }


    public void writeToFile () {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : taskList) {
                String taskType = task.getType();
                String taskStatus = task.getStatusIcon();
                String taskDescription = task.getDescription();
                String taskTime = task.getTime();
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
