package ua.edu.sumdu.j2se.ZavaliiVV.tasks.notification;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.TaskManagerView;

import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Zavalii V.V. on 29.12.2015.
 */

public class NotificationManager implements Runnable {

    private final int numberMillisecondsSleep = 5000;
    private TaskManagerModel model;

    public NotificationManager(TaskManagerModel model) {
        this.model = model;
    }

    @Override
    public void run() {

        try {
            Notification notification = new ScreenNotification();

            while(Thread.currentThread().isInterrupted()) {

                sleep(numberMillisecondsSleep);

                Date currentDate = new Date();
                Date beforeDate = new Date(currentDate.getTime() - numberMillisecondsSleep);

                List<Task> taskList = model.getCurrentCalendar(beforeDate, currentDate);

                if (taskList.size() != 0)
                    notification.notifyUser(taskList);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
