package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.PC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PCDAOTest {
    private Employee employee;
    private EmployeeRepository employeeRepository;
    private PCRepository pcdao;
    @Before
    public void setUp() throws SQLException {
        Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        pcdao = new PCRepository(connection);
        employeeRepository = new EmployeeRepository(connection);
        employee = new Employee( "name", "surname", "position", "email");
        employeeRepository.addEmployee(employee);
        employee.setId(employeeRepository.getAllEmployees().get(employeeRepository.getAllEmployees().size() - 1).getId());
    }

    @After
    public void tearDown() throws SQLException {
        employeeRepository.deleteEmployee(employee.getId());
    }

    @Test
    public void testAddGetUpdateAndDeletePC() throws SQLException {
        PC pc = new PC();
        pc.setModel("apple macbook pro");
        pc.setEmployeeId(employee.getId());
        pcdao.addPC(pc);
        PC dbPc = pcdao.getPCByEmployeeId(employee.getId());
        pc.setId(dbPc.getId());
        assertEquals(pc,dbPc);
        pc.setModel("xiaomi redmi");
        pcdao.updatePC(pc);
        dbPc = pcdao.getAllPCs().get(pcdao.getAllPCs().size() - 1);
        assertEquals(pc,dbPc);
        pcdao.deletePC(pc.getId());
        dbPc = pcdao.getPCByEmployeeId(employee.getId());
        assertNull(dbPc);
    }
}
