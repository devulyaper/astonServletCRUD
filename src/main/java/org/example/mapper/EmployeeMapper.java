package org.example.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Employee employee) throws JsonProcessingException {
        return objectMapper.writeValueAsString(employee);
    }

    public static List<String> toJson(List<Employee> employees) throws JsonProcessingException {
        List<String> strings = new ArrayList<>();
        for(Employee employee : employees){
            strings.add(EmployeeMapper.toJson(employee));
        }
        return strings;
    }

    public static Employee fromJson(String json) throws JsonProcessingException {
        EmployeeDTO employeeDTO = objectMapper.readValue(json, EmployeeDTO.class);
        return new Employee(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPosition(),
                employeeDTO.getEmail()
        );
    }

    public static Employee readJsonRequest(HttpServletRequest req) throws IOException {
        EmployeeDTO employeeDTO = objectMapper.readValue(req.getReader(), EmployeeDTO.class);
        return new Employee(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPosition(),
                employeeDTO.getEmail());
    }

    public static void writeJsonResponse(HttpServletResponse resp, Employee employee) throws IOException {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getPosition(),
                employee.getEmail()
        );
        objectMapper.writeValue(resp.getWriter(), employeeDTO);
    }
}
