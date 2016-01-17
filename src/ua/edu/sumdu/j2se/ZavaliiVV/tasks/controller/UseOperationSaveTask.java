package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.IOException;

/**
 * Created by Administratorus on 17.01.2016.
 */
public class UseOperationSaveTask extends ControllerUseOperation {

    @Override
    public void performOperation(TaskManagerController controller) throws IOException {

        TaskManagerView view = controller.getView();
        TaskManagerModel model = controller.getModel();

        TaskManagerView.setOperationView(OperationView.ListTasks);

        if(view.getTempTask() != null) {
            model.addTask(view.getTempTask());
            model.writeAllTasksList();

            view.setTempTask(null);
        }

    }

}
