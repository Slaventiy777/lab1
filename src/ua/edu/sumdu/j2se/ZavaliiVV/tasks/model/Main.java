package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import java.io.*;
 import java.text.ParseException;
 import java.util.*;

 class Main {
    
     public static void main(String[] args) throws CloneNotSupportedException, IOException, ParseException {
        
//        Task t1 = new Task();
//        Task t2 = new Task();
//        Task t3 = new Task();
//        Task t4 = new Task();
//
//        t1.setTitle("Task 1");
//        t2.setTitle("Task 2");
//
//       //try {
//            //t2.setTitle("");
//        //} catch (IOException e) {
//        //}
//
//        t3.setTitle("Task 3");
//        t4.setTitle("Task 4");
//
//        System.out.println("Title: " + t1.getTitle());
//        System.out.println("Title: " + t2.getTitle());
//        System.out.println("Title: " + t3.getTitle());
//        System.out.println("Title: " + t4.getTitle());
//
//        /* ArrayTaskList arrayTask = new ArrayTaskList(1);
//        System.out.println("Element 0: " + arrayTask.getTask(0));
//        arrayTask.remove(t2);
//        arrayTask.add(t1);
//        System.out.println("Size array: " + arrayTask.size());
//        arrayTask.add(t2);
//        System.out.println("Size array: " + arrayTask.size());
//        arrayTask.remove(t2);
//        System.out.println("Element 0: " + arrayTask.getTask(0));
//        System.out.println("Element 1: " + arrayTask.getTask(1));
//        System.out.println("Size array: " + arrayTask.size());
//        arrayTask.add(t2);
//        arrayTask.add(t3);
//        System.out.println("Element 0: " + arrayTask.getTask(0));
//        System.out.println("Element 1: " + arrayTask.getTask(1));
//        System.out.println("Element 2: " + arrayTask.getTask(2));
//        arrayTask.remove(t1);
//        System.out.println("Element 0: " + arrayTask.getTask(0));
//        System.out.println("Element 1: " + arrayTask.getTask(1));
//        System.out.println("Element 2: " + arrayTask.getTask(2));
//        System.out.println("Size array: " + arrayTask.size()); */
//
//        LinkedTaskList list = new LinkedTaskList();
//        list.add(t1);
//        list.add(t2);
//        list.add(t3);
//        list.add(t4);
//
//         System.out.println(list.toString());
//
//         LinkedTaskList list1 = new LinkedTaskList();
//         list1.add(t1);
//         list1.add(t2);
//         list1.add(t3);
//         list1.add(t4);
//
//        //list.incoming(-1, 6);
//
//        //list.getInfo();
//
////        list.remove(t1);
////        list.remove(t3);
////        list.remove(t2);
//        //list.remove(t4);
//
//        //System.out.println(t1.toString());
//
//        //list.getInfo();
//        //LinkedTaskList list1 = list.clone();
//
//        //System.out.println(list.toString());
//        System.out.println(list1.toString());
//
//        System.out.println(list.equals(list1));
//
//         LinkedTaskList list2 = list.clone();
//         list2.remove(t1);
//         System.out.println(list.equals(list2));
//         System.out.println(list.hashCode());
//         System.out.println(list2.hashCode());
//
//
//         TaskList listL = new LinkedTaskList();
//         listL.add(t1);
//         listL.add(t2);
//         listL.add(t3);
//         listL.add(t4);
//
//         TaskList listA = new ArrayTaskList();
//         listA.add(t1);
//         listA.add(t2);
//         listA.add(t3);
//         listA.add(t4);
//
//         System.out.println(listL.equals(listA));

         Date dateT = new Date();
         Task taskT = new Task("some", new Date(dateT.getTime()), new Date(dateT.getTime() + 3600*1000*24), 3600);
         taskT.setActive(true);
         System.out.println(taskT.nextTimeAfter(dateT));

         Set<Task> ts = set(
                 task("Simple IN", 55, true),//++
                 task("Simple OUT", 10, true),
                 task("Inactive OUT", 0, 1000, 1, false),//+
                 task("Simple bound OUT", 50, true),
                 task("Simple bound IN", 60, true),//++
                 task("Repeat inside IN", 51, 58, 2, true),//++
                 task("Repeat outside IN", 0, 100, 5, true),//++
                 task("Repeat outside OUT", 0, 100, 22, true),
                 task("Repeat left OUT", 0, 40, 1, true),
                 task("Repeat right OUT", 65, 100, 1, true),
                 task("Repeat left intersect IN 1", 0, 55, 13, true),//++
                 task("Repeat left intersect IN 2", 0, 60, 30, true),//++
                 task("Repeat left intersect OUT", 0, 55, 22, true),
                 task("Repeat right intersect IN", 55, 100, 20, true)//++
         );

         //void write(TaskList tasks, Writer out);

         //void read(TaskList tasks, Reader in);

         Set<String> incoming = new HashSet<String>();
         for (Task t : ts)
             if (t.getTitle().contains("IN"))
                 incoming.add(t.getTitle());
         Date start = seconds(50);
         Date end = seconds(60);
         Iterable<Task> res = Tasks.incoming(ts, start, end);

         Date TODAY = new Date();
         Task daily = new Task("Daily", new Date(TODAY.getTime() - 24*3600*1000), new Date(TODAY.getTime() + 24*3600*1000), 24*3600);
         Task hourly = new Task("Hourly", TODAY, new Date(TODAY.getTime() + 24*3600*1000), 3600);
         Task every3h = new Task("Every 3 hours", new Date(TODAY.getTime() + 3600*1000), new Date(TODAY.getTime() + 24*3600*1000), 3*3600);
         //Task test = new Task("Simple IN", new Date(TODAY.getTime() - 24*3600*1000));
         daily.setActive(true);
         hourly.setActive(true);
         every3h.setActive(true);
         //test.setActive(true);

         TaskList taskList = new LinkedTaskList();
         taskList.add(daily);
         taskList.add(hourly);
         taskList.add(every3h);
         //taskList.add(test);

         File in = new File("write");
         File out = new File("read");

         TaskList taskList1 = new ArrayTaskList();
         TaskIO.writeText(taskList, in);
         TaskIO.readText(taskList1, in);
         TaskIO.writeText(taskList1, out);


//         PipedInputStream inP = new PipedInputStream();
//         PipedOutputStream outP = new PipedOutputStream(inP);

         ByteArrayOutputStream byteOut = new ByteArrayOutputStream();


         TaskList taskList2 = new ArrayTaskList();
         TaskIO.write(taskList, new OutputStreamWriter(byteOut));
         ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
         TaskIO.read(taskList2, new InputStreamReader(byteIn));


         SortedMap<Date, Set<Task>> timeline = new TreeMap<Date, Set<Task>>();
         timeline.put(TODAY, set(daily, hourly));
         timeline.put(new Date(TODAY.getTime() + 3600*1000), set(hourly, every3h));
         timeline.put(new Date(TODAY.getTime() + 2*3600*1000), set(hourly));
         timeline.put(new Date(TODAY.getTime() + 3*3600*1000), set(hourly));
         timeline.put(new Date(TODAY.getTime() + 4*3600*1000), set(hourly, every3h));
         SortedMap<Date, Set<Task>> result =
                 Tasks.calendar(set(daily, hourly, every3h), new Date(TODAY.getTime() - 1), new Date(TODAY.getTime() + 4*3600*1000));

    }

     private static Set<Task> set(Task ... tasks) {
         return new HashSet<Task>(Arrays.asList(tasks));
     }

     public static Date seconds(int secs) {
         return new Date(secs * 1000);
     }

     protected static Task task(String title, int time, boolean active) {
         Task task = new Task(title, seconds(time));
         task.setActive(active);
         return task;
     }

     protected static Task task(String title, int start, int end, int interval, boolean active) {
         Task task = new Task(title, seconds(start), seconds(end), interval);
         task.setActive(active);
         return task;
     }

//     private static TaskList createList() {
//         TaskList tasks = new LinkedTaskList();
//         tasks.add(new Task("A", TODAY));
//         tasks.add(Task.create("B", TODAY, TOMORROW, HOUR));
//         Task t = Task.create("C", TODAY);
//         t.setActive(true);
//         tasks.add(t);
//         t = Task.create("B", TODAY, TOMORROW, HOUR);
//         t.setActive(true);
//         tasks.add(t);
//         return tasks;
//     }

}