package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskRepositoryTest {
    private TaskRepository taskRepository;
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @Before
    public void setUp() throws SQLException {
        Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        taskRepository = new TaskRepository(connection);
        employeeRepository = new EmployeeRepository(connection);
        employee = new Employee( "name", "surname", "position", "email");
        employeeRepository.addEmployee(employee);
        employee.setId(employeeRepository.getAllEmployees().get(employeeRepository.getAllEmployees().size() - 1).getId());
    }

    @After
    public void tearDown() throws SQLException {
        employeeRepository.deleteEmployee(employee.getId());
    }

    @Test
    public void testAddAndGetAllTasks() throws SQLException {
        List<Task> tasksBeforeAdding = taskRepository.getAllEmployeeTasks(employee.getId());
        Task task = new Task("title", "description", LocalDate.now(), false, employee.getId());
        taskRepository.addTask(task);
        List<Task> tasksAfterAdding = taskRepository.getAllEmployeeTasks(employee.getId());
        Task dbTask = tasksAfterAdding.get(tasksAfterAdding.size() - 1);
        task.setId(dbTask.getId());
//        taskDao.deleteTask(dbTask.getId());
        assertEquals(tasksBeforeAdding.size(), tasksAfterAdding.size() - 1);
        assertEquals(task, dbTask);
    }

    @Test
    public void testUpdateTask() throws SQLException {
        Task task = new Task("title", "description", LocalDate.now(), false, employee.getId());
        taskRepository.addTask(task);
        List<Task> tasks = taskRepository.getAllEmployeeTasks(employee.getId());
        task.setId(tasks.get(0).getId());
        task.setDescription("new description");
        task.setDeadlineDate(LocalDate.now());
        taskRepository.updateTask(task);
        List<Task> updatedTasks = taskRepository.getAllEmployeeTasks(employee.getId());
        Task dbTask = updatedTasks.get(updatedTasks.size() - 1);
        taskRepository.deleteTask(task.getId());
        assertEquals(task, dbTask);
    }

    @Test
    public void testDeleteTask() throws SQLException {
        Task task = new Task("title", "description", LocalDate.now(), false, employee.getId());
        taskRepository.addTask(task);
        List<Task> tasksBeforeDeleting = taskRepository.getAllEmployeeTasks(employee.getId());
        taskRepository.deleteTask(tasksBeforeDeleting.get(tasksBeforeDeleting.size() - 1).getId());
        List<Task> tasksAfterDeleting = taskRepository.getAllEmployeeTasks(employee.getId());
        assertEquals(tasksBeforeDeleting.size(), tasksAfterDeleting.size() + 1);
    }
}
