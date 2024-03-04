package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate deadlineDate;
    private Long employeeId;
    private Boolean isDone;

    public TaskDTO(Long id, String title, String description, LocalDate deadlineDate, boolean isDone, Long employeeId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.employeeId = employeeId;
        this.isDone = isDone;
    }

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
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
        TaskDTO task = (TaskDTO) o;
        return isDone == task.isDone && Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(deadlineDate, task.deadlineDate) && Objects.equals(employeeId, task.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadlineDate, employeeId, isDone);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", employeeId=" + employeeId +
                ", isDone=" + isDone +
                '}';
    }
}
