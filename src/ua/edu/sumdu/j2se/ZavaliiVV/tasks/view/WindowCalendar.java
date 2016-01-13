package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.*;

public class WindowCalendar implements WindowStrategy {

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

        System.out.println("");
        System.out.println("***Calendar***");
        System.out.println("");

        for(Map.Entry<Date, Set<Task>> calEntry : calendar.entrySet()){

            System.out.println(calEntry.getKey() + ":\n");

            Iterator<Task> iter = calEntry.getValue().iterator();

            int i = 0;
            while(iter.hasNext()) {

                i++;
                Task task = iter.next();

                boolean lastIndex = !iter.hasNext();
                System.out.println(i + ". " + TaskIO.taskToString(task, lastIndex));

            }

            System.out.println("");

        }

    }

    @Override
    public void openWindow(TaskList tasks) {

    }

}
