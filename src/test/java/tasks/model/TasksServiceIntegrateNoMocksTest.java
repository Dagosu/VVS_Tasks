package tasks.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.*;
import javafx.collections.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

public class TasksServiceIntegrateNoMocksTest {

    @Test
    public void testGetIntervalInHours() throws ParseException {
        // Create a mock Task object
        Task task = new Task("flotari", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 7200);

        // Create an ArrayTaskList instance and add the mock Task object to it
        ArrayTaskList taskList = new ArrayTaskList();
        taskList.add(task);

        // Create a TaskService instance using the ArrayTaskList instance
        TasksService service = new TasksService(taskList);

        // Call the getIntervalInHours method with the mock Task object and verify that the returned string is "02:00"
        String interval = service.getIntervalInHours(task);
        Assertions.assertEquals("02:00", interval);
    }

    @Test
    public void testFilterTasks() throws ParseException {
        // Create a mock Task object
        Task task = new Task("flotari", Task.getDateFormat().parse("2023-02-12 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 7200);

        // Create an ArrayTaskList instance and add the mock Task object to it
        ArrayTaskList taskList = new ArrayTaskList();
        taskList.add(task);
        System.out.println(taskList);

        // Create a TaskService instance using the ArrayTaskList instance
        TasksService service = new TasksService(taskList);

        // Call the filterTasks method with some dates and verify that the returned Iterable contains the mock Task object
        Date start = Task.getDateFormat().parse("2023-02-10 10:10");
        Date end = Task.getDateFormat().parse("2023-02-15 10:10");
        Iterable<Task> filtered = service.filterTasks(start, end);
        List<Task> filteredList = new ArrayList<>();
        for (Task t : filtered) {
            filteredList.add(t);
        }
        Assertions.assertEquals(1, filteredList.size());
        Assertions.assertEquals("flotari", filteredList.get(0).getTitle());
    }
}
