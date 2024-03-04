package org.example.dto;

import org.example.entity.Employee;

import java.util.Objects;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String email;

    public EmployeeDTO(Long id, String name, String surname, String position, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
    }

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO employee = (EmployeeDTO) o;
        return Objects.equals(id, employee.id)
                && Objects.equals(name, employee.name)
                && Objects.equals(surname, employee.surname)
                && Objects.equals(position, employee.position)
                && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, position, email);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
