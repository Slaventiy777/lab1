package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.*;

import java.util.*;

public class WindowEdit implements WindowStrategy {

    private Map<Integer, OperationView> mapOperationView;

    public WindowEdit() {
        mapOperationView = new HashMap<>();

        mapOperationView.put(1, OperationView.EditMode);
        mapOperationView.put(2, TaskManagerView.getPreviousOperationView());
        mapOperationView.put(3, OperationView.ListTasks);
    }

    @Override
    public void openWindow(TaskList tasks) {

        println();

        println("***Edit task***");
        println("");

        Iterator<Task> iter = tasks.iterator();

        if (iter.hasNext()) {

            Task task = iter.next();

            println("Title        - '" + task.getTitle() + "';");
            println("Is active    - " + task.isActive() + ";");

            if (task.isRepeated()) {
                println("Start time   - " + task.getStartTime() + ";");
                println("End time     - " + task.getEndTime() + ";");
                println("Interval     - " + task.getInterval() + ";");
            } else {
                println("Time         - " + task.getTime() + ";");
            }

        }

        println();

        setMenu();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {}

    @Override
    public void setMenu() {

        println();
        println("Start edit enter:                   1");
        println("Back to the previous window enter:  2");
        println("View list tasks enter:              3");
        println();

    }

    @Override
    public OperationView enterOperation(int intOperation) {

        return mapOperationView.get(intOperation);

    }

}
