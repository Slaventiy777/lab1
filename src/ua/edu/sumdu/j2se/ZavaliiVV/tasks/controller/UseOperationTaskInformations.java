package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.CurrentWindow;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.IOException;

/**
 * Created by Administratorus on 17.01.2016.
 */
public class UseOperationTaskInformations extends ControllerUseOperation {

    @Override
    public void performOperation(TaskManagerController controller) throws IOException {

        TaskManagerView view = controller.getView();
        TaskManagerModel model = controller.getModel();

        view.update();

        CurrentWindow currentWindow = view.getCurrentWindow();

        TaskList tasks = new ArrayTaskList();
        if(TaskManagerView.getPreviousOperationView() == OperationView.ListTasks) {

            int index = view.choiceTaskID(model.getAllTasks().size());
            if (index <= 0) {
                TaskManagerView.setOperationView(OperationView.ListTasks);
                return;
            }

            view.setTempTask(model.getAllTasks().getTask(index - 1));

        }

        tasks.add(view.getTempTask());

        currentWindow.openWindowStrategy(tasks);
        TaskManagerView.setPreviousOperationView(TaskManagerView.getOperationView());

        //view.setTempTask(model.getAllTasks().getTask(index - 1));

        view.enterOperation(currentWindow);

    }

}
