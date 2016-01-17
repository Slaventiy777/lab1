package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.CurrentWindow;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administratorus on 17.01.2016.
 */
public class UseOperationAdd extends ControllerUseOperation {

    @Override
    public void performOperation(TaskManagerController controller) throws IOException {

        TaskManagerView view = controller.getView();
        TaskManagerModel model = controller.getModel();

        view.update();

        CurrentWindow currentWindow = view.getCurrentWindow();

        TaskList tasks = new ArrayTaskList();
        currentWindow.openWindowStrategy(tasks);

        String title = view.enterTitle();

        boolean isRepeated = view.enterBoolean("repeated");
        if (!isRepeated) {
            Date time = view.enterDate("time");
            boolean active = view.enterBoolean("active");

            view.setTempTask(model.createTask(title, time, active));
        } else {
            Date startTime = view.enterDate("start time");
            Date endTime = view.enterDate("end time");
            int interval = view.enterInterval();
            boolean active = view.enterBoolean("active");

            view.setTempTask(model.createTask(title, startTime, endTime, interval, active));
        }

        currentWindow.openWindowStrategy(tasks);

        view.enterOperation(currentWindow);

    }

}
