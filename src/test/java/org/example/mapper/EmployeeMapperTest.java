package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.Employee;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeMapperTest {

    private String jsonString = "{\"id\":62,\"name\":\"John\",\"surname\":\"Martins\",\"position\":\"Manager\",\"email\":\"email@gmail.com\"}";
    private String jsonStringShort = "{\"name\":\"John\"}";

    @Test
    public void testToJson() throws JsonProcessingException {
        Employee employee = new Employee( "John", "Martins", "Manager", "email@gmail.com");
        assertEquals(EmployeeMapper.toJson(employee), jsonString);
    }

    @Test
    public void testFromJson() throws JsonProcessingException {
        Employee employee = EmployeeMapper.fromJson(jsonString);
        Employee employee1 = new Employee("John", "Martins", "Manager", "email@gmail.com");
        employee1.setId(62L);
        assertEquals(employee, employee1);
        employee = EmployeeMapper.fromJson(jsonStringShort);
        System.out.println(employee);
    }
}
