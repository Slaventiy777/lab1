package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskIO {

    public final static int DAY     = 24*60*60;
    public final static int HOUR    = 60*60;
    public final static int MINUTES = 60;
    public final static int SECOND  = 1;

    private static final SimpleDateFormat sdf
            = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS]");

    private static final Pattern patternTask =
            Pattern.compile(
                    "^\"(.+)\"(( at (\\[[\\d\\s\\-:.]+\\]))"
                    + "|( from (\\[[\\d\\s\\-\\:\\.]+\\]) to (\\[[\\d\\s\\-\\:\\.]+\\]) "
                    + "every (\\[.+\\])))( inactive)?[.;]$");

    private static final Pattern patternInterval
            = Pattern.compile(
            "^\\[(([0-9]+) days?)? ?(([0-9]+) hours?)? ?(([0-9]+) minutes?)? "+
                    "?(([0-9]+) seconds?)?\\]$");

    public static void write(TaskList tasks, OutputStream out) throws IOException {

        DataOutputStream dOut = new DataOutputStream(out);

        try {

            dOut.writeInt(tasks.size());

            for (Task task : tasks) {

                dOut.writeUTF(task.getTitle());
                dOut.writeBoolean(task.isActive());
                dOut.writeInt(task.getInterval());

                if (task.isRepeated()) {
                    dOut.writeLong(task.getStartTime().getTime());
                    dOut.writeLong(task.getEndTime().getTime());
                } else {
                    dOut.writeLong(task.getTime().getTime());
                }

            }

        }
        finally {
            dOut.flush();
        }

    }

    public static void read(TaskList tasks, InputStream in) throws IOException {

        DataInputStream dIn = new DataInputStream(in);

        try {

            int sizeTasks = dIn.readInt();

            for (int i = 0; i < sizeTasks; i++) {

                Task task       = null;
                String title    = dIn.readUTF();
                boolean active  = dIn.readBoolean();
                int interval    = dIn.readInt();

                if (interval > 0) {
                    Date startTime  = new Date(dIn.readLong());
                    Date endTime    = new Date(dIn.readLong());

                    task = new Task(title, startTime, endTime, interval);
                } else {
                    Date time = new Date(dIn.readLong());

                    task = new Task(title, time);
                }

                if (task != null) {
                    task.setActive(active);
                    tasks.add(task);
                }

            }

        }
        finally {
            dIn.close();
        }

    }

    public static void writeBinary(TaskList tasks, File file) throws IOException {

        FileOutputStream fileOut = new FileOutputStream(file);

        try {
           write(tasks, fileOut);
        }
        finally {
            fileOut.close();
        }

    }

    public static void readBinary(TaskList tasks, File file) throws IOException {

        FileInputStream fileIn = new FileInputStream(file);

        try {
            read(tasks, fileIn);
        }
        finally {
            fileIn.close();
        }

    }

    public static void write(TaskList tasks, Writer out) throws IOException {

        BufferedWriter buffWriter = new BufferedWriter(out);

        try {

            int index = 0;
            int maxIndex = tasks.size();
            for (Task task : tasks) {

                index++;
                boolean lastIndex = (index == maxIndex);

                buffWriter.append(taskToString(task, lastIndex));

                if (!lastIndex)
                    buffWriter.newLine();

            }

        }
        finally {
            buffWriter.flush();
        }

    }

    public static void read(TaskList tasks, Reader in) throws IOException, ParseException {

        BufferedReader buffReader = new BufferedReader(in);

        try {

            if (buffReader.ready()) {
                String nLine = buffReader.readLine();
                while (nLine != null) {

                    String title    = "";
                    Date time       = new Date(0);
                    Date startTime  = new Date(0);
                    Date endTime    = new Date(0);
                    int interval    = 0;
                    boolean active  = true;

                    Matcher match = patternTask.matcher(nLine);

                    if (match.matches()) {

                        if (match.group(1) != null) {
                            title = match.group(1);
                            title = title.replaceAll("\"\"", "\"");
                        } else {
                            throw new ParseException("Wrong string format to parsing Task", 0);
                        }
                        active = (match.group(9) == null);

                        if (match.group(2) != null) {
                            if (match.group(2).contains("every")) {
                                if (match.group(6) != null &&
                                        match.group(7) != null &&
                                        match.group(8) != null) {
                                    startTime = sdf.parse(match.group(6));
                                    endTime = sdf.parse(match.group(7));

                                    String intervalString = match.group(8);
                                    Matcher matchInterval = patternInterval.matcher(intervalString);
                                    if (matchInterval.matches()) {

                                        interval += DAY *
                                                (matchInterval.group(2) != null ? Integer.parseInt(matchInterval.group(2)) : 0)
                                                + HOUR *
                                                (matchInterval.group(4) != null ? Integer.parseInt(matchInterval.group(4)) : 0)
                                                + MINUTES *
                                                (matchInterval.group(6) != null ? Integer.parseInt(matchInterval.group(6)) : 0)
                                                + SECOND *
                                                (matchInterval.group(8) != null ? Integer.parseInt(matchInterval.group(8)) : 0);

                                    } else {
                                        throw new ParseException("Wrong string format to parsing interval", 0);
                                    }

                                } else {
                                    throw new ParseException("Wrong string format to parsing Task", 0);
                                }
                            } else if (match.group(2).contains("at")) {

                                if (match.group(4) != null) {
                                    time = sdf.parse(match.group(4));
                                } else {
                                    throw new ParseException("Wrong string format to parsing Task", 0);
                                }
                            } else {
                                throw new ParseException("Wrong string format to parsing Task", 0);
                            }
                        } else {
                            throw new ParseException("Wrong string format to parsing Task", 0);
                        }
                    } else {
                        throw new ParseException("Wrong string format to parsing Task", 0);
                    }

                    Task task;
                    if (interval > 0) {
                        task = new Task(title, startTime, endTime, interval);
                    } else {
                        task = new Task(title, time);
                    }
                    task.setActive(active);

                    tasks.add(task);

                    nLine = buffReader.readLine();

                }
            } else {
                throw new IOException("Stream is not ready to be read!");
            }


        }
        finally {
            buffReader.close();
        }

    }

    public static void writeText(TaskList tasks, File file) throws IOException {

        FileWriter fileW = new FileWriter(file);

        try {
            write(tasks, fileW);
        }
        finally {
            fileW.close();
        }

    }

    public static void readText(TaskList tasks, File file) throws IOException, ParseException {

        FileReader fileR = new FileReader(file);

        try {
            read(tasks, fileR);
        }
        finally {
            fileR.close();
        }

    }


    public static String taskToString(Task task, boolean lastIndex) {

        StringBuffer nLine = new StringBuffer();

        nLine.append('"');
        nLine.append(task.getTitle().replaceAll("\"", "\"\""));
        nLine.append('"');

        if (!task.isRepeated()) {
            Date time = new Date(task.getTime().getTime());
            nLine.append(" at ");
            nLine.append(sdf.format(time));

        } else {
            Date startTime = new Date(task.getStartTime().getTime());
            Date endTime = new Date(task.getEndTime().getTime());

            nLine.append(" from ");
            nLine.append(sdf.format(startTime));
            nLine.append(" to ");
            nLine.append(sdf.format(endTime));

            nLine.append(" every ");
            nLine.append(intervalToString(task.getInterval()));

        }

        if (!task.isActive())
            nLine.append(" inactive");

        if (!lastIndex) {
            nLine.append(";");
        } else {
            nLine.append(".");

        }

        return nLine.toString();

    }

    public static String intervalToString(int interval) {

        StringBuffer sb = new StringBuffer();

        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if (interval >= DAY) {
            days = (interval - (interval % DAY)) / DAY;
            interval = interval % DAY;
        }

        if (interval >= HOUR) {
            hours = (interval - (interval % HOUR)) / HOUR;
            interval = interval % HOUR;
        }

        if (interval >= MINUTES) {
            minutes = (interval - (interval % MINUTES)) / MINUTES;
            interval = interval % MINUTES;
        }

        seconds = interval;

        if (days > 0 || hours > 0 || minutes > 0 || seconds > 0) {

            boolean isFirst = false;

            sb.append("[");

            if (days == 1) {
                sb.append(isFirst ? " 1 day" : "1 day");
                isFirst = true;
            } else if (days > 1){
                sb.append(isFirst ? " " + days + " days" : days + " days");
                isFirst = true;
            }

            if (hours == 1) {
                sb.append(isFirst ? " 1 hour" : "1 hour");
                isFirst = true;
            } else if (hours > 1){
                sb.append(isFirst ? " " + hours + " hours" : hours + " hours");
                isFirst = true;
            }

            if (minutes == 1) {
                sb.append(isFirst ? " 1 minutes" : "1 minutes");
                isFirst = true;
            } else if (minutes > 1){
                sb.append(isFirst ? " " + minutes + " minutes" : minutes + " minutes");
                isFirst = true;
            }

            if (seconds == 1) {
                sb.append(isFirst ? " 1 seconds" : "1 seconds");
                isFirst = true;
            } else if (seconds > 1){
                sb.append(isFirst ? " " + seconds + " seconds" : seconds + " seconds");
                isFirst = true;
            }

            sb.append("]");

        }

        return sb.toString();

    }

    public static Date stringToDate(String str) throws ParseException {

        Date date;
        date = sdf.parse(str);

        return date;

    }

}
