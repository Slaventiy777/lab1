package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import org.apache.log4j.Logger;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.*;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.notification.NotificationManager;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.IOException;

/**
 * Created by Zavalii V.V. on 29.12.2015.
 */

public class TaskManagerController {

    private final TaskManagerModel model;
    private TaskManagerView view;
    private ControllerUseOperation controllerUseOperation;

    private static final Logger log = Logger.getLogger(TaskManagerController.class);

    public TaskManagerController(TaskManagerModel model) {
        this.model = model;
    }

    public void createView() {
        view = new TaskManagerView(model);
    }

    public void createNotification() {

        NotificationManager notificationManager = new NotificationManager(model);
        Thread notificationThread = new Thread(notificationManager);
        notificationThread.setDaemon(true);
        notificationThread.start();

    }


    public TaskManagerModel getModel() {
        return model;
    }

    public TaskManagerView getView() {
        return view;
    }


    public void startController() throws IOException{

        try{
            while(true) {
                controllerUseOperation = ControllerUseOperation.newUseOperation(TaskManagerView.getOperationView());
                controllerUseOperation.performOperation(this);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);

            TaskManagerView.setOperationView(OperationView.ListTasks);
            startController();
        }

    }

}
