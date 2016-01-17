package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.CurrentWindow;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.IOException;

/**
 * Created by Administratorus on 17.01.2016.
 */
public class UseOperationEditMode extends ControllerUseOperation {

    @Override
    public void performOperation(TaskManagerController controller) throws IOException {

        TaskManagerView view = controller.getView();
        TaskManagerModel model = controller.getModel();

        view.update();

        CurrentWindow currentWindow = view.getCurrentWindow();

        Task task = view.getTempTask();

        TaskList tasks = new ArrayTaskList();
        tasks.add(task);

        while(TaskManagerView.getOperationView() != OperationView.ExitEditMode) {
            currentWindow.openWindowStrategy(tasks);
            view.enterOperation(currentWindow);

            if(!task.isRepeated()) {

                if(TaskManagerView.getOperationView() == OperationView.EditTitle)
                    task.setTitle(view.enterTitle());
                else if(TaskManagerView.getOperationView() == OperationView.EditTime)
                    task.setTime(view.enterDate("time"));
                else if(TaskManagerView.getOperationView() == OperationView.EditActive)
                    task.setActive(view.enterBoolean("active"));
                else if(TaskManagerView.getOperationView() == OperationView.ExitEditMode)
                    break;

            } else {

                if(TaskManagerView.getOperationView() == OperationView.EditTitle)
                    task.setTitle(view.enterTitle());
                else if(TaskManagerView.getOperationView() == OperationView.EditStartTime)
                    task.setTime(view.enterDate("start time"));
                else if(TaskManagerView.getOperationView() == OperationView.EditEndTime)
                    task.setTime(view.enterDate("end time"));
                else if(TaskManagerView.getOperationView() == OperationView.EditInterval)
                    task.setInterval(view.enterInterval());
                else if(TaskManagerView.getOperationView() == OperationView.EditActive)
                    task.setActive(view.enterBoolean("active"));
                else if(TaskManagerView.getOperationView() == OperationView.ExitEditMode)
                    break;

            }

        }

        model.writeAllTasksList();

        TaskManagerView.setOperationView(OperationView.Edit);
        //view.setTempTask(task);

    }

}
