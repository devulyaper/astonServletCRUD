package org.example.servlet;

import org.example.entity.Employee;
import org.example.mapper.EmployeeMapper;
import org.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {
    EmployeeService employeeService;
    @Override
    public void init() throws ServletException {
        employeeService = (EmployeeService) getServletContext().getAttribute("employeeService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            String json = String.valueOf(EmployeeMapper.toJson(employees));
            resp.getWriter().print(json);
        } catch (SQLException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            Employee newEmployee = EmployeeMapper.readJsonRequest(req);
            employeeService.addEmployee(newEmployee);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
