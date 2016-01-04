package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;

/**
 * Created by Zavalii V.V. on 28.12.2015.
 */

public class TaskManagerView implements Observer {

    private final TaskManagerModel model;

    public TaskManagerView(TaskManagerModel model) {

        this.model = model;

        model.registerObserver(this);

    }

    @Override
    public void update(TaskList tasks) {

    }

}
