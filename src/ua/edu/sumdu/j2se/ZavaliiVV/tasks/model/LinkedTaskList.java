package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* Class LinkedTaskList
*
*/

public class LinkedTaskList extends TaskList {
      
    private ListEl firstListEl;
    private ListEl lastListEl;
    private int size;
    
    private class ListEl {
        
        private Task taskLink;
        private ListEl previousListEl;
        private ListEl nextListEl;

        ListEl(Task aTaskLink) {
            this.taskLink = aTaskLink;
        }
        
        public void setPreviousListEl(ListEl previousListEl) {
            this.previousListEl = previousListEl;
        }
        
        public ListEl getPreviousListEl() {
            return previousListEl;
        }
        
        public void setNextListEl(ListEl nextListEl) {
            this.nextListEl = nextListEl;
        }
        
        public ListEl getNextListEl() {
            return nextListEl;
        }

        public Task getTaskLink() {
            return taskLink;
        }

    }

    /**
    * Method add the task in linked task list,
    * set links previous / next element last list element,
    * set first and last list element
    *
    * @param task - object class Task
    */
    
    public void add(Task task) {
        if (task == null) throw new IllegalArgumentException("task == null");

        ListEl listEl = new ListEl(task);

        if (firstListEl == null) {
            this.firstListEl = listEl;
            this.lastListEl = listEl;
        } else {
            this.lastListEl.setNextListEl(listEl);
            listEl.setPreviousListEl(this.lastListEl);
            this.lastListEl = listEl;
        }

        size++;
    }

    /**
    * Returns size linked task list
    *
    * @return size linked task list
    */
    
    public int size() {
        return size;
    }

    /**
     * Returns last index linked task list
     *
     * @return last index linked task list
     */

    public int lastIndex() {
        return size - 1;
    }
    
    /**
    * Returns the task getting on the index
    *
    * @param index in list at which get the task
    *
    * @return element linked task list of index
    */
    
    public Task getTask(int index) {
        if (index < 0) throw new IllegalArgumentException("index < 0");
        if (index > lastIndex())
            throw new IllegalArgumentException("index > lastIndex");

        int tempIndex = -1;

        ListEl currentListEl = firstListEl;
        while (currentListEl != null) {
            tempIndex++;

            if (tempIndex == index) break;

            currentListEl = currentListEl.getNextListEl();
        }

        return (currentListEl == null) ? null : currentListEl.getTaskLink();
    }

    /**
    * Method remove list element in linked task list that contains the task
    *
    * @param task that is searched
    *
    * @return boolean remote task
    */
    
    public boolean remove(Task task) {
        if (task == null) throw new IllegalArgumentException("task == null");

        if (size() == 0) return false;
        
        boolean taskFound = false;
        ListEl currentListEl = firstListEl;
        while (currentListEl != null) {
            if (task.equals(currentListEl.getTaskLink())) {
                taskFound = true;
                break;
            }
            
            currentListEl = currentListEl.getNextListEl();
        }
        
        if (taskFound) {
            
            if (size() == 1) {
                firstListEl = null;
                lastListEl = null;
                size--;
                
                return true;
            } else if (size() == 2) {
                firstListEl = currentListEl.getNextListEl();
                lastListEl = currentListEl.getNextListEl();
                
                firstListEl.setPreviousListEl(null);
                firstListEl.setNextListEl(null);

                size--;
                
                return true;
            } else {
                                
                if (currentListEl.equals(firstListEl)) {
                    firstListEl = currentListEl.getNextListEl();
                    firstListEl.setPreviousListEl(null);
                } else if (currentListEl.equals(lastListEl)) {
                    lastListEl = currentListEl.getPreviousListEl();
                    lastListEl.setNextListEl(null);
                } else {
                    ListEl currLPrevL = currentListEl.getPreviousListEl();
                    ListEl currLNextL = currentListEl.getNextListEl();

                    currLPrevL.setNextListEl(currLNextL);
                    currLNextL.setPreviousListEl(currLPrevL);
                }

                size--;
                
                return true;
            }
                        
        } else {
            return false;
        }
        
    }

    /**
     * Method remove list element in linked task list
     *
     * @param currentListEl list element to be removed
     *
     * @return boolean remote task
     */

    public boolean remove(ListEl currentListEl) {

        if (size() == 0) return false;

        if (currentListEl != null) {

            if (size() == 1) {
                firstListEl = null;
                lastListEl = null;
                size--;

                return true;
            } else if (size() == 2) {
                firstListEl = currentListEl.getNextListEl();
                lastListEl = currentListEl.getNextListEl();

                firstListEl.setPreviousListEl(null);
                firstListEl.setNextListEl(null);

                size--;

                return true;
            } else {

                if (currentListEl.equals(firstListEl)) {
                    firstListEl = currentListEl.getNextListEl();
                    firstListEl.setPreviousListEl(null);
                } else if (currentListEl.equals(lastListEl)) {
                    lastListEl = currentListEl.getPreviousListEl();
                    lastListEl.setNextListEl(null);
                } else {
                    ListEl currLPrevL = currentListEl.getPreviousListEl();
                    ListEl currLNextL = currentListEl.getNextListEl();

                    currLPrevL.setNextListEl(currLNextL);
                    currLNextL.setPreviousListEl(currLPrevL);
                }

                size--;

                return true;
            }

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
        private ListEl cursorEl = firstListEl;
        private ListEl lastEl = null;

        public boolean hasNext() {
            return cursorEl != null;
        }

        public Task next() {
            if (!hasNext()) throw new NoSuchElementException();

            lastEl = cursorEl;
            cursorEl = cursorEl.getNextListEl();

            return lastEl.getTaskLink();
        }

        public void remove() {
            if (lastEl == null) throw new IllegalStateException();

            LinkedTaskList.this.remove(lastEl);

            if (cursorEl != null) lastEl = cursorEl.getPreviousListEl();
        }
    }

    @Override
    public String toString() {

        String tempString = "";

        int i = 0;
        for (Task task : this) {
            if (i != 0) tempString += ", ";
            tempString += "{" + task.toString() + "}";
            i++;
        }

        tempString = "LinkedTaskList{" + tempString + "}";

        return tempString;

    }

    @Override
    protected LinkedTaskList clone() throws CloneNotSupportedException {

        LinkedTaskList nLinkedTaskList = (LinkedTaskList) super.clone();
        nLinkedTaskList.lastListEl = null;
        nLinkedTaskList.firstListEl = null;
        nLinkedTaskList.size = 0;

        Iterator<Task> iter = iterator();
        while (iter.hasNext()) {
            nLinkedTaskList.add(iter.next());
        }

        return nLinkedTaskList;

    }

}