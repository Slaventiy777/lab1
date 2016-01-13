package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;

import org.apache.log4j.Logger;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.Task;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskList;
import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.TaskManagerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.SortedMap;


/**
 * Created by Zavalii V.V. on 28.12.2015.
 */

public class TaskManagerView implements Observer {

    private final TaskManagerModel model;
    private OperationView previousOperation;
    private static final Logger log = Logger.getLogger(TaskManagerView.class);

    public TaskManagerView(TaskManagerModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    public OperationView getPreviousOperation() {
        return previousOperation;
    }

    public void setPreviousOperation(OperationView previousOperation) {
        this.previousOperation = previousOperation;
    }

    public void setMenu(OperationView operationView) {

        switch (operationView) {

            case ListTasks:

                System.out.println();
                System.out.println("View task information enter: 1");
                System.out.println("Add new task enter:          2");
                System.out.println("Edit task enter:             3");
                System.out.println("Remove task enter:           4");
                System.out.println("View calendar enter:         5");
                System.out.println("Update list tasks enter:     6");
                System.out.println("Quit enter:                  7");
                System.out.println();

                setPreviousOperation(operationView);
                break;
            case Add:

                System.out.println();
                System.out.println("Save task enter:                    1");
                System.out.println("Back to the previous window enter:  2");
                System.out.println("View list tasks enter:              3");
                System.out.println();

                break;
            case Edit:

                System.out.println();
                System.out.println("Start edit enter:                   1");
                System.out.println("Back to the previous window enter:  2");
                System.out.println("View list tasks enter:              3");
                System.out.println();

                break;
            case Calendar:

                System.out.println();
                System.out.println("Back list tasks enter:  1");
                System.out.println("Add new task enter:     2");
                System.out.println();

                setPreviousOperation(operationView);
                break;
            case TaskInformations:

                System.out.println();
                System.out.println("Back list tasks enter: 1");
                System.out.println("Edit task enter:       2");
                System.out.println();

                setPreviousOperation(operationView);
                break;
            case Remove:

                System.out.println();

                break;
            case Update:

                System.out.println();

                break;
            case Exit:

                System.out.println();

                break;

        }

    }

    public OperationView enterOperation(OperationView currentOperation) throws IOException {

        OperationView operationView = OperationView.Error;

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

            String text;
            text = bReader.readLine();

            int intOperation = Integer.parseInt(text);

            if(currentOperation == OperationView.ListTasks) {

                if(intOperation == 1)
                    operationView = OperationView.TaskInformations;
                else if(intOperation == 2)
                    operationView = OperationView.Add;
                else if(intOperation == 3)
                    operationView = OperationView.Edit;
                else if(intOperation == 4)
                    operationView = OperationView.Remove;
                else if(intOperation == 5)
                    operationView = OperationView.Calendar;
                else if(intOperation == 6)
                    operationView = OperationView.Update;
                else if(intOperation == 7)
                    operationView = OperationView.Exit;
                else
                    System.out.println("Incorrect entry operation!");

            } else if(currentOperation == OperationView.TaskInformations) {

                if(intOperation == 1)
                    operationView = OperationView.ListTasks;
                else if(intOperation == 2)
                    operationView = OperationView.Edit;
                else
                    System.out.println("Incorrect entry operation!");

            } else if(currentOperation == OperationView.Add) {

                if(intOperation == 1)
                    operationView = OperationView.SaveTask;
                else if(intOperation == 2)
                    operationView = getPreviousOperation();
                else if(intOperation == 3)
                    operationView = OperationView.ListTasks;
                else
                    System.out.println("Incorrect entry operation!");

            } else if(currentOperation == OperationView.Edit) {

               if(intOperation == 1)
                    operationView = OperationView.EditMode;
               else if(intOperation == 2)
                    operationView = getPreviousOperation();
               else if(intOperation == 3)
                    operationView = OperationView.ListTasks;
               else
                    System.out.println("Incorrect entry operation!");

            } else if(currentOperation == OperationView.Calendar) {

                if(intOperation == 1)
                    operationView = OperationView.ListTasks;
                else if(intOperation == 2)
                    operationView = OperationView.Add;
                else
                    System.out.println("Incorrect entry operation!");

            }

        } catch (NumberFormatException e) {
            log.error("Incorrect entry operation!");
            System.out.println("\nEnter correct operation:");
            return enterOperation(currentOperation);
        } catch (IOException e) {
            log.error("Incorrect entry operation!");
            System.out.println("\nEnter correct operation:");
            return enterOperation(currentOperation);
        }

        return operationView;

    }


    public int choiceTaskID(int size) throws IOException {

        if(size <= 0) {
            System.out.println("Task list is empty!");
            return -1;
        }

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        int index = -1;
        try {
            while(true) {

                System.out.println("Enter task ID:");

                String text;
                text = bReader.readLine();

                int intOperation = Integer.parseInt(text);

                if(intOperation > 0 & intOperation <= size) {
                    index = intOperation;
                    break;
                } else {
                    System.out.println("Incorrect entry task ID.");
                }

            }
        } catch (NumberFormatException e) {
            log.error("Incorrect entry task ID.");
            return choiceTaskID(size);
        } catch (IOException e) {
            log.error("Incorrect entry task ID.");
            return choiceTaskID(size);
        }

        return index;

    }


    public String enterTitle() throws IOException {

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        String title;
        try {
            while(true) {
                System.out.println("Enter title task:");

                title = bReader.readLine();

                if(!title.trim().equals("")) {
                    break;
                } else {
                    System.out.println("Incorrect entry task title.");
                }
            }
        } catch (NumberFormatException e) {
            log.error("Incorrect entry task title.");
            return enterTitle();
        } catch (IOException e) {
            log.error("Incorrect entry task title.");
            return enterTitle();
        }

        return title;
    }

    public boolean enterBoolean(String str) throws IOException {

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        boolean isRepeated;
        String text;
        try {
            while(true) {
                System.out.println("If task is " + str + " enter:     Y");
                System.out.println("If task is not " + str + " enter: N");

                text = bReader.readLine();

                if(text.equals("Y")) {
                    isRepeated = true;
                    break;
                } else if(text.equals("N")) {
                    isRepeated = false;
                    break;
                } else {
                    System.out.println("Incorrect entry info task " + str + ".");
                }
            }
        } catch (NumberFormatException e) {
            log.error("Incorrect entry info task " + str + ".");
            return enterBoolean(str);
        } catch (IOException e) {
            log.error("Incorrect entry info task " + str + ".");
            return enterBoolean(str);
        }

        return isRepeated;

    }

    public Date enterDate(String str) throws IOException {

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        Date date;
        String text;
        try {
            while(true) {
                System.out.println("Enter " + str + " in format - year-month-day hour:minute:second.millisecond");

                text = bReader.readLine();

                try {
                    date = TaskIO.stringToDate("[" + text + "]");
                    break;
                } catch (ParseException e) {
                    log.error("Incorrect entry info " + str + ".");
                    return enterDate(str);
                }
            }
        } catch (NumberFormatException e) {
            log.error("Incorrect entry info " + str + ".");
            return enterDate(str);
        } catch (IOException e) {
            log.error("Incorrect entry info " + str + ".");
            return enterDate(str);
        }

        return date;

    }

    public int enterInterval() throws IOException {

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        String text;
        int interval;
        try {
            while(true) {
                System.out.println("Enter interval repeated task (in seconds):");

                text = bReader.readLine();

                interval = Integer.parseInt(text);

                if(interval > 0) {
                    break;
                } else {
                    System.out.println("Incorrect entry task interval.");
                }
            }
        } catch (NumberFormatException e) {
            log.error("Incorrect entry task interval.");
            return enterInterval();
        } catch (IOException e) {
            log.error("Incorrect entry task interval.");
            return enterInterval();
        }

        return interval;
    }


    public void editTask(Task task) throws IOException {

        System.out.println("***Edit mode***");

        try {
            while (true) {

                System.out.println();
                System.out.println("Edit title enter:       1");
                if(!task.isRepeated()) {
                    System.out.println("Edit time enter:        2");
                    System.out.println("Edit active task enter: 3");
                    System.out.println("Exit edit mode enter:   4");
                } else {
                    System.out.println("Edit start time enter:  2");
                    System.out.println("Edit end time enter:    3");
                    System.out.println("Edit interval enter:    4");
                    System.out.println("Edit active task enter: 5");
                    System.out.println("Exit edit mode enter:   6");
                }
                System.out.println();

                BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

                String text;
                text = bReader.readLine();

                int intOperation = Integer.parseInt(text);

                if(!task.isRepeated()) {

                    if(intOperation == 1)
                        task.setTitle(enterTitle());
                    else if(intOperation == 2)
                        task.setTime(enterDate("time"));
                    else if(intOperation == 3)
                        task.setActive(enterBoolean("active"));
                    else if(intOperation == 4)
                        return;
                    else
                        System.out.println("Incorrect entry operation!");

                } else {

                    if(intOperation == 1)
                        task.setTitle(enterTitle());
                    else if(intOperation == 2)
                        task.setTime(enterDate("start time"));
                    else if(intOperation == 3)
                        task.setTime(enterDate("end time"));
                    else if(intOperation == 4)
                        task.setInterval(enterInterval());
                    else if(intOperation == 5)
                        task.setActive(enterBoolean("active"));
                    else if(intOperation == 6)
                        return;
                    else
                        System.out.println("Incorrect entry operation!");

                }

            }
        } catch (NumberFormatException e) {
            log.error("Incorrect entry operation.");
            editTask(task);
            return;
        } catch (IOException e) {
            log.error("Incorrect entry operation.");
            editTask(task);
            return;
        }

    }


    @Override
    public void update(TaskList tasks, OperationView operationView) {

        UpdateWindow updateWindow = new UpdateWindow();

        switch (operationView) {

            case Add:
            case Edit:
                updateWindow.setWindowStrategy(new WindowEdit());
                updateWindow.openWindowStrategy(tasks);
                break;
            case ListTasks:
                updateWindow.setWindowStrategy(new WindowListTasks());
                updateWindow.openWindowStrategy(tasks);
                break;
            case Calendar:
                updateWindow.setWindowStrategy(new WindowCalendar());
                break;
            case TaskInformations:
                updateWindow.setWindowStrategy(new WindowTaskInformations());
                updateWindow.openWindowStrategy(tasks);
                break;

        }

    }

    @Override
    public void update(SortedMap<Date, Set<Task>> calendar, OperationView operationView) {

        UpdateWindow updateWindow = new UpdateWindow();

        switch (operationView) {

            case Add:
            case Edit:
                updateWindow.setWindowStrategy(new WindowEdit());
                break;
            case ListTasks:
                updateWindow.setWindowStrategy(new WindowListTasks());
                break;
            case Calendar:
                updateWindow.setWindowStrategy(new WindowCalendar());
                updateWindow.openWindowStrategy(calendar);
                break;
            case TaskInformations:
                updateWindow.setWindowStrategy(new WindowTaskInformations());
                break;

        }

    }

}
