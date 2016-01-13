package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Observer;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.OperationView;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Zavalii V.V. on 28.12.2015.
 */

public class TaskManagerModel implements Observable {

    private String tasksPathName;
    private List<Observer> observers;
    private TaskList allTasks;
    private static final Logger log = Logger.getLogger(TaskManagerModel.class);

    public TaskManagerModel(String tasksPathName) {

        this.tasksPathName = tasksPathName;

        allTasks = new LinkedTaskList();
        observers = new ArrayList<Observer>();

    }

    public void initialize() throws IOException, ParseException {
        readAllTasksList();
    }

    public TaskList readAllTasksList() throws IOException, ParseException {
        try {
            TaskIO.readText(allTasks, new File(tasksPathName));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return allTasks;
    }

    public void writeAllTasksList() throws IOException{
        TaskIO.writeText(allTasks, new File(tasksPathName));
    }

    public void addTask(Task task) throws IOException {
        allTasks.add(task);
        notifyObservers();
    }

    public void removeTask(Task task) throws IOException {
        allTasks.remove(task);
        writeAllTasksList();
        notifyObservers();
    }

    public List<Task> getCurrentCalendar(Date before, Date current) {

        Iterable<Task> iterTasks = Tasks.incoming(allTasks, before, current);

        List<Task> taskSet = new ArrayList<Task>();
        for (Task task : iterTasks) {
            taskSet.add(task);
        }

        return taskSet;

    }

    public SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        return Tasks.calendar(tasks, start, end);
    }

    public TaskList getAllTasks() {

        return allTasks;

    }

    public Task createTask(String title, Date time, boolean active) {
        Task task = new Task(title, time);
        task.setActive(active);

        return task;
    }

    public Task createTask(String title, Date startTime, Date endTime, int interval, boolean active) {
        Task task = new Task(title, startTime, endTime, interval);
        task.setActive(active);

        return task;
    }

    @Override
    public void registerObserver(Observer o) {

        observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers)
        {
            observer.update(allTasks, OperationView.ListTasks);
        }

    }

}
