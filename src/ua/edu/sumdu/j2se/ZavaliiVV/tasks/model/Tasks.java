package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.util.*;

public class Tasks {

    /**
     * method incoming
     * @param tasks
     * @param start
     * @param end
     * @return incoming
     * @throws IllegalArgumentException
     */

    public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) {

        if (start == null) throw new IllegalArgumentException("from = null");
        if (end == null) throw new IllegalArgumentException("to = null");
        if (end.before(start))
            throw new IllegalArgumentException("end < start");

        ArrayTaskList nTask = new ArrayTaskList();

        for (Task task : tasks) {
            Date timeTask = task.nextTimeAfter(start);
            if (timeTask != null && !timeTask.after(end)) {
                nTask.add(task);
            }
        }

        return nTask;

    }

    /**
     * method calendar
     * @param tasks
     * @param start
     * @param end
     * @return calendar
     * @throws IllegalArgumentException
     */

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {

        if (start == null) throw new IllegalArgumentException("from = null");
        if (end == null) throw new IllegalArgumentException("to = null");
        if (end.before(start))
            throw new IllegalArgumentException("end < start");

        SortedMap<Date, Set<Task>> calendar = new TreeMap<Date, Set<Task>>();

        Iterable<Task> iterTasks = incoming(tasks, start, end);

        for (Task task : iterTasks) {

            Date timeTask = task.nextTimeAfter(start);
            while (timeTask != null && !timeTask.after(end)) {
                if (calendar.containsKey(timeTask)) {
                    calendar.get(timeTask).add(task);
                } else {
                    Set<Task> setTasks = new HashSet<Task>();
                    setTasks.add(task);
                    calendar.put(timeTask, setTasks);
                }
                timeTask = task.nextTimeAfter(timeTask);
            }

        }

        return calendar;

    }

}
