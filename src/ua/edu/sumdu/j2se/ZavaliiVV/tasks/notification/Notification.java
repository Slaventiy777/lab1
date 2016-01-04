package ua.edu.sumdu.j2se.ZavaliiVV.tasks.notification;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;

import java.util.List;

/**
 * Created by Zavalii V.V. on 29.12.2015.
 */

public interface Notification {

    void notifyUser(List<Task> tasks);

}
