package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* class ArrayTaskList
*
*/

public class ArrayTaskList extends TaskList {
    
    private Task[] taskArray;
    private int lastIndex;
    
    /**
    * constructors ArrayTaskList
    * @param size - size array
    */
    
    public ArrayTaskList(int size) {
        if (size < 0) throw new IllegalArgumentException("size < 0");

        this.taskArray = new Task[size];
        this.lastIndex = -1;
    }
    
    /**
    * constructors ArrayTaskList
    */
    
    public ArrayTaskList() {
        this(10);
    }
        
    /**
    * method add
    * @param task - object class Task
    */
    
    public void add(Task task) {
        if (task == null) throw new IllegalArgumentException("task == null");

        lastIndex++;

        if (taskArray.length == 0) {
            taskArray = new Task[1];
            taskArray[lastIndex] = task;
        } else {
            if (lastIndex < taskArray.length) {
                taskArray[lastIndex] = task;
            } else {
                Task[] newArrayCopy = new Task[2 * taskArray.length];
                System.arraycopy(taskArray, 0, newArrayCopy, 0,
                        taskArray.length);
                taskArray = newArrayCopy;

                taskArray[lastIndex] = task;
            }
        }
    }

    /**
    * method size
    * @return size - size array
    */
    
    public int size() {
        return lastIndex + 1;
    }

    /**
    * method getTask
    * @param index - index array
    * @return element array of index
    */
    
    public Task getTask(int index) {
        if (index < 0) throw new IllegalArgumentException("index < 0");
        if (index > lastIndex)
            throw new IllegalArgumentException("index > lastIndex");

        return taskArray[index];
    }
    
    /**
    * method remove
    * @param task - object class Task
    * @return boolean fined element array
    */

    public boolean remove(Task task) {
        if (task == null) throw new IllegalArgumentException("task == null");

        int index = -1;

        for (int i = 0; i < size(); i++) {
            if (task.equals(taskArray[i])) {
                index = i;
                break;
            }
        }

        if (index >= 0) {

            if (index == (taskArray.length - 1)) {
                taskArray[index] = null;
                lastIndex--;
            } else {
                System.arraycopy(taskArray, (index + 1), taskArray, index,
                                ((taskArray.length - 1) - index));
                taskArray[taskArray.length - 1] = null;
                lastIndex--;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * method remove
     * @param index - index object class Task
     * @return boolean fined element array
     */

    public boolean remove(int index) {

        if (index >= 0 && index <= size()) {

            if (index == (taskArray.length - 1)) {
                taskArray[index] = null;
                lastIndex--;
            } else {
                System.arraycopy(taskArray, (index + 1), taskArray, index,
                        ((taskArray.length - 1) - index));
                taskArray[taskArray.length - 1] = null;
                lastIndex--;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * method iterator
     *
     * @return iterator
     */

    public Iterator<Task> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Task> {

        int cursorEl = 0;
        int lastEl = -1;

        public boolean hasNext() {
            return cursorEl < size();
        }

        public Task next() {
            if (!hasNext()) throw new NoSuchElementException();

            lastEl = cursorEl;
            cursorEl++;

            return getTask(lastEl);
        }

        public void remove() {
            if (lastEl == -1) throw new IllegalStateException();

            ArrayTaskList.this.remove(lastEl);
            lastEl--;
            cursorEl--;
        }
    }


    @Override
    public String toString() {

        String tempString = "";
        for (int i = 0; i < size(); i++) {
            if (i > 0) tempString += ", ";

            tempString += "{" + taskArray[i].toString() + "}";
        }

        tempString = "ArrayTaskList{"
                    + "taskArray{" + tempString
                    + "}, lastIndex=" + lastIndex
                    + "}";

        return tempString;

    }

    @Override
    protected ArrayTaskList clone() throws CloneNotSupportedException {

        ArrayTaskList nArrayTaskList = (ArrayTaskList) super.clone();
        nArrayTaskList.lastIndex = lastIndex;
        Task[] newArrayCopy = new Task[taskArray.length];
        System.arraycopy(taskArray, 0, newArrayCopy, 0, taskArray.length);

        return nArrayTaskList;

    }

}