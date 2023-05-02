package tasks.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

public class TasksServiceTest {

    private ArrayTaskList tasks;
    private TasksService tasksService;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayTaskList();
        tasksService = new TasksService(tasks);
    }

    @Test
    public void testGetObservableList() throws ParseException {
        tasks.add(new Task("Test Task",  Task.getDateFormat().parse("2023-03-28 10:10")));
        ObservableList<Task> observableList = tasksService.getObservableList();
        assertEquals(1, observableList.size());
        assertEquals("Test Task", observableList.get(0).getTitle());
    }

    @Test
    public void testGetIntervalInHours() throws ParseException {
        Task task = new Task("Test Task",  Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 3600);
        assertEquals("01:00", tasksService.getIntervalInHours(task));
    }

    @Test
    public void testFormTimeUnit() {
        assertEquals("00", tasksService.formTimeUnit(0));
        assertEquals("01", tasksService.formTimeUnit(1));
        assertEquals("10", tasksService.formTimeUnit(10));
    }

    @Test
    public void testParseFromStringToSeconds() {
        assertEquals(3600, tasksService.parseFromStringToSeconds("01:00")); // 1 hour
        assertEquals(0, tasksService.parseFromStringToSeconds("00:00"));
        assertEquals(600, tasksService.parseFromStringToSeconds("00:10"));
    }
}
