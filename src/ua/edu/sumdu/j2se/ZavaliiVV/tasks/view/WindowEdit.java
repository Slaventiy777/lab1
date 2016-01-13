package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;


import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class WindowEdit implements WindowStrategy {



    @Override
    public void openWindow(TaskList tasks) {

        System.out.println();

        if(tasks.size() == 0) {
            System.out.println("***Add task***");
            System.out.println("");
        } else {

            System.out.println("***Edit task***");
            System.out.println("");

            Iterator<Task> iter = tasks.iterator();

            if (iter.hasNext()) {

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

        }

        System.out.println();

    }

    @Override
    public void openWindow(SortedMap<Date, Set<Task>> calendar) {

    }
}
