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
import java.util.List;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {

    TaskService taskService;
    @Override
    public void init() {
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Task> tasks = taskService.getAllTasks();
            resp.setContentType("application/json");
            TaskMapper.writeJsonResponse(resp, tasks);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException | NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
