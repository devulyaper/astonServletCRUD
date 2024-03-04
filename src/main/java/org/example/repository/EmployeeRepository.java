package org.example.repository;

import org.example.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private static final String GET_ALL_EMPLOYEES_QUERY = "SELECT * FROM employee";
    private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM employee WHERE id = ?";
    private static final String ADD_EMPLOYEE_QUERY = "INSERT INTO employee (name, surname, position, email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE employee SET name = ?, surname = ?, position = ?, email = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employee WHERE id = ?";

    private final Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_EMPLOYEES_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setSurname(rs.getString("surname"));
                employee.setPosition(rs.getString("position"));
                employee.setEmail(rs.getString("email"));
                employees.add(employee);
            }
        }
        return employees;
    }

    public Employee getEmployeeById(Long id) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE_BY_ID_QUERY)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                Employee employee;
                if(rs.next()){
                    employee = new Employee();
                    employee.setId(rs.getLong("id"));
                    employee.setName(rs.getString("name"));
                    employee.setSurname(rs.getString("surname"));
                    employee.setPosition(rs.getString("position"));
                    employee.setEmail(rs.getString("email"));
                } else {
                    throw new SQLException();
                }
                return employee;
            }
        }
    }

    public void addEmployee(Employee employee) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(ADD_EMPLOYEE_QUERY)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getPosition());
            ps.setString(4, employee.getEmail());
            ps.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getPosition());
            ps.setString(4, employee.getEmail());
            ps.setLong(5, employee.getId());
            ps.executeUpdate();
        }
    }

    public void deleteEmployee(Long id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            ps.setLong(1, id);
            int updatedStrings = ps.executeUpdate();
            if (updatedStrings < 1){
                throw new SQLException();
            }
        }
    }

}
