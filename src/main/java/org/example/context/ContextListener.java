package org.example.context;

import org.example.repository.DatabaseConnectionManager;
import org.example.repository.EmployeeRepository;
import org.example.repository.PCRepository;
import org.example.repository.TaskRepository;
import org.example.service.EmployeeService;
import org.example.service.PCService;
import org.example.service.TaskService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        EmployeeRepository employeeRepository = new EmployeeRepository(connection);
        TaskRepository taskRepository = new TaskRepository(connection);
        PCRepository pcRepository = new PCRepository(connection);
        final ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("employeeRepository", employeeRepository);
        servletContext.setAttribute("taskRepository", taskRepository);
        servletContext.setAttribute("pcRepository", pcRepository);

        servletContext.setAttribute("employeeService", new EmployeeService(connection));
        servletContext.setAttribute("taskService", new TaskService(connection));
        servletContext.setAttribute("pcService", new PCService(connection));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
