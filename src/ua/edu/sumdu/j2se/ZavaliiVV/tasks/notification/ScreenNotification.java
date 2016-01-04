package ua.edu.sumdu.j2se.ZavaliiVV.tasks.notification;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;

import java.util.List;

public class ScreenNotification implements Notification {

    @Override
    public void notifyUser(List<Task> tasks) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Attention!!!");
        stringBuffer.append('\n');
        stringBuffer.append("List of tasks whose time has come:");
        stringBuffer.append('\n');

        int i = 1;
        for (Task task : tasks) {
            stringBuffer.append(task.getTitle());
            if(i == tasks.size()) {
                stringBuffer.append(".");
            } else {
                stringBuffer.append("; ");
            }
            i++;
        }

        stringBuffer.append("\n");

        System.out.println(stringBuffer.toString());

    }

}
