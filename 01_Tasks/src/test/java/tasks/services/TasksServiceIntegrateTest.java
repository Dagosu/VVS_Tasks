package tasks.services;

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

public class TasksServiceIntegrateTest {

    @Test
    public void testGetIntervalInHours() {
        // Create a mock Task object
        Task mockTask = mock(Task.class);
        when(mockTask.getRepeatInterval()).thenReturn(7200); // 2 hours

        // Create an ArrayTaskList instance and add the mock Task object to it
        ArrayTaskList taskList = new ArrayTaskList();
        taskList.add(mockTask);

        // Create a TaskService instance using the ArrayTaskList instance
        TasksService service = new TasksService(taskList);

        // Call the getIntervalInHours method with the mock Task object and verify that the returned string is "02:00"
        String interval = service.getIntervalInHours(mockTask);
        Assertions.assertEquals("02:00", interval);
    }

    @Test
    public void testFilterTasks() throws ParseException {
        // Create a mock Task object
        Task mockTask = mock(Task.class);
        when(mockTask.getTitle()).thenReturn("Test task");
        when(mockTask.getStartTime()).thenReturn(Task.getDateFormat().parse("2023-02-12 10:10"));
        when(mockTask.getStartTime()).thenReturn(Task.getDateFormat().parse("2023-02-14 10:10"));

        // Create an ArrayTaskList instance and add the mock Task object to it
        ArrayTaskList taskList = new ArrayTaskList();
        taskList.add(mockTask);
        System.out.println(taskList);

        // Create a TaskService instance using the ArrayTaskList instance
        TasksService service = new TasksService(taskList);

        // Call the filterTasks method with some dates and verify that the returned Iterable contains the mock Task object
        Date start = Task.getDateFormat().parse("2023-02-10 10:10");
        Date end = Task.getDateFormat().parse("2023-02-15 10:10");
        Iterable<Task> filtered = service.filterTasks(start, end);
        System.out.println("aaaa" + filtered);
        List<Task> filteredList = new ArrayList<>();
        for (Task task : filtered) {
            filteredList.add(task);
        }
        Assertions.assertEquals(1, filteredList.size());
        Assertions.assertEquals("Test task", filteredList.get(0).getTitle());
    }
}
