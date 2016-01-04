package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public class UpdateWindow {

    private WindowStrategy windowStrategy;

    public UpdateWindow(WindowStrategy windowStrategy) {
        this.windowStrategy = windowStrategy;
    }

    public UpdateWindow() {
    }

    public void setWindowStrategy(WindowStrategy windowStrategy) {
        this.windowStrategy = windowStrategy;
    }

    public void openWindowStrategy(TaskList tasks) {
        windowStrategy.openWindow(tasks);
    }

    public void openWindowStrategy(SortedMap<Date, Set<Task>> calendar) {
        windowStrategy.openWindow(calendar);
    }

}
