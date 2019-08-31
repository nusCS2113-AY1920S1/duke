package myduke;

import myduke.task.Deadline;
import myduke.task.Event;
import myduke.task.Task;
import myduke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DukeFileOperation {
    public DukeFileOperation() {}
    public DukeFileOperation(ArrayList<Task> taskLst) {
        this.taskLoader = taskLst;
    }
    ArrayList<Task> taskLoader = new ArrayList<>();
    File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");

    public long getFileLength () {
        return this.file.length();
    }


    public ArrayList<Task> loadFile() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String input = bufferedReader.readLine();

            while (input != null) {
                String taskType = input.split(" \\| ")[0].trim();
                String taskStatus = input.split(" \\| ")[1].trim();
                String taskDescription = input.split("\\|")[2].trim();
//                System.out.println(taskDescription);
//                System.out.println(taskType);
//                System.out.println(taskStatus);
//
//                if (taskType.equals("T")) {
//                    Task todo = new ToDo(taskDescription);
//                        if (taskStatus.equals('\u2713')) {
//                            todo.markAsDone();
//                        }
//                        taskLoader.add(todo);
//                }else if (taskType.equals("D")) {
//                    String taskTime = input.split(" \\| ")[3];
//                    Deadline deadline = new Deadline(taskDescription , taskTime);
//                    if (taskStatus.equals('\u2713')) {
//                        deadline.markAsDone();
//                    }
//                    taskLoader.add(deadline);
//                }else {
//                    String taskTime = input.split(" \\| ")[3];
//                    Event event = new Event(taskDescription , taskTime);
//                    if (taskStatus.equals('\u2713')) {
//                        event.markAsDone();
//                    }
//                    taskLoader.add(event);
//                }

                switch (taskType) {
                    case "T" :
                        Task todo = new ToDo(taskDescription);
                        if (taskStatus.equals('\u2713')) {
                            todo.markAsDone();
                        }
                        taskLoader.add(todo);
                        break;
                    case "D" :
                        String taskTime = input.split(" \\| ")[3];
                        Task deadline = new Deadline(taskDescription , taskTime);
                        if (taskStatus.equals('\u2713')) {
                            deadline.markAsDone();
                        }
                        taskLoader.add(deadline);
                        break;
                    case "E" :
                        taskTime = input.split(" \\| ")[3];
                        Task event = new Event(taskDescription , taskTime);
                        if (taskStatus.equals('\u2713')) {
                            event.markAsDone();
                        }
                        taskLoader.add(event);
                        break;
                }
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
            return taskLoader;

        }
    }


    public void writeToFile (ArrayList<Task> taskList) {
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
