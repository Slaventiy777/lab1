package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.*;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.println;

public class WindowEditMode implements WindowStrategy {

    private Map<Integer, OperationView> mapOperationView;
    private Task taskEdit;

    public WindowEditMode(Task taskEdit) {
        this.taskEdit = taskEdit;

        mapOperationView = new HashMap<>();

        if(taskEdit == null) {
            mapOperationView.put(1, OperationView.ExitEditMode);
        } else {
            if(!taskEdit.isRepeated()) {
                mapOperationView.put(1, OperationView.EditTitle);
                mapOperationView.put(2, OperationView.EditTime);
                mapOperationView.put(3, OperationView.EditActive);
                mapOperationView.put(4, OperationView.ExitEditMode);
            } else {
                mapOperationView.put(1, OperationView.EditTitle);
                mapOperationView.put(2, OperationView.EditStartTime);
                mapOperationView.put(3, OperationView.EditEndTime);
                mapOperationView.put(4, OperationView.EditInterval);
                mapOperationView.put(5, OperationView.EditActive);
                mapOperationView.put(6, OperationView.ExitEditMode);
            }
        }
    }

    @Override
    public void openWindow(TaskList tasks) {

        println();

        System.out.println("***Edit mode***");
        println();

        setMenu();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {}

    @Override
    public void setMenu() {

        println();
        if(taskEdit == null) {
            println("Task not found!");
            println("Exit edit mode enter:   1");
        } else {
            if(!taskEdit.isRepeated()) {
                println("Edit title enter:       1");
                println("Edit time enter:        2");
                println("Edit active task enter: 3");
                println("Exit edit mode enter:   4");
            } else {
                println("Edit title enter:       1");
                println("Edit start time enter:  2");
                println("Edit end time enter:    3");
                println("Edit interval enter:    4");
                println("Edit active task enter: 5");
                println("Exit edit mode enter:   6");
            }
        }
        println();

    }

    @Override
    public OperationView enterOperation(int intOperation) {

        return mapOperationView.get(intOperation);

    }

}
