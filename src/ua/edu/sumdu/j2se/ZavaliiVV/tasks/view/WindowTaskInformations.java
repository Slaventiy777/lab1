package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class WindowTaskInformations implements WindowStrategy{

    @Override
    public void openWindow(TaskList tasks) {

        System.out.println();
        System.out.println("***Task informations***");

        Iterator<Task> iter = tasks.iterator();

        if(iter.hasNext()) {

            Task task = iter.next();

            System.out.println("Title        - '" + task.getTitle() + "';");
            System.out.println("Is active    - " + task.isActive() + ";");

            if(task.isRepeated()) {
                System.out.println("Start time   - " + task.getStartTime() + ";");
                System.out.println("End time     - " + task.getEndTime() + ";");
                System.out.println("Interval     - " + task.getInterval() + ";");
            } else {
                System.out.println("Time         - " + task.getTime() + ";");
            }

        }

        System.out.println();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

    }
}
