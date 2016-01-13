package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class WindowListTasks implements WindowStrategy {

    @Override
    public void openWindow(TaskList tasks) {

        System.out.println("");
        System.out.println("***List tasks***");
        System.out.println("");

        Iterator<Task> iter = tasks.iterator();

        int i = 0;
        while(iter.hasNext()) {

            i++;

            Task task = iter.next();

            boolean lastIndex = !iter.hasNext();
            System.out.println(i + ". " + TaskIO.taskToString(task, lastIndex));

        }

        System.out.println();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

    }
}
