package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.io.Serializable;
import java.util.Date;

/**
*Class Task
*
*/

public class Task implements Cloneable, Serializable {

    private static final long serialVersionUID = 8787472581122797179L;

    private String title;
    private boolean active;
    private Date time;
    private Date startTime;
    private Date endTime;
    private int interval;
    
    /**
    *CONSTRUCTORS
    */
    
    /**
    * constructors Task
    * @param title - name task
    * @param time - time task
    */
    
    public Task(String title, Date time) {

        if (time == null) throw new IllegalArgumentException("time = null");

        this.title      = title;
        this.time       = new Date(time.getTime());
        this.startTime  = null;
        this.endTime    = null;
        this.interval   = 0;
        this.active     = false;
    }
    
    /**
    * constructors Task
    * @param title - name task
    * @param startTime - start time task
    * @param endTime - end time task
    * @param interval - interval repeat task
    */
    
    public Task(String title, Date startTime, Date endTime, int interval) {

        if (interval <= 0) throw new IllegalArgumentException("interval <= 0");
        if (startTime == null) throw new IllegalArgumentException("startTime = null");
        if (endTime == null) throw new IllegalArgumentException("endTime = null");
        if (endTime.before(startTime))
            throw new IllegalArgumentException("endTime < startTime");

        this.title      = title;
        this.interval   = interval;
        this.startTime  = new Date(startTime.getTime());
        this.endTime    = new Date(endTime.getTime());
        this.time       = null;
        this.active     = false;

    }
    
    /**
    * constructors Task
    */
    
    public Task() {
        this("Untitled", null);
    }
    
    
    /**
    * method setTitle
    * @param title - name task
    * @throws
    */
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
    * method getTitle
    * @return title - return title task
    */
    
    public String getTitle() {
        return title;
    }
    
    /**
    * method setActive
    * @param active - boolean active task
    */
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
    * method isActive
    * @return active - activ or passive task
    */
    
    public boolean isActive() {
        return active;
    }
    
    /**
    * method setTime
    * @param time - time task
    */
    
    public void setTime(Date time) {
        if (time == null) throw new IllegalArgumentException("time = null");

        this.time = new Date(time.getTime());
        this.startTime = null;
        this.endTime = null;

        if (isRepeated()) {
           setInterval(0); 
        }
    }
    
    /**
    * method getTime
    * @return time - time task
    */
    
    public Date getTime() {
        if (isRepeated()) 
            return new Date(startTime.getTime());
        else
            return new Date(time.getTime());
    }
    
    
    /**
    * method setInterval
    * @param interval - interval repeat task
    */
    
    public void setInterval(int interval) {
        if (interval < 0) throw new IllegalArgumentException("interval < 0");
        this.interval = interval;
    }
    
    /**
    * method getInterval
    * @return interval - interval task
    */
    
    public int getInterval() {
        return interval;
    }
    
    
    /**
    * method setStartTime
    * @param startTime - start time task
    */
    
    public void setStartTime(Date startTime) {
        if (startTime == null) throw new IllegalArgumentException("startTime = null");
        this.startTime = new Date(startTime.getTime());
    }
    
    /**
    * method getStartTime
    * @return time - time task
    */
    
    public Date getStartTime() {
        if (isRepeated())
           return new Date(startTime.getTime());
        else
            return new Date(time.getTime());
    }
    
    
    /**
    * method setEndTime
    * @param endTime - end time task
    */
    
    public void setEndTime(Date endTime) {
        if (endTime == null) throw new IllegalArgumentException("endTime = null");
        this.endTime = new Date(endTime.getTime());
    }
    
    /**
    * method getEndTime
    * @return time - time end task
    */
    
    public Date getEndTime() {
        if (isRepeated())
           return new Date(endTime.getTime());
        else
            return new Date(time.getTime());
    }
    
    
    /**
    * method setTime
    * @param startTime - start time task
    * @param endTime - end time task
    * @param interval - interval repeat task
    */
    
    public void setTime(Date startTime, Date endTime, int interval) {
        if (startTime == null) throw new IllegalArgumentException("startTime = null");
        if (endTime == null) throw new IllegalArgumentException("endTime = null");
        if (endTime.before(startTime))
            throw new IllegalArgumentException("endTime < startTime");
        if (interval <= 0) throw new IllegalArgumentException("interval < 0");

        this.startTime  = new Date(startTime.getTime());
        this.endTime    = new Date(endTime.getTime());
        this.interval   = interval;

        this.time       = null;
    }
    
    /**
    * method getRepeatInterval
    * @return interval - interval repeat task
    */
    
//    public int getRepeatInterval() {
//
//        if (isRepeated())
//            return interval;
//        else
//            return 0;
//
//    }
    
    
    /**
    * method isRepeated
    * @return repeated - boolean is repeated task
    */
    
    public boolean isRepeated() {
        boolean isRepeated;
        if (interval > 0)
            isRepeated = true;
        else
            isRepeated = false;
        return isRepeated;
    }
    
    
    /**
    * method nextTimeAfter
    * @param currentDate - current time
    * @return nextTime - time next implementation task
    */
    
    public Date nextTimeAfter(Date currentDate) {
        if (currentDate == null) throw new IllegalArgumentException("currentDate = null");

        Date current = new Date(currentDate.getTime());

        if (isActive()) {
            
            if (isRepeated()) {
                if (current.before(startTime))
                    return new Date(startTime.getTime());

                Date nextTime = new Date(startTime.getTime());
                while (!nextTime.after(current)) {
                   nextTime.setTime(nextTime.getTime() + interval*1000);
                }
                
                if (!nextTime.after(endTime))
                    return new Date(nextTime.getTime());
                else
                    return null;
            } else {
                if (current.before(time))
                    return new Date(time.getTime());
                else
                    return null;
            }
            
        } else
            return null;
            
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Task task = (Task) obj;

        if (active != task.active) return false;
        if (time != null ? !time.equals(task.time) : task.time != null) return false;
        if (startTime != null ? !startTime.equals(task.startTime) : task.startTime != null) return false;
        if (endTime != null ? !endTime.equals(task.endTime) : task.endTime != null) return false;
        if (interval != task.interval) return false;
        return title.equals(task.title);

    }

    @Override
    public int hashCode() {

        int result = title.hashCode();
        result = 13 * result + (active ? 1 : 0);
        result = 13 * result + (time != null ? time.hashCode() : 0);
        result = 13 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 13 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 13 * result + interval;
        return result;

    }

    @Override
    public String toString() {

        return "Task{"
                + "title='" + title + '\''
                + ", active=" + active
                + ", time=" + time
                + ", startTime=" + startTime
                + ", endTime=" + endTime
                + ", interval=" + interval
                + '}';

    }

    @Override
    protected Task clone() throws CloneNotSupportedException {

        Task nTask = (Task) super.clone();
        nTask.title     = title;
        nTask.active    = active;
        nTask.time      = (time != null ? new Date(time.getTime()) : null);
        nTask.startTime = (startTime != null ? new Date(startTime.getTime()) : null);
        nTask.endTime   = (endTime != null ? new Date(endTime.getTime()) : null);
        nTask.interval  = interval;

        return nTask;
    }

}
