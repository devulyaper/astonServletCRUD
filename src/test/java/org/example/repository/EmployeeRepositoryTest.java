package org.example.repository;

import org.example.entity.Employee;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeRepositoryTest {
    Connection connection;
    EmployeeRepository employeeRepository;

    @Before
    public void setUp(){
        connection = DatabaseConnectionManager.getInstance().getConnection();
        employeeRepository = new EmployeeRepository(connection);
    }

    @Test
    public void testAddEmployeeAndGetAllEmployees() throws SQLException{
        Employee employee1 = new Employee("name", "surname", "position", "email");
        Employee employee2 = new Employee("name2", "surname2", "position2", "email2");

        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);

        List<Employee> employees = employeeRepository.getAllEmployees();

        Employee bdEmployee1 = employees.get(employees.size()-2);
        Employee bdEmployee2 = employees.get(employees.size()-1);

        employee1.setId(bdEmployee1.getId());
        employee2.setId(bdEmployee2.getId());

        employeeRepository.deleteEmployee(employee1.getId());
        employeeRepository.deleteEmployee(employee2.getId());

        assertEquals(employee1, bdEmployee1);
        assertEquals(employee2, bdEmployee2);
    }

    @Test
    public void getEmployeeByIdTest() throws SQLException {
        Employee employee = employeeRepository.getEmployeeById(77L);
        assertEquals(employee.getName(), "Jack");
    }

    @Test
    public void updateEmployeeTest() throws SQLException{
        Employee employee = new Employee( "John", "Martins", "Manager", "email@gmail.com");
        employeeRepository.addEmployee(employee);

        List<Employee> employees = employeeRepository.getAllEmployees();
        Long dbEmployeeId = employees.get(employees.size() - 1).getId();
        employee.setId(dbEmployeeId);

        employee.setPosition("Director");
        employeeRepository.updateEmployee(employee);

        employees = employeeRepository.getAllEmployees();
        assertEquals(employee, employees.get(employees.size() - 1));

        employeeRepository.deleteEmployee(dbEmployeeId);
    }



//    @Test
//    public void testCreateEmployeeTable() throws SQLException {
//        EmployeeDao employeeDao = new EmployeeDao(DatabaseConnectionManager.getInstance().getConnection());
////        employeeDao.createEmployeeTable();
//        List<Employee> employeesAfterDelete = employeeDao.getAllEmployees();
//        assertEquals(0, employeesAfterDelete.size());
//    }
}
