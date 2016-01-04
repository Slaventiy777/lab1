package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.notification.NotificationManager;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

/**
 * Created by Zavalii V.V. on 29.12.2015.
 */

public class TaskManagerController {

    private TaskManagerModel model;
    private TaskManagerView view;

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

}
