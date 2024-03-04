package org.example.servlet;

import org.example.entity.Task;
import org.example.mapper.TaskMapper;
import org.example.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employee_task/*")
public class EmployeeTaskServlet extends HttpServlet {
    TaskService taskService;
    @Override
    public void init() {
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long taskId = Long.parseLong(req.getPathInfo().split("/")[3]);
        long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
        try {
            Task task = taskService.getEmployeeTaskById(employeeId, taskId);
            TaskMapper.writeJsonResponse(resp, task);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long taskId = Long.parseLong(req.getPathInfo().split("/")[3]);
        try {
            Task updatedTask = TaskMapper.readJsonRequest(req);
            updatedTask.setId(taskId);
            taskService.updateTask(updatedTask);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long employeeId = Long.parseLong(req.getPathInfo().split("/")[1]);
        long taskId = Long.parseLong(req.getPathInfo().split("/")[3]);
        try {
            taskService.deleteEmployeeTask(employeeId, taskId);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
