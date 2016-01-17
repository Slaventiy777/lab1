package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;

import java.io.IOException;

import static java.lang.System.exit;

/**
 * Created by Administratorus on 17.01.2016.
 */
public abstract class ControllerUseOperation {

    public static ControllerUseOperation newUseOperation(OperationView operationView) {

        switch (operationView) {
            case ListTasks:
                return new UseOperationListTasks();
            case TaskInformations:
                return new UseOperationTaskInformations();
            case Calendar:
                return new UseOperationCalendar();
            case Add:
                return new UseOperationAdd();
            case Edit:
                return new UseOperationEdit();
            case EditMode:
                return new UseOperationEditMode();
            case SaveTask:
                return new UseOperationSaveTask();
            case Remove:
                return new UseOperationRemove();
            case Update:
                return new UseOperationUpdate();
            case Exit:
                exit(0);
            default:
                throw new IllegalStateException();
        }

    }

    abstract public void performOperation(TaskManagerController controller) throws IOException;

}
