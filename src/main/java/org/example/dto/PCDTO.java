package org.example.dto;

import org.example.entity.PC;

import java.util.Objects;

public class PCDTO {
    private Long id;
    private String model;
    private Long employeeId;

    public PCDTO(Long id, String model, Long employeeId) {
        this.id = id;
        this.model = model;
        this.employeeId = employeeId;
    }

    public PCDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PCDTO pc = (PCDTO) o;
        return Objects.equals(id, pc.id) && Objects.equals(model, pc.model) && Objects.equals(employeeId, pc.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, employeeId);
    }

    @Override
    public String toString() {
        return "PC{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
