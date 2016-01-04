package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;

public interface WindowStrategy {

    void openWindow(TaskList tasks);
    void openWindow(SortedMap<Date, Set<Task>> calendar);

}
