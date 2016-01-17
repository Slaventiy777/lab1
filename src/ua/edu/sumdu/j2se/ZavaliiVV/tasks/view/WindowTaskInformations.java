package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.*;

import java.util.*;

public class WindowTaskInformations implements WindowStrategy{

    private Map<Integer, OperationView> mapOperationView;

    public WindowTaskInformations() {
        mapOperationView = new HashMap<>();

        mapOperationView.put(1, OperationView.ListTasks);
        mapOperationView.put(2, OperationView.Edit);
    }

    @Override
    public void openWindow(TaskList tasks) {

        println();
        println("***Task informations***");

        Iterator<Task> iter = tasks.iterator();

        if(iter.hasNext()) {

            Task task = iter.next();

            println("Title        - '" + task.getTitle() + "';");
            println("Is active    - " + task.isActive() + ";");

            if(task.isRepeated()) {
                println("Start time   - " + task.getStartTime() + ";");
                println("End time     - " + task.getEndTime() + ";");
                println("Interval     - " + task.getInterval() + ";");
            } else {
                println("Time         - " + task.getTime() + ";");
            }

        }

        System.out.println();

        setMenu();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {}

    @Override
    public void setMenu() {

        println();
        println("Back list tasks enter: 1");
        println("Edit task enter:       2");
        println();

    }

    @Override
    public OperationView enterOperation(int intOperation) {

        return mapOperationView.get(intOperation);

    }

}
