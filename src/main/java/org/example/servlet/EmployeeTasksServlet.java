package org.example.servlet;

import org.example.entity.Task;
import org.example.mapper.TaskMapper;
import org.example.service.TaskService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee_tasks/*")
public class EmployeeTasksServlet extends HttpServlet {
    TaskService taskService;
    @Override
    public void init() {
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
        resp.setContentType("application/json");
        try {
            List<Task> tasks = taskService.getAllEmployeeTasks(employeeId);
            TaskMapper.writeJsonResponse(resp, tasks);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Task task = TaskMapper.readJsonRequest(req);
        long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
        task.setEmployeeId(employeeId);
        try {
            taskService.addTask(task);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
