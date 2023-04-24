package tasks.model;

import javafx.collections.ObservableList;

import java.util.*;

public class TasksOperations {

    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList){
        tasks=new ArrayList<>();
        tasks.addAll(tasksList);
    }
    public Iterable<Task> incoming(Date start, Date end){
        System.out.println(start);
        System.out.println(end);
        ArrayList<Task> incomingTasks = new ArrayList<>();
        for (Task t : tasks) {
            Date nextTime = t.getStartTime();
            if (nextTime != null) {
                System.out.println(nextTime);
                System.out.println(" inainte ");
                System.out.println(end);
                if (nextTime.before(end) || nextTime.equals(end)) {
                    incomingTasks.add(t);
                    System.out.println(t.getTitle());
                }
                else {
                    System.out.println("Task " + t.getTitle() + " is not due yet.");
                }
            }
            else {
                System.out.println("Task " + t.getTitle() + " has no scheduled time.");
            }

        }
        if (incomingTasks.isEmpty()) {
            System.out.println("No tasks due between " + start + " and " + end + ".");
        }
        return incomingTasks;
    }
    public SortedMap<Date, Set<Task>> calendar( Date start, Date end){
        Iterable<Task> incomingTasks = incoming(start, end);
        TreeMap<Date, Set<Task>> calendar = new TreeMap<>();

        for (Task t : incomingTasks){
            Date nextTimeAfter = t.nextTimeAfter(start);
            while (nextTimeAfter!= null && (nextTimeAfter.before(end) || nextTimeAfter.equals(end))){
                if (calendar.containsKey(nextTimeAfter)){
                    calendar.get(nextTimeAfter).add(t);
                }
                else {
                    HashSet<Task> oneDateTasks = new HashSet<>();
                    oneDateTasks.add(t);
                    calendar.put(nextTimeAfter,oneDateTasks);
                }
                nextTimeAfter = t.nextTimeAfter(nextTimeAfter);
            }
        }
        return calendar;
    }
}

