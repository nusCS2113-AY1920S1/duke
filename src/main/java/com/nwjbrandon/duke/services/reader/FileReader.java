package com.nwjbrandon.duke.services.reader;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public FileReader() {
    }

    /**
     * Load data.
     * @return data in string
     */
    public static ArrayList<String> loadData(String filePath) throws Exception {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        ArrayList<String> inputList = new ArrayList<String>();
        while (scan.hasNextLine()) {
            String taskDetails = scan.nextLine();
            inputList.add(taskDetails);
        }
        return inputList;
    }

    /**
     * Save data.
     */
    public static void saveData(String filePath, String data) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }
}
