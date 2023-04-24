package tasks.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class ArrayTaskListTest {

    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    ArrayTaskList list;

    @BeforeEach
    void setUp() throws ParseException {
        task1 = new Task("flotari", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
        list = new ArrayTaskList();
    }

    @Test
    void add() {
        try {
            Task task2 = new Task("flotari", Task.getDateFormat().parse("2023-03-30 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
            list.add(task2);
            assert list.size() == 1;
        }
        catch (Exception e) {
            assert(e.getMessage().equals("End time cannot be below start time"));
        }
    }

    @RepeatedTest(3)
    public void repeatedTest() {
        try {
            list.add(task1);
            assert list.size() == 1;
            System.out.println("Record added: " + list.getTask(0));
        }
        catch (Exception e) {
            assert false;
        }
    }

    @TestFactory
    public Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("Positive Test", () -> {
                    try {
                        Task task2 = new Task("flotari", Task.getDateFormat().parse("2023-03-30 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
                        list.add(task2);
                        assert list.size() == 1;
                    }
                    catch (Exception e) {
                        assert true;
                        System.out.println("Error: start date > end date " + e);
                    }
                }),
                dynamicTest("Negative Test", () -> {
                    try {
                        task3 = new Task("", Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
                        list.add(task3);
                        assert list.size() == 1;
                    }
                    catch (Exception e) {
                        assert true;
                        System.out.println("Error: invalid title length " + e);
                    }
                })
        );
    }

    @Test
    @DisplayName("Test adding a task to the list")
    void displayNameTest() {
        try {
            list.add(task1);
            assert list.size() == 1;
            System.out.println("Record added: " + list.getTask(0));
        } catch (Exception e) {
            assert false;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "bbb", "ccc"})
    void testAddTask(String title) {
        try {
            Task task = new Task(title, Task.getDateFormat().parse("2023-03-28 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
            list.add(task);
            assert list.size() == 1;
        } catch (Exception e) {
            assert true;
            System.out.println("Error: invalid title length " + e);
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void timeoutTest() {
        try {
            Task task2 = new Task("flotari", Task.getDateFormat().parse("2023-03-30 10:10"), Task.getDateFormat().parse("2023-03-29 10:10"), 1);
            list.add(task2);
            assert list.size() == 1;
        }
        catch (Exception e) {
            assert true;
            System.out.println("Error: start date > end date " + e);
        }
    }

    @AfterEach
    void tearDown() {
    }

}