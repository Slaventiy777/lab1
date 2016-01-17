package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.CurrentWindow;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administratorus on 17.01.2016.
 */
public class UseOperationCalendar extends ControllerUseOperation {

    @Override
    public void performOperation(TaskManagerController controller) throws IOException {

        TaskManagerView view = controller.getView();
        TaskManagerModel model = controller.getModel();

        view.update();

        CurrentWindow currentWindow = view.getCurrentWindow();

        Date startTime = view.enterDate("start time calendar");
        Date endTime = view.enterDate("end time calendar");
        currentWindow.openWindowStrategy(model.calendar(model.getAllTasks(), startTime, endTime));

        TaskManagerView.setPreviousOperationView(TaskManagerView.getOperationView());

        view.enterOperation(currentWindow);

    }

}
