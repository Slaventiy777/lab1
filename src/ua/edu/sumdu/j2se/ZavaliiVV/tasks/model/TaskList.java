package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.util.Iterator;

/**
* class TaskList
*
*/

public abstract class TaskList implements Iterable<Task>, Cloneable {

    /**
     *
     * @param task
     */

    public abstract void add(Task task);

    /**
     *
     * @return
     */

    public abstract int size();

    /**
     *
     * @param index
     * @return
     */

    public abstract Task getTask(int index);

    /**
     *
     * @param task
     * @return
     */

    public abstract boolean remove(Task task);

    /**
     *
     * @return
     */

    public abstract Iterator<Task> iterator();

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof TaskList)) return false;

        TaskList that = (TaskList) obj;

        if (size() != that.size()) return false;

        Iterator<Task> iter1 = this.iterator();
        Iterator<Task> iter2 = that.iterator();

        while (iter1.hasNext() & iter2.hasNext()) {
            Task task1 = iter1.next();
            Task task2 = iter2.next();
            if (task1 != null ? !task1.equals(task2) : task2 != null)
                return false;
        }

        return true;

    }

    @Override
    public int hashCode() {

        int result = 1;

        Iterator<Task> iter = iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            result = 13 * result + (task == null ? 0 : task.hashCode());
        }

        result = 13 * result + size();

        return result;

    }

}