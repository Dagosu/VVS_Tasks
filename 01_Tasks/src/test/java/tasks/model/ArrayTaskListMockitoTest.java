package tasks.model;

import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayTaskListMockitoTest {

    private ArrayTaskList arrayTaskList;
    private Task mockTask1;
    private Task mockTask2;
    private Task mockTask3;

    @BeforeEach
    public void setUp() {
        arrayTaskList = mock(ArrayTaskList.class);

        mockTask1 = mock(Task.class);
        mockTask2 = mock(Task.class);
        mockTask3 = mock(Task.class);

        when(arrayTaskList.size()).thenReturn(3);
        when(arrayTaskList.getAll()).thenReturn(Arrays.asList(mockTask1, mockTask2, mockTask3));
    }

    @Test
    public void testAddTask() throws ParseException {
        Task newTask = new Task("flotari", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
        arrayTaskList.add(newTask);
        when(arrayTaskList.size()).thenReturn(4);
        Assertions.assertEquals(4, arrayTaskList.size());
    }

    @Test
    public void testRemoveTask() {
        when(arrayTaskList.remove(mockTask2)).thenReturn(true);
        boolean result = arrayTaskList.remove(mockTask2);
        Assertions.assertTrue(result);
        Assertions.assertEquals(3, arrayTaskList.size());
    }

    @Test
    public void testGetAllTasks() {
        List<Task> expectedTasks = Arrays.asList(mockTask1, mockTask2, mockTask3);
        List<Task> actualTasks = arrayTaskList.getAll();

        Assertions.assertEquals(expectedTasks, actualTasks);
    }
}