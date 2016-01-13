package ua.edu.sumdu.j2se.ZavaliiVV.tasks.program;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller.TaskManagerController;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Zavalii V.V. on 29.12.2015.
 */

public class Program {

    public static void main(String[] args) throws IOException, ParseException {

        String tasksPathName = "ListTasksFile.txt";

        File file = new File(tasksPathName);
        if (!file.exists() && !file.isFile()) {
            try(FileWriter writer = new FileWriter(file))
            {
                writer.write("");
            }
            catch(IOException ex){
                throw ex;
            }
        }

        TaskManagerModel model = new TaskManagerModel(tasksPathName);

        model.initialize();

        TaskManagerController controller = new TaskManagerController(model);
        controller.createView();
        controller.createNotification();
        controller.startController();

    }

}
