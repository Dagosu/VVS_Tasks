package tasks.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTaskListUnitTest {

    @Test
    public void testAddTask() throws ParseException {
        ArrayTaskList taskList = new ArrayTaskList();
        Task task = new Task("Test task", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testRemoveTask() throws ParseException {
        ArrayTaskList taskList = new ArrayTaskList();
        Task task1 = new Task("Test task 1", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        Task task2 = new Task("Test task 2", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        taskList.add(task1);
        taskList.add(task2);
        assertTrue(taskList.remove(task1));
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    public void testIterator() throws ParseException {
        ArrayTaskList taskList = new ArrayTaskList();
        Task task1 = new Task("Test task 1", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        Task task2 = new Task("Test task 2", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        Task task3 = new Task("Test task 3", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        int i = 0;
        for (Task task : taskList) {
            switch (i) {
                case 0:
                    assertEquals(task1, task);
                    break;
                case 1:
                    assertEquals(task2, task);
                    break;
                case 2:
                    assertEquals(task3, task);
                    break;
            }
            i++;
        }
    }

    @Test
    public void testEquals() throws ParseException {
        ArrayTaskList taskList1 = new ArrayTaskList();
        ArrayTaskList taskList2 = new ArrayTaskList();
        Task task1 = new Task("Test task 1", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        Task task2 = new Task("Test task 2", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-28 10:10"), 1);
        taskList1.add(task1);
        taskList1.add(task2);
        taskList2.add(task1);
        taskList2.add(task2);
        assertTrue(taskList1.equals(taskList2));
    }
}
