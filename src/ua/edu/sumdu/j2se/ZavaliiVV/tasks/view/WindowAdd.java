package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.*;

import java.util.*;

public class WindowAdd implements WindowStrategy {

    private Map<Integer, OperationView> mapOperationView;

    public WindowAdd() {
        mapOperationView = new HashMap<>();

        mapOperationView.put(1, OperationView.SaveTask);
        mapOperationView.put(2, TaskManagerView.getPreviousOperationView());
        mapOperationView.put(3, OperationView.ListTasks);
    }
    @Override
    public void openWindow(TaskList tasks) {

        println();
        println("***Add task***");
        println("");

        setMenu();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

    }

    @Override
    public void setMenu() {

        println();
        println("Save task enter:                    1");
        println("Back to the previous window enter:  2");
        println("View list tasks enter:              3");
        println();

    }

    @Override
    public OperationView enterOperation(int intOperation) {

        return mapOperationView.get(intOperation);

    }

}
