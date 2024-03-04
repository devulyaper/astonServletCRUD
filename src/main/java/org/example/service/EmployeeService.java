package org.example.service;

import org.example.repository.EmployeeRepository;
import org.example.entity.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    Connection connection;
    EmployeeRepository employeeRepository;

    public EmployeeService(Connection connection){
        this.connection = connection;
        employeeRepository = new EmployeeRepository(connection);
    }


    public List<Employee> getAllEmployees() throws SQLException {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(long id) throws SQLException {
        return employeeRepository.getEmployeeById(id);
    }

    public void addEmployee(Employee employee) throws SQLException {
        employeeRepository.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) throws SQLException {
        employeeRepository.updateEmployee(employee);
    }

    public void deleteEmployee(long id) throws SQLException {
        employeeRepository.deleteEmployee(id);
    }
}

