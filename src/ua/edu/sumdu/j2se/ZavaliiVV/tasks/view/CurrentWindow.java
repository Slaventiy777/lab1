package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class CurrentWindow {

    private WindowStrategy windowStrategy;

    public CurrentWindow(WindowStrategy windowStrategy) {
        this.windowStrategy = windowStrategy;
    }

    public CurrentWindow() {}

    public void setWindowStrategy(WindowStrategy windowStrategy) {
        this.windowStrategy = windowStrategy;
    }

    public void openWindowStrategy(TaskList tasks){
        windowStrategy.openWindow(tasks);
    }

    public void openWindowStrategy(SortedMap<Date, Set<Task>> calendar) {
        windowStrategy.openWindow(calendar);
    }

    public OperationView enterOperation(int intOperation) {
        return windowStrategy.enterOperation(intOperation);
    }

}
