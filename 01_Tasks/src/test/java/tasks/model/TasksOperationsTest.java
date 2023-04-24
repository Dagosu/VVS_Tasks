package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TasksOperationsTest {
    Task task1 = new Task("flotari", Task.getDateFormat().parse("2023-04-27 10:10"), Task.getDateFormat().parse("2023-04-29 10:10"), 1);
    Task task2 = new Task("flotari", Task.getDateFormat().parse("2023-04-27 10:10"), Task.getDateFormat().parse("2023-04-29 10:10"), 1);

    TasksOperationsTest() throws ParseException {
    }

    @Test
    void incoming() throws ParseException {
        //Valid
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        ObservableList<Task> taskObservableList = FXCollections.observableList(taskList);
        TasksOperations tasksOperations = new TasksOperations(taskObservableList);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate, endDate;
        try {
            startDate = dateFormat.parse("28-04-2023");
            endDate = dateFormat.parse("29-04-2023");
            Iterable<Task> filteredTasks1 = tasksOperations.incoming(startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Invalid
        task1.setTime(null,null,1);
        task2.setTime(dateFormat.parse("30-04-2023"),dateFormat.parse("28-04-2023"),1);
        List<Task> taskList1 = new ArrayList<>();
        taskList1.add(task1);
        taskList1.add(task2);
        ObservableList<Task> taskObservableList1 = FXCollections.observableList(taskList1);
        TasksOperations tasksOperations1 = new TasksOperations(taskObservableList1);
        try {
            startDate = dateFormat.parse("28-04-2023");
            endDate = dateFormat.parse("29-04-2023");
            Iterable<Task> filteredTasks2 = tasksOperations1.incoming(startDate, endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}