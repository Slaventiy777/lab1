package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Observer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Zavalii V.V. on 28.12.2015.
 */

public class TaskManagerModel implements Observable {

    private String tasksPathName;
    private List<Observer> observers;
    private TaskList allTasks;

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
            throw new IOException(e);
        }
        return allTasks;
    }

    public void writeAllTasksList() throws IOException{
        TaskIO.writeText(allTasks, new File(tasksPathName));
    }

    public void addTask(Task task) {
        allTasks.add(task);
        notifyObservers();
    }

    public void removeTask(Task task) {
        allTasks.remove(task);
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
            observer.update(allTasks);
        }

    }

}
