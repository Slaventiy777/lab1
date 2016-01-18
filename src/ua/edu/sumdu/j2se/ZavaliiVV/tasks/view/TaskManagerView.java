package ua.edu.sumdu.j2se.ZavaliiVV.tasks.view;

import org.apache.log4j.Logger;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.model.*;

import static ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Print.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;


/**
 * Created by Zavalii V.V. on 28.12.2015.
 */

public class TaskManagerView implements Observer {

    private final TaskManagerModel model;

    private static OperationView previousOperationView = OperationView.ListTasks;
    private static OperationView operationView = OperationView.ListTasks;
    private Task tempTask = null;
    private CurrentWindow currentWindow = new CurrentWindow();

    public static BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

    private static final Logger log = Logger.getLogger(TaskManagerView.class);


    public TaskManagerView(TaskManagerModel model) {
        this.model = model;
        model.registerObserver(this);
    }


    public Task getTempTask() {
        return tempTask;
    }

    public void setTempTask(Task tempTask) {
        this.tempTask = tempTask;
    }


    public static OperationView getOperationView() {
        return operationView;
    }

    public static void setOperationView(OperationView nOperationView) {
        operationView = nOperationView;
    }


    public static OperationView getPreviousOperationView() {
        return previousOperationView;
    }

    public static void setPreviousOperationView(OperationView nPreviousOperation) {
        previousOperationView = nPreviousOperation;
    }


    public CurrentWindow getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(CurrentWindow currentWindow) {
        this.currentWindow = currentWindow;
    }


    public void enterOperation(CurrentWindow currentWindow) throws IOException {

        OperationView tempOperationView;

        try {

            int intOperation = Integer.parseInt(bReader.readLine());

            tempOperationView = currentWindow.enterOperation(intOperation);
            if(tempOperationView == null) {
                log.info("Incorrect entry operation!");
                enterOperation(currentWindow);
                return;
            } else {
                operationView = tempOperationView;
            }

        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            println();
            println("Enter correct operation:");
            enterOperation(currentWindow);
            return;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            println();
            println("Enter correct operation:");
            enterOperation(currentWindow);
            return;
        }

    }


    public int choiceTaskID(int size) throws IOException {

        if(size <= 0) {
            println("Task list is empty!");
            return -1;
        }

        int index = -1;
        try {
            while(true) {

                println("Enter task ID:");

                String text;
                text = bReader.readLine();

                int intOperation = Integer.parseInt(text);

                if(intOperation > 0 & intOperation <= size) {
                    index = intOperation;
                    break;
                } else {
                    println("Incorrect entry task ID.");
                }

            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return choiceTaskID(size);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return choiceTaskID(size);
        }

        return index;

    }


    public String enterTitle() throws IOException {

        String title;
        try {
            while(true) {
                println("Enter title task:");

                title = bReader.readLine();

                if(!title.trim().equals("")) {
                    break;
                } else {
                    println("Incorrect entry task title.");
                }
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return enterTitle();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return enterTitle();
        }

        return title;

    }

    public boolean enterBoolean(String str) throws IOException {

        boolean isRepeated;
        String text;
        try {
            while(true) {
                println("If task is " + str + " enter:     Y");
                println("If task is not " + str + " enter: N");

                text = bReader.readLine();

                if(text.equals("Y")) {
                    isRepeated = true;
                    break;
                } else if(text.equals("N")) {
                    isRepeated = false;
                    break;
                } else {
                    println("Incorrect entry info task " + str + ".");
                }
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return enterBoolean(str);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return enterBoolean(str);
        }

        return isRepeated;

    }

    public Date enterDate(String str) throws IOException {

        Date date;
        String text;
        try {
            while(true) {
                println("Enter " + str + " in format - year-month-day hour:minute:second");

                text = bReader.readLine();

                try {
                    date = TaskIO.stringToDate("[" + text + ".000]");
                    println("" + date);
                    break;
                } catch (ParseException e) {
                    log.error("Incorrect entry info " + str + ".");
                    return enterDate(str);
                }
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return enterDate(str);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return enterDate(str);
        }

        return date;

    }

    public int enterInterval() throws IOException {

        String text;
        int interval;
        try {
            while(true) {
                println("Enter interval repeated task (in seconds):");

                text = bReader.readLine();

                interval = Integer.parseInt(text);

                if(interval > 0) {
                    break;
                } else {
                    println("Incorrect entry task interval.");
                }
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return enterInterval();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return enterInterval();
        }

        return interval;
    }


    @Override
    public void update() {

        switch (operationView) {

            case ListTasks:
                currentWindow.setWindowStrategy(new WindowListTasks());
                break;
            case TaskInformations:
                currentWindow.setWindowStrategy(new WindowTaskInformations());
                break;
            case Calendar:
                currentWindow.setWindowStrategy(new WindowCalendar());
                break;
            case Add:
                currentWindow.setWindowStrategy(new WindowAdd());
                break;
            case Edit:
                currentWindow.setWindowStrategy(new WindowEdit());
                break;
            case EditMode:
                currentWindow.setWindowStrategy(new WindowEditMode(getTempTask()));
                break;
            default:
                throw new IllegalStateException();

        }

    }

}
