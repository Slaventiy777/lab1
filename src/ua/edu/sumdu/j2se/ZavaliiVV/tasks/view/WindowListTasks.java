package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.*;

import java.util.*;

public class WindowListTasks implements WindowStrategy {

    private Map<Integer, OperationView> mapOperationView;

    public WindowListTasks() {
        mapOperationView = new HashMap<>();

        mapOperationView.put(1, OperationView.TaskInformations);
        mapOperationView.put(2, OperationView.Add);
        mapOperationView.put(3, OperationView.Edit);
        mapOperationView.put(4, OperationView.Remove);
        mapOperationView.put(5, OperationView.Calendar);
        mapOperationView.put(6, OperationView.Update);
        mapOperationView.put(7, OperationView.Exit);
    }

    @Override
    public void openWindow(TaskList tasks) {

        println("");
        println("***List tasks***");
        println("");

        Iterator<Task> iter = tasks.iterator();

        int i = 0;
        while(iter.hasNext()) {
            i++;

            Task task = iter.next();
            boolean lastIndex = !iter.hasNext();

            println(i + ". " + TaskIO.taskToString(task, lastIndex));
        }

        println();

        setMenu();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {}

    @Override
    public void setMenu() {

        println();
        println("View task information enter: 1");
        println("Add new task enter:          2");
        println("Edit task enter:             3");
        println("Remove task enter:           4");
        println("View calendar enter:         5");
        println("Update list tasks enter:     6");
        println("Quit enter:                  7");
        println();

    }

    @Override
    public OperationView enterOperation(int intOperation) {

        return mapOperationView.get(intOperation);

    }

}
