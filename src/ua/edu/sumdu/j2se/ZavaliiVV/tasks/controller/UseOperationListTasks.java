package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.CurrentWindow;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.WindowListTasks;

import java.io.IOException;

/**
 * Created by Administratorus on 17.01.2016.
 */
public class UseOperationListTasks extends ControllerUseOperation {

    @Override
    public void performOperation(TaskManagerController controller) throws IOException {

        TaskManagerView view = controller.getView();
        TaskManagerModel model = controller.getModel();

        view.update();

        CurrentWindow currentWindow = view.getCurrentWindow();
        currentWindow.openWindowStrategy(model.getAllTasks());

        TaskManagerView.setPreviousOperationView(TaskManagerView.getOperationView());

        view.enterOperation(currentWindow);

    }

}
