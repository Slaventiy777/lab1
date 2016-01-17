package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.*;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.*;

public class WindowCalendar implements WindowStrategy {

    private Map<Integer, OperationView> mapOperationView;

    public WindowCalendar() {
        mapOperationView = new HashMap<>();

        mapOperationView.put(1, OperationView.ListTasks);
        mapOperationView.put(2, OperationView.Add);
    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

        println("");
        println("***Calendar***");
        println("");

        for(Map.Entry<Date, Set<Task>> calEntry : calendar.entrySet()){

            println(calEntry.getKey() + ":\n");

            Iterator<Task> iter = calEntry.getValue().iterator();

            int i = 0;
            while(iter.hasNext()) {
                i++;

                Task task = iter.next();
                boolean lastIndex = !iter.hasNext();

                println(i + ". " + TaskIO.taskToString(task, lastIndex));
            }

            println("");

        }

        setMenu();

    }

    @Override
    public void openWindow(TaskList tasks) {

    }

    @Override
    public void setMenu() {

        println();
        println("Back list tasks enter:  1");
        println("Add new task enter:     2");
        println();

    }

    @Override
    public OperationView enterOperation(int intOperation) {

        return mapOperationView.get(intOperation);

    }

}
