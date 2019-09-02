package com.nwjbrandon.duke;

import com.nwjbrandon.duke.services.TaskManager;
import com.nwjbrandon.duke.services.interfaces.Ui;

public class Duke {

    public Duke() {
    }

    /**
     * Main application starts running here.
     * @param args - input of array of strings from console
     */
    public static void main(String[] args) throws Exception {
        Ui.greetingMessage();
        TaskManager taskManager = new TaskManager();
        String filePath = "./data/duke.txt";
        taskManager.loadData(filePath);
        while (taskManager.run()) {
            System.out.println();
        }
        taskManager.saveData(filePath);
        Ui.farewellMessage();
    }


}
