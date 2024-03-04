package org.example.repository;
import org.example.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static final String GET_ALL_TASKS_QUERY = "SELECT * FROM task";
    private static final String GET_ALL_EMPLOYEE_TASKS_QUERY = "SELECT * FROM task where employee_id = ?";
    private static final String GET_TASK_BY_ID_QUERY = "SELECT * FROM task where id = ?";
    private static final String GET_EMPLOYEE_TASK_BY_ID_QUERY = "SELECT * FROM task where (employee_id = ?) AND (id = ?)";
    private static final String ADD_TASK_QUERY = "INSERT INTO task (title, description, deadline_date, is_done, employee_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_TASK_QUERY = "UPDATE task SET title = ?, description = ?, deadline_date = ?, is_done = ? WHERE id = ?";
    private static final String DELETE_TASK_QUERY = "DELETE FROM task WHERE id = ?";
    private static final String DELETE_EMPLOYEE_TASK_QUERY = "DELETE FROM task WHERE (employee_id = ?) AND (id = ?)";

    private final Connection connection;

    public TaskRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_TASKS_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDeadlineDate(rs.getDate("deadline_date").toLocalDate());
                task.setDone(rs.getBoolean("is_done"));
                task.setEmployeeId(rs.getLong("employee_id"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<Task> getAllEmployeeTasks(long id) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_EMPLOYEE_TASKS_QUERY)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDeadlineDate(rs.getDate("deadline_date").toLocalDate());
                task.setDone(rs.getBoolean("is_done"));
                task.setEmployeeId(rs.getLong("employee_id"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void addTask(Task task) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(ADD_TASK_QUERY)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(task.getDeadlineDate()));
            ps.setBoolean(4, task.isDone());
            ps.setLong(5, task.getEmployeeId());
            ps.executeUpdate();
        }
    }

    public void updateTask(Task task) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TASK_QUERY)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(task.getDeadlineDate()));
            ps.setBoolean(4, task.isDone());
            ps.setLong(5, task.getId());
            ps.executeUpdate();
        }
    }

    public void deleteTask(long id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TASK_QUERY)) {
            ps.setLong(1, id);
            int updatedStrings = ps.executeUpdate();
            if (updatedStrings < 1){
                throw new SQLException();
            }
        }
    }

    public Task getTaskById(long taskId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(GET_TASK_BY_ID_QUERY)) {
            ps.setLong(1, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDeadlineDate(rs.getDate("deadline_date").toLocalDate());
                task.setDone(rs.getBoolean("is_done"));
                task.setEmployeeId(rs.getLong("employee_id"));
                return task;
            } else {
                throw new SQLException();
            }
        }
    }

    public Task getEmployeeTaskById(long employeeId, long taskId) throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE_TASK_BY_ID_QUERY)) {
            ps.setLong(1, employeeId);
            ps.setLong(2, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDeadlineDate(rs.getDate("deadline_date").toLocalDate());
                task.setDone(rs.getBoolean("is_done"));
                task.setEmployeeId(rs.getLong("employee_id"));
                return task;
            } else {
                throw new SQLException();
            }
        }
    }

    public void deleteEmployeeTask(long employeeId, long taskId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_TASK_QUERY)) {
            ps.setLong(1, employeeId);
            ps.setLong(2, taskId);
            int updatedStrings = ps.executeUpdate();
            if (updatedStrings < 1){
                throw new SQLException();
            }
        }
    }
}
