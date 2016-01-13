package ua.edu.sumdu.j2se.ZavaliiVV.tasks.controller;

import org.apache.log4j.Logger;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.*;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.notification.NotificationManager;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Zavalii V.V. on 29.12.2015.
 */

public class TaskManagerController {

    private final TaskManagerModel model;
    private TaskManagerView view;
    private OperationView operationView;
    private Task tempTask;
    private static final Logger log = Logger.getLogger(TaskManagerController.class);

    public TaskManagerController(TaskManagerModel model) {
        this.model = model;
        this.operationView = OperationView.ListTasks;
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

    public void startController() throws IOException{

        OperationView tempOperation = operationView;

        try{
            while(true) {

                if (tempOperation == OperationView.ListTasks) {
                    operationView = tempOperation;
                    view.update(model.getAllTasks(), operationView);
                }

                if (tempOperation == OperationView.TaskInformations) {

                    operationView = tempOperation;
                    int index = view.choiceTaskID(model.getAllTasks().size());

                    if (index <= 0) {
                        operationView = OperationView.ListTasks;
                        view.update(model.getAllTasks(), operationView);
                    } else {
                        TaskList tasks = new ArrayTaskList();
                        tasks.add(model.getAllTasks().getTask(index - 1));
                        view.update(tasks, operationView);
                    }

                }

                if (tempOperation == OperationView.Add) {

                    operationView = tempOperation;
                    TaskList tasks = new ArrayTaskList();
                    view.update(tasks, operationView);

                    String title = view.enterTitle();

                    boolean isRepeated = view.enterBoolean("repeated");
                    if (!isRepeated) {
                        Date time = view.enterDate("time");
                        boolean active = view.enterBoolean("active");
                        tempTask = model.createTask(title, time, active);
                    } else {
                        Date startTime = view.enterDate("start time");
                        Date endTime = view.enterDate("end time");
                        int interval = view.enterInterval();
                        boolean active = view.enterBoolean("active");
                        tempTask = model.createTask(title, startTime, endTime, interval, active);
                    }

                     //model.addTask(newTask);

                }

                if (tempOperation == OperationView.Edit) {

                    operationView = tempOperation;
                    int index = view.choiceTaskID(model.getAllTasks().size());

                    if (index <= 0) {
                        operationView = OperationView.ListTasks;
                        view.update(model.getAllTasks(), operationView);
                    } else {
                        TaskList tasks = new ArrayTaskList();
                        tasks.add(model.getAllTasks().getTask(index - 1));
                        view.update(tasks, operationView);

                        tempTask = model.getAllTasks().getTask(index - 1);
                        //view.editTask(model.getAllTasks().getTask(index - 1));

                    }

                }

                if (tempOperation == OperationView.EditMode) {

                    operationView = OperationView.Edit;
                    view.editTask(tempTask);

                    model.writeAllTasksList();

                    TaskList tasks = new ArrayTaskList();
                    tasks.add(tempTask);
                    view.update(tasks, operationView);

                }

                if (tempOperation == OperationView.SaveTask) {

                    operationView = OperationView.ListTasks;

                    if(tempTask == null) {
                        view.update(model.getAllTasks(), operationView);
                    } else {
                        model.addTask(tempTask);
                        model.writeAllTasksList();

                        tempTask = null;
                    }

                }

                if (tempOperation == OperationView.Remove) {

                    operationView = OperationView.ListTasks;
                    int index = view.choiceTaskID(model.getAllTasks().size());

                    if (index <= 0) {
                        view.update(model.getAllTasks(), operationView);
                    } else {
                        model.removeTask(model.getAllTasks().getTask(index - 1));
                    }

                }

                if (tempOperation == OperationView.Update) {
                    operationView = OperationView.ListTasks;
                    view.update(model.getAllTasks(), operationView);
                }

                if (tempOperation == OperationView.Calendar) {
                    operationView = tempOperation;

                    Date startTime = view.enterDate("start time calendar");
                    Date endTime = view.enterDate("end time calendar");

                    view.update(model.calendar(model.getAllTasks(), startTime, endTime), operationView);
                }

                if (tempOperation == OperationView.Exit) {
                    System.exit(0);
                }

                view.setMenu(operationView);
                tempOperation = view.enterOperation(operationView);

            }
        } catch (IOException e) {
            log.error(e.getMessage());

            operationView = OperationView.ListTasks;
            startController();
        }

    }

}
