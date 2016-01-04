package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class WindowEdit implements WindowStrategy {

    @Override
    public void openWindow(TaskList tasks) {

        System.out.println("***Edit task***/n");

        Iterator<Task> iter = tasks.iterator();

        if (iter.hasNext()) {
            Task task = iter.next();
            System.out.println(TaskIO.taskToString(task, true));
        }

        System.out.println();

//        System.out.println("View task information enter: 1");
//        System.out.println("Add new task enter:          2");
//        System.out.println("Edit task enter:             3");
//        System.out.println("Remove task enter:           4");
//        System.out.println("View calendar enter:         5");
//        System.out.println("Update list tasks enter:     6");
//        System.out.println("Back list tasks enter:       7");
//        System.out.println("Save task enter:             8");
//        System.out.println("Quit enter:                  9");

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

    }
}
