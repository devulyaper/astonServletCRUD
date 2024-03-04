package org.example.service;

import org.example.entity.Task;
import org.example.repository.TaskRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(Connection connection) {
        this.taskRepository = new TaskRepository(connection);
    }

    public List<Task> getAllTasks() throws SQLException {
        return taskRepository.getAllTasks();
    }

    public List<Task> getAllEmployeeTasks(long employeeId) throws SQLException {
        return taskRepository.getAllEmployeeTasks(employeeId);
    }

    public void addTask(Task task) throws SQLException {
        taskRepository.addTask(task);
    }

    public void updateTask(Task task) throws SQLException {
        taskRepository.updateTask(task);
    }

    public Task getEmployeeTaskById(long employeeId, long taskId) throws SQLException {
        return taskRepository.getEmployeeTaskById(employeeId, taskId);
    }

    public void deleteEmployeeTask(long employeeId, long taskId) throws SQLException {
        taskRepository.deleteEmployeeTask(employeeId, taskId);
    }
}
