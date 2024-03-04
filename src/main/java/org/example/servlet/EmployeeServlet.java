package org.example.servlet;

import org.example.entity.Employee;
import org.example.mapper.EmployeeMapper;
import org.example.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employees/*")
public class EmployeeServlet extends HttpServlet {
    EmployeeService employeeService;
    @Override
    public void init() {
        employeeService = (EmployeeService) getServletContext().getAttribute("employeeService");
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().matches("^/\\d+/tasks$")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/employee_tasks" + req.getPathInfo());
            dispatcher.forward(req, resp);
        } else if(req.getPathInfo().matches("^/\\d+/tasks/\\d+$")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/employee_task" + req.getPathInfo());
            dispatcher.forward(req, resp);
        } else if (req.getPathInfo().matches("^/\\d+/pc$")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/employee_pc" + req.getPathInfo());
            dispatcher.forward(req, resp);
        } else if(req.getPathInfo().matches("^/\\d+$")){
            super.service(req,resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long employeeId = Long.parseLong(req.getPathInfo().substring(1));
            Employee employee = employeeService.getEmployeeById(employeeId);
            EmployeeMapper.writeJsonResponse(resp, employee);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long employeeId = Long.valueOf(req.getPathInfo().substring(1));
        try {
            Employee updatedEmployee = EmployeeMapper.readJsonRequest(req);
            updatedEmployee.setId(employeeId);
            employeeService.updateEmployee(updatedEmployee);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long employeeId = Long.parseLong(req.getPathInfo().substring(1));
            employeeService.deleteEmployee(employeeId);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
